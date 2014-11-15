package com.cavemen.cavehealth.util;


import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class LUtils {

    private static Typeface sMediumTypeface;

    protected ActionBarActivity mActivity;
    private Handler mHandler = new Handler();

    private LUtils(ActionBarActivity activity) {
        mActivity = activity;
    }

    public static LUtils getInstance(ActionBarActivity activity) {
        return new LUtils(activity);
    }

    private static boolean hasL() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public void startActivityWithTransition(Intent intent, final View clickedView,
                                            final String transitionName) {
        ActivityOptions options = null;
        if (hasL() && clickedView != null && !TextUtils.isEmpty(transitionName)) {
//            options = ActivityOptions.makeSceneTransitionAnimation(
//                    mActivity, clickedView, transitionName);
        }

        mActivity.startActivity(intent, (options != null) ? options.toBundle() : null);
    }

    public void setMediumTypeface(TextView textView) {
        if (hasL()) {
            if (sMediumTypeface == null) {
                sMediumTypeface = Typeface.create("sans-serif-medium", Typeface.NORMAL);
            }

            textView.setTypeface(sMediumTypeface);
        } else {
            textView.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        }
    }

    public int getStatusBarColor() {
        if (!hasL()) {
            // On pre-L devices, you can have any status bar color so long as it's black.
            return Color.BLACK;
        }

        return mActivity.getWindow().getStatusBarColor();
    }

    public void setStatusBarColor(int color) {
        if (!hasL()) {
            return;
        }

        mActivity.getWindow().setStatusBarColor(color);
    }
}
