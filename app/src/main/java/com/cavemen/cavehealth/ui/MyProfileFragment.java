package com.cavemen.cavehealth.ui;

import android.app.Fragment;

import com.cavemen.cavehealth.util.NavDrawerManager;

public class MyProfileFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @Override
    public int getSelfNavDrawerItem() {
        return NavDrawerManager.NAVDRAWER_ITEM_MY_PROFILE;
    }
}