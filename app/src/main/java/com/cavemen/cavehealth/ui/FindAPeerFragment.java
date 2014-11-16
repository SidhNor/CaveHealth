package com.cavemen.cavehealth.ui;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.model.Activity;
import com.cavemen.cavehealth.service.KennyStats_;
import com.cavemen.cavehealth.ui.widget.ActivityItemView;
import com.cavemen.cavehealth.ui.widget.ActivityItemView_;
import com.cavemen.cavehealth.util.NavDrawerManager;
import com.cavemen.cavehealth.util.PrefGsonHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.ItemSelectionSupport;
import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.ListLayoutManager;
import org.lucasr.twowayview.widget.SpacingItemDecoration;

import java.util.List;

@EFragment(R.layout.fragment_find_a_peer)
public class FindAPeerFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @ViewById(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Pref
    KennyStats_ mStatsProvider;
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

        final ItemSelectionSupport itemSelectionSupport = ItemSelectionSupport.addTo(mRecyclerView);

        itemSelectionSupport.setChoiceMode(ItemSelectionSupport.ChoiceMode.SINGLE);

        itemClick.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View child, int position, long id) {

            }
        });
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
