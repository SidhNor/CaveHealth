package com.cavemen.cavehealth.ui;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.gcm.ServerUtilities;
import com.cavemen.cavehealth.model.Activity;
import com.cavemen.cavehealth.model.Match;
import com.cavemen.cavehealth.service.KennyStats_;
import com.cavemen.cavehealth.service.SyncClient;
import com.cavemen.cavehealth.service.SyncErrorHandler;
import com.cavemen.cavehealth.ui.widget.ActivityItemView;
import com.cavemen.cavehealth.ui.widget.ActivityItemView_;
import com.cavemen.cavehealth.util.NavDrawerManager;
import com.cavemen.cavehealth.util.PrefGsonHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.ItemSelectionSupport;
import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.ListLayoutManager;
import org.lucasr.twowayview.widget.SpacingItemDecoration;

import java.util.Calendar;
import java.util.List;

@EFragment(R.layout.fragment_find_a_peer)
public class FindAPeerFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware, TimePickerDialog.OnTimeSetListener {

    @ViewById(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Pref
    KennyStats_ mStatsProvider;
    @ViewById(R.id.confirmButton)
    Button confirmButton;
    ItemSelectionSupport itemSelectionSupport;
    @RestService
    SyncClient syncClient;
    @Bean
    SyncErrorHandler syncErrorHandler;
    private RecyclerView.Adapter mAdapter;

    @AfterViews
    void afterViews() {

        List<Activity> activities = PrefGsonHelper.getListOfActivities(mStatsProvider.activities().get());

        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ActivitiesAdapter(activities);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpacingItemDecoration(0, 8));
        mRecyclerView.setLayoutManager(new ListLayoutManager(getActivity(), TwoWayLayoutManager.Orientation.HORIZONTAL));

        final ItemClickSupport itemClick = ItemClickSupport.addTo(mRecyclerView);

        itemSelectionSupport = ItemSelectionSupport.addTo(mRecyclerView);

        itemSelectionSupport.setChoiceMode(ItemSelectionSupport.ChoiceMode.SINGLE);

        itemClick.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View child, int position, long id) {
                itemSelectionSupport.setItemChecked(position, true);
                confirmButton.setVisibility(View.VISIBLE);
                confirmButton.setAlpha(0);
                confirmButton.animate().alpha(1).setDuration(300).start();
            }
        });
    }

    @Click(R.id.confirmButton)
    void confirmButtonClick() {

        int selectedItem = itemSelectionSupport.getCheckedItemPosition();
        Activity act = ((ActivitiesAdapter) mAdapter).getItemAtPosition(selectedItem);
        if (act != null) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity())).show();
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        int selectedItem = itemSelectionSupport.getCheckedItemPosition();
        Activity act = ((ActivitiesAdapter) mAdapter).getItemAtPosition(selectedItem);
        if (act != null) {
            callFindPeer(act, c.getTime().getTime());
        }
    }

    @Background
    void callFindPeer(Activity act, long timeStamp) {
        syncClient.setRestErrorHandler(syncErrorHandler);
        String endId = ServerUtilities.getGcmId(getActivity());
        Match match = syncClient.findPeers(act.getActivityId(), act.getMaxPlayers(), timeStamp, endId, endId);
        confirmPeer();
    }

    @UiThread
    void confirmPeer() {
        Toast.makeText(getActivity(), "You request has been received.", Toast.LENGTH_SHORT).show();
        confirmButton.setVisibility(View.GONE);
        itemSelectionSupport.clearChoices();
    }

    @Override
    public int getSelfNavDrawerItem() {
        return NavDrawerManager.NAVDRAWER_ITEM_FIND_A_PEER;
    }

    public static class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ViewHolder> {

        List<Activity> mDataset;

        public ActivitiesAdapter(List<Activity> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            ActivityItemView v = ActivityItemView_.build(parent.getContext());
            return new ViewHolder(v);
        }

        public Activity getItemAtPosition(int position) {
            if (position >= 0 && position < mDataset.size()) {
                return mDataset.get(position);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.item.bindData(mDataset.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public ActivityItemView item;
            public ViewHolder(ActivityItemView itemView) {
                super(itemView);
                item = itemView;
            }
        }
    }
}
