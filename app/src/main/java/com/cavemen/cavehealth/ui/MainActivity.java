package com.cavemen.cavehealth.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cavemen.cavehealth.BuildConfig;
import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.util.NavDrawerManager;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToNavDrawerItem(NavDrawerManager.NAVDRAWER_ITEM_MY_CAVE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_debug:
                if (BuildConfig.DEBUG) {
                    //startActivity(new Intent(this, DebugActionRunnerActivity.class));
                }
                return true;
        }
        //Handle default options
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getSelfNavDrawerItem() {
        if (mCurrentNavigationElement != null) {
            return mCurrentNavigationElement.getSelfNavDrawerItem();
        }
        return NavDrawerManager.NAVDRAWER_ITEM_INVALID;
    }
}
