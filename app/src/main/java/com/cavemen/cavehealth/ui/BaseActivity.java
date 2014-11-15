package com.cavemen.cavehealth.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.util.ActivityFragmentNavigator;
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
        switch (itemId) {
            case NavDrawerManager.NAVDRAWER_ITEM_MY_CAVE:
                MyCaveFragment caveFrag = MyCaveFragment_.builder().build();
                mCurrentNavigationElement = caveFrag;
                ActivityFragmentNavigator.switchFragmentInMainActivity(this, caveFrag);
                getActionBarToolbar().setTitle(R.string.navdrawer_item_my_cave);
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_NEWS:
                NewsFragment newsFrag = NewsFragment_.builder().build();
                mCurrentNavigationElement = newsFrag;
                ActivityFragmentNavigator.switchFragmentInMainActivity(this, newsFrag);
                getActionBarToolbar().setTitle(R.string.navdrawer_item_news);
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_ACTIVITY_HISTORY:
                ActivityHistoryFragment actFrag = ActivityHistoryFragment_.builder().build();
                mCurrentNavigationElement = actFrag;
                ActivityFragmentNavigator.switchFragmentInMainActivity(this, actFrag);
                getActionBarToolbar().setTitle(R.string.navdrawer_item_activity_history);
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_GROUP_ACTIVITIES:
                GroupActivitiesFragment groupFrag = GroupActivitiesFragment_.builder().build();
                mCurrentNavigationElement = groupFrag;
                ActivityFragmentNavigator.switchFragmentInMainActivity(this, groupFrag);
                getActionBarToolbar().setTitle(R.string.navdrawer_item_group_activities);
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_MY_PROFILE:
                MyProfileFragment myProfFrag = MyProfileFragment_.builder().build();
                mCurrentNavigationElement = myProfFrag;
                ActivityFragmentNavigator.switchFragmentInMainActivity(this, myProfFrag);
                getActionBarToolbar().setTitle(R.string.navdrawer_item_my_profile);
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_FIND_A_PEER:
                FindAPeerFragment peerFrag = FindAPeerFragment_.builder().build();
                mCurrentNavigationElement = peerFrag;
                ActivityFragmentNavigator.switchFragmentInMainActivity(this, peerFrag);
                getActionBarToolbar().setTitle(R.string.navdrawer_item_find_a_peer);
                break;
            case NavDrawerManager.NAVDRAWER_ITEM_PVP:
                PvPFragment pvpFrag = PvPFragment_.builder().build();
                mCurrentNavigationElement = pvpFrag;
                ActivityFragmentNavigator.switchFragmentInMainActivity(this, pvpFrag);
                getActionBarToolbar().setTitle(R.string.navdrawer_item_pvp);
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
