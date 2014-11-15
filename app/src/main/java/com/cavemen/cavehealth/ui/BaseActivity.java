package com.cavemen.cavehealth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.util.NavDrawerManager;

public class BaseActivity extends ActionBarActivity implements
        NavDrawerManager.NavDrawerListener,
        NavDrawerManager.NavDrawerItemAware {

    protected NavDrawerManager.NavDrawerItemAware mCurrentNavigationElement;
    private NavDrawerManager mDrawerManager;
    // Primary toolbar and drawer toggle
    private Toolbar mActionBarToolbar;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerManager = new NavDrawerManager(this);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    public Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }

    @Override
    public void goToNavDrawerItem(int itemId) {
        Intent intent;
        switch (itemId) {
            case NavDrawerManager.NAVDRAWER_ITEM_MY_CAVE:
                //TODO Switch fragment util.ActivityFragmentNavigator
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_NEWS:
                //TODO Switch fragment with util.ActivityFragmentNavigator
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_ACTIVITY_HISTORY:
                //TODO Switch fragment with util.ActivityFragmentNavigator
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_GROUP_ACTIVITIES:
                //TODO Switch fragment with util.ActivityFragmentNavigator
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_MY_PROFILE:
                //TODO Switch fragment with util.ActivityFragmentNavigator
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_FIND_A_PEER:
                //TODO Switch fragment with util.ActivityFragmentNavigator
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_PVP:
                //TODO Switch fragment with util.ActivityFragmentNavigator
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_SETTINGS:
                SettingsActivity_.intent(this).start();
                break;
        }
    }

    @Override
    public int getSelfNavDrawerItem() {
        return NavDrawerManager.NAVDRAWER_ITEM_INVALID;
    }

    @Override
    public void onNavDrawerStateChanged(boolean isOpen, boolean isAnimating) {

    }
}
