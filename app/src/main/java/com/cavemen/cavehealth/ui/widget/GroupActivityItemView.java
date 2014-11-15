package com.cavemen.cavehealth.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.model.GroupActivity;
import com.cavemen.cavehealth.service.KennyStats_;
import com.cavemen.cavehealth.util.PrefGsonHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

@EViewGroup(R.layout.group_activity_item_view)
public class GroupActivityItemView extends FrameLayout {

    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.description)
    TextView description;
    @ViewById(R.id.icon)
    ImageView icon;
    @ViewById
    CheckBox isPartOf;

    @Pref
    KennyStats_ mStatsProvider;

    GroupActivity currentActivity;

    public GroupActivityItemView(Context context) {
        super(context);
    }

    public GroupActivityItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupActivityItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    void afterViews() {
        isPartOf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<Integer> list = PrefGsonHelper.getListOfInts(mStatsProvider.myGroupActivities().get());
                if (isChecked && !list.contains(currentActivity.getId())) {
                    //Add to list
                    list.add(currentActivity.getId());
                    mStatsProvider.myGroupActivities().put(PrefGsonHelper.saveListOfInts(list));
                    //Save to list
                } else if (!isChecked && list.contains(currentActivity.getId())) {
                    ///Remove from list
                    list.remove(list.indexOf(currentActivity.getId()));
                    mStatsProvider.myGroupActivities().put(PrefGsonHelper.saveListOfInts(list));
                }
            }
        });
    }

    public void bindData(GroupActivity act, boolean isActive) {
        currentActivity = act;
        icon.setImageResource(getResources().getIdentifier(act.getIconResourceName(), "drawable", this.getContext().getPackageName()));
        title.setText(act.getName());
        description.setText(act.getDescription());
        isPartOf.setChecked(isActive);
    }
}
