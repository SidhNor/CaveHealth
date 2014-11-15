package com.cavemen.cavehealth.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cavemen.cavehealth.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.daily_challenge_progress)
public class DailyChallengeProgress extends RelativeLayout {


    @ViewById(R.id.progress_front)
    View progressFront;

    @ViewById(R.id.challenge_one)
    TextView challengeOne;
    @ViewById(R.id.challenge_three)
    TextView challengeThree;
    @ViewById(R.id.challenge_two)
    TextView challengeTwo;

    @ViewById(R.id.challenge_tip)
    TextView challengeTip;

    public DailyChallengeProgress(Context context) {
        super(context);
    }

    public DailyChallengeProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DailyChallengeProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bindProgressData(int progress, String ch1, String ch2, String ch3) {
        switch (progress) {
            case 0:
                progressFront.setBackgroundResource(0);
                challengeTip.setText("You will loose 40 health points if you do not complete any of the challenge parts");
                break;
            case 1:
                progressFront.setBackgroundResource(R.drawable.daily_challenge_one);
                challengeTip.setText("You will receive 10 health points for completing one part");
                break;
            case 2:
                progressFront.setBackgroundResource(R.drawable.daily_challenge_two);
                challengeTip.setText("You will receive 10 health points for completing two parts");
                break;
            case 3:
                progressFront.setBackgroundResource(R.drawable.daily_challenge_done);
                challengeTip.setText("Congrats, you received 30 health points and a bonus of 20 health points for completing all challenge parts");
                break;
            default:
                progressFront.setBackgroundResource(0);
                break;
        }
        challengeOne.setText(ch1);
        challengeTwo.setText(ch2);
        challengeThree.setText(ch3);
    }

}
