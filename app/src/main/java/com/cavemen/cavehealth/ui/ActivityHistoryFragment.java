package com.cavemen.cavehealth.ui;

import android.app.Fragment;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.util.NavDrawerManager;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_activity_history)
public class ActivityHistoryFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @Override
    public int getSelfNavDrawerItem() {
        return 0;
    }
}
