package com.cavemen.cavehealth.util;

import android.app.Fragment;
import android.app.FragmentTransaction;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.ui.BaseActivity;

public class ActivityFragmentNavigator {

    public static void switchFragmentInMainActivity(BaseActivity activity, Fragment fragment) {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_holder, fragment);
        transaction.setCustomAnimations(0, 0);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
