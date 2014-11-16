package com.cavemen.cavehealth.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.model.Activity;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;


@EViewGroup(R.layout.activity_item_view)
public class ActivityItemView extends LinearLayout {

    @ViewById(R.id.activity_title)
    TextView textView;

    @ViewById(R.id.activity_icon)
    ImageView icon;


    public ActivityItemView(Context context) {
        super(context);
    }

    public ActivityItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bindData(Activity activity) {
        textView.setText(activity.getName());
        icon.setImageResource(getResources().getIdentifier(activity.getBigIconResourceName(), "drawable", this.getContext().getPackageName()));
    }
}
