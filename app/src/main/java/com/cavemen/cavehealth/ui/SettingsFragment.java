package com.cavemen.cavehealth.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceFragment;

import org.androidannotations.annotations.EFragment;

@EFragment
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
