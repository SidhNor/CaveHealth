package com.cavemen.cavehealth.ui;

import android.app.Fragment;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.util.NavDrawerManager;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_group_activities)
public class GroupActivitiesFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @Override
    public int getSelfNavDrawerItem() {
        return NavDrawerManager.NAVDRAWER_ITEM_GROUP_ACTIVITIES;
    }
}
