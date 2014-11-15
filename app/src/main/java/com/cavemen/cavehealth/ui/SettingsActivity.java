package com.cavemen.cavehealth.ui;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cavemen.cavehealth.R;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_settings)
public class SettingsActivity extends BaseActivity {


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toolbar toolbar = getActionBarToolbar();
        toolbar.setTitle(R.string.title_settings);
        toolbar.setNavigationIcon(R.drawable.ic_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateUpToFromChild(SettingsActivity.this,
                        IntentCompat.makeMainActivity(new ComponentName(SettingsActivity.this,
                                MainActivity.class)));
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, SettingsFragment_.builder().build())
                    .commit();
        }
    }
}
