package com.cavemen.cavehealth.ui.widget;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.model.Activity;
import com.cavemen.cavehealth.model.Match;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

@EViewGroup(R.layout.upcoming_match)
public class UpcomingMatchWidget extends FrameLayout {


    @ViewById(R.id.activity_icon)
    ImageView activityIcon;
    @ViewById(R.id.activity_name)
    TextView nameTx;
    @ViewById(R.id.match_time)
    TextView matchTime;
    @ViewById(R.id.match_status)
    TextView matchStatus;

    public UpcomingMatchWidget(Context context) {
        super(context);
    }

    public UpcomingMatchWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UpcomingMatchWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bindWithData(Match match) {
        Activity act = match.getActivity();
        activityIcon.setImageResource(getResources().getIdentifier(act.getIconResourceName(), "drawable", this.getContext().getPackageName()));
        nameTx.setText(act.getName());
        String formattedMatchDate = DateUtils.getRelativeTimeSpanString(
                match.getTimeStamp(), new Date().getTime(),
                DateUtils.MINUTE_IN_MILLIS).toString();
        matchTime.setText(formattedMatchDate);
    }
}
