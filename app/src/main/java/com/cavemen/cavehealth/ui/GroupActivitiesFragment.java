package com.cavemen.cavehealth.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.model.GroupActivity;
import com.cavemen.cavehealth.service.KennyStats_;
import com.cavemen.cavehealth.ui.widget.DividerItemDecoration;
import com.cavemen.cavehealth.ui.widget.GroupActivityItemView;
import com.cavemen.cavehealth.ui.widget.GroupActivityItemView_;
import com.cavemen.cavehealth.util.NavDrawerManager;
import com.cavemen.cavehealth.util.PrefGsonHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.lucasr.twowayview.ItemClickSupport;

import java.io.File;
import java.util.List;

@EFragment(R.layout.fragment_group_activities)
@OptionsMenu(R.menu.activity_groups_menu)
public class GroupActivitiesFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @Pref
    KennyStats_ mStatsProvider;

    @ViewById(R.id.recycler_view)
    RecyclerView mRecyclerView;

    List<GroupActivity> allActivities;
    List<Integer> myActivities;

    private Uri fileUri;

    private RecyclerView.Adapter mAdapter;

    @AfterViews
    void afterViews() {
        String activitiesStr = mStatsProvider.groupActivities().get();
        allActivities = PrefGsonHelper.getListOfGroupActivities(activitiesStr);
        myActivities = PrefGsonHelper.getListOfInts(mStatsProvider.myGroupActivities().get());

        mRecyclerView.setHasFixedSize(true);
        mAdapter = new GroupActivitiesAdapter(allActivities, myActivities);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        final ItemClickSupport itemClick = ItemClickSupport.addTo(mRecyclerView);

        itemClick.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View child, int position, long id) {
                Toast.makeText(getActivity(), allActivities.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OptionsItem(R.id.action_capture_receipt)
    void captureSportReceipt() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
        fileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, 200);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == Activity.RESULT_OK){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"sprotsStuff@endava.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Caveman Receipt");
            intent.putExtra(Intent.EXTRA_TEXT, "Please find the Caveman's reciept attached to this mail.");
            File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
            Uri uri = Uri.fromFile(file);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(intent, "Send Your Receipt..."));
        }
    }

    @Override
    public int getSelfNavDrawerItem() {
        return NavDrawerManager.NAVDRAWER_ITEM_GROUP_ACTIVITIES;
    }


    public static class GroupActivitiesAdapter extends RecyclerView.Adapter<GroupActivitiesAdapter.ViewHolder> {
        private List<GroupActivity> mDataset;
        private List<Integer> myActs;

        // Provide a suitable constructor (depends on the kind of dataset)
        public GroupActivitiesAdapter(List<GroupActivity> myDataset, List<Integer> myActs) {
            mDataset = myDataset;
            this.myActs = myActs;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public GroupActivitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
            // create a new view
            GroupActivityItemView v = GroupActivityItemView_.build(parent.getContext());
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
            public GroupActivityItemView item;

            public ViewHolder(GroupActivityItemView v) {
                super(v);
                item = v;
            }
        }
    }
}
