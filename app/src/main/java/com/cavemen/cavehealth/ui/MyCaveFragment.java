package com.cavemen.cavehealth.ui;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.gcm.ServerUtilities;
import com.cavemen.cavehealth.model.Activity;
import com.cavemen.cavehealth.model.Match;
import com.cavemen.cavehealth.model.MatchesWrapper;
import com.cavemen.cavehealth.service.KennyStats_;
import com.cavemen.cavehealth.service.SyncClient;
import com.cavemen.cavehealth.service.SyncErrorHandler;
import com.cavemen.cavehealth.ui.widget.DailyChallengeProgress;
import com.cavemen.cavehealth.ui.widget.UpcomingMatchWidget;
import com.cavemen.cavehealth.ui.widget.UpcomingMatchWidget_;
import com.cavemen.cavehealth.util.NavDrawerManager;
import com.cavemen.cavehealth.util.PrefGsonHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EFragment(R.layout.fragment_my_cave)
public class MyCaveFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @ViewById(R.id.avatar_image)
    ImageView avatarImage;

    @ViewById(R.id.level_text)
    TextView levelText;

    @ViewById(R.id.health_bar)
    ProgressBar healthBar;

    @ViewById(R.id.health_value)
    TextView healthValue;

    @ViewById(R.id.kenny_mood_description)
    TextView kennyMoodDescription;

    @ViewById(R.id.daily_challenge)
    DailyChallengeProgress dailyChallenge;

    @ViewById(R.id.linear_container)
    ViewGroup container;

    @Pref
    KennyStats_ mStatsProvider;

    @RestService
    SyncClient syncClient;

    @Bean
    SyncErrorHandler syncErrorHandler;

    private Map<Integer, Activity> activitiesMap = new HashMap<Integer, Activity>();

    @AfterViews
    void afterViews() {

        int currentLevel = mStatsProvider.currentLevel().get();
        int currentHp = mStatsProvider.currentHp().get();
        int currentDailyChallengeProgress = mStatsProvider.dailyChallengeProgress().get();

        int maxHp = currentLevel * 100;
        healthBar.setMax(maxHp);
        healthBar.setProgress(currentHp);
        levelText.setText(getString(R.string.level_text, currentLevel));
        healthValue.setText(getString(R.string.healthValue, currentHp, maxHp));

        dailyChallenge.bindProgressData(currentDailyChallengeProgress, "Push ups", "Tennis", "Foosball");

        loadMyMatches();

        List<Activity> activities = PrefGsonHelper.getListOfActivities(mStatsProvider.activities().get());
        for (Activity act : activities) {
            activitiesMap.put(act.getActivityId(), act);
        }
    }

    @Background
    void loadMyMatches() {
        syncClient.setRestErrorHandler(syncErrorHandler);
        MatchesWrapper wrapper = syncClient.findMyMatches(ServerUtilities.getGcmId(getActivity()));
        if (wrapper == null) {
            return;
        }
        List<Match> matches = wrapper.getItems();
        long currentTime = new Date().getTime();

        if (matches != null) {


            List<Match> upcomingMatches = new ArrayList<Match>();
            for (Match match : matches) {
                if (match.getTimeStamp() > currentTime) {
                    if (activitiesMap.containsKey(match.getActivityId())) {
                        match.setActivity(activitiesMap.get(match.getActivityId()));
                    }
                    upcomingMatches.add(match);
                }
            }

            updateUIForMyMatches(upcomingMatches);
        }
    }

    @UiThread
    void updateUIForMyMatches(List<Match> upcomingMatches) {
        if (upcomingMatches.size() > 0) {
            container.setVisibility(View.VISIBLE);
            for (Match match : upcomingMatches) {
                UpcomingMatchWidget widget = UpcomingMatchWidget_.build(getActivity());
                container.addView(widget);
                widget.bindWithData(match);
            }
        }
    }

    @Override
    public int getSelfNavDrawerItem() {
        return NavDrawerManager.NAVDRAWER_ITEM_MY_CAVE;
    }
}
