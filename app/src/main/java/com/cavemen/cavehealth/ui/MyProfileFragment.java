package com.cavemen.cavehealth.ui;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.model.Achievement;
import com.cavemen.cavehealth.model.GroupActivity;
import com.cavemen.cavehealth.service.KennyStats_;
import com.cavemen.cavehealth.ui.widget.AchievementItemView;
import com.cavemen.cavehealth.ui.widget.AchievementItemView_;
import com.cavemen.cavehealth.ui.widget.DividerItemDecoration;
import com.cavemen.cavehealth.ui.widget.GroupActivityItemView;
import com.cavemen.cavehealth.ui.widget.GroupActivityItemView_;
import com.cavemen.cavehealth.util.NavDrawerManager;
import com.cavemen.cavehealth.util.PrefGsonHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

@EFragment(R.layout.fragment_my_profile)
public class MyProfileFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @ViewById(R.id.avatar_image)
    ImageView avatarImage;

    @ViewById(R.id.level_text)
    TextView levelText;

    @ViewById(R.id.health_bar)
    ProgressBar healthBar;

    @ViewById(R.id.health_value)
    TextView healthValue;

    @Pref
    KennyStats_ mStatsProvider;

    @ViewById(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    List<Achievement> allActivities;
    List<Integer> myActivities;
    @Override
    public int getSelfNavDrawerItem() {
        return NavDrawerManager.NAVDRAWER_ITEM_MY_PROFILE;
    }

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

        String activitiesStr = mStatsProvider.achievements().get();
        allActivities = PrefGsonHelper.getListOfGroupAchievemnts(activitiesStr);
        myActivities = PrefGsonHelper.getListOfInts(mStatsProvider.myGroupActivities().get());


        mRecyclerView.setHasFixedSize(true);
        mAdapter = new AchievementsAdapter(allActivities, myActivities);
        mRecyclerView.setAdapter(mAdapter);
    }
    public static class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ViewHolder> {
        private List<Achievement> mDataset;
        private List<Integer> myActs;

        // Provide a suitable constructor (depends on the kind of dataset)
        public AchievementsAdapter(List<Achievement> myDataset, List<Integer> myActs) {
            mDataset = myDataset;
            this.myActs = myActs;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public AchievementsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
            // create a new view
            AchievementItemView v = AchievementItemView_.build(parent.getContext());
            return new ViewHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.item.bindData(mDataset.get(position), myActs.contains(mDataset.get(position).getId()));

        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public AchievementItemView item;

            public ViewHolder(AchievementItemView v) {
                super(v);
                item = v;
            }
        }
    }
}
