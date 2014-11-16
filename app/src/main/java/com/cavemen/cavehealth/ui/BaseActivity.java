package com.cavemen.cavehealth.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.cavemen.cavehealth.Config;
import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.gcm.ServerUtilities;
import com.cavemen.cavehealth.util.ActivityFragmentNavigator;
import com.cavemen.cavehealth.util.NavDrawerManager;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class BaseActivity extends ActionBarActivity implements
        NavDrawerManager.NavDrawerListener,
        NavDrawerManager.NavDrawerItemAware {

    protected NavDrawerManager.NavDrawerItemAware mCurrentNavigationElement;
    private NavDrawerManager mDrawerManager;
    // Primary toolbar and drawer toggle
    private Toolbar mActionBarToolbar;

    String regId;
    GoogleCloudMessaging gcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        regId = ServerUtilities.getGcmId(this);
        if (TextUtils.isEmpty(regId)) {
            registerInBackground();
        }
    }

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

    private void registerInBackground() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regId = gcm.register("500496825264");
                    msg = "Device registered, registration ID=" + regId;

                    // Persist the regID - no need to register again.
                    ServerUtilities.register(getApplicationContext(), regId, Config.GCM_API_KEY);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }
        }.execute(null, null, null);
    }
}
