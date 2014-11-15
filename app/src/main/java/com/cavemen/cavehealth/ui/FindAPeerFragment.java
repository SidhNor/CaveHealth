package com.cavemen.cavehealth.ui;

import android.app.Fragment;

import com.cavemen.cavehealth.util.NavDrawerManager;

public class FindAPeerFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @Override
    public int getSelfNavDrawerItem() {
        return NavDrawerManager.NAVDRAWER_ITEM_FIND_A_PEER;
    }
}
