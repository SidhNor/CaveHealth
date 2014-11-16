package com.cavemen.cavehealth.ui.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.model.Achievement;
import com.cavemen.cavehealth.service.KennyStats_;
import com.cavemen.cavehealth.util.PrefGsonHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

@EViewGroup(R.layout.achievement_item_view)
public class AchievementItemView extends FrameLayout {

    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.description)
    TextView description;
    @ViewById(R.id.icon)
    ImageView icon;

    @Pref
    KennyStats_ mStatsProvider;

    Achievement achievement;

    public AchievementItemView(Context context) {
        super(context);
    }

    public AchievementItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AchievementItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    void afterViews() {
    }

    public void bindData(Achievement act, boolean isActive) {
        achievement = act;
        icon.setImageResource(R.drawable.ic_achievement);
        if(act.getLevel()==Achievement.LEVEL_BRONZE){
            icon.setColorFilter(getResources().getColor(R.color.bronze));
        }
        if(act.getLevel()==Achievement.LEVEL_GOLD){
            icon.setColorFilter(getResources().getColor(R.color.gold));
        }
        if(act.getLevel()==Achievement.LEVEL_SILVER){
            icon.setColorFilter(getResources().getColor(R.color.silver));
        }
        if(act.getIconResourceName()!=null && !act.getIconResourceName().isEmpty()){
            icon.setImageResource(getResources().getIdentifier(act.getIconResourceName(), "drawable", this.getContext().getPackageName()));
        }
        title.setText(act.getName());
        description.setText(act.getDescription());
    }
}
