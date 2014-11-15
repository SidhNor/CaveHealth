package com.cavemen.cavehealth.ui;

import android.app.Fragment;

import com.cavemen.cavehealth.util.NavDrawerManager;

public class ActivityHistoryFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @Override
    public int getSelfNavDrawerItem() {
        return 0;
    }
}
