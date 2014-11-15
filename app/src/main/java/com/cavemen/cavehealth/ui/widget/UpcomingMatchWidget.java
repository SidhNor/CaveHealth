package com.cavemen.cavehealth.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.model.Match;

import org.androidannotations.annotations.EViewGroup;

@EViewGroup(R.layout.upcoming_match)
public class UpcomingMatchWidget extends FrameLayout {

    public UpcomingMatchWidget(Context context) {
        super(context);
    }

    public UpcomingMatchWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UpcomingMatchWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bindWithData(Match data) {

    }
}
