package com.cavemen.cavehealth.ui;

import android.app.Fragment;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cavemen.cavehealth.R;
import com.cavemen.cavehealth.util.NavDrawerManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_my_cave)
public class MyCaveFragment extends Fragment
        implements NavDrawerManager.NavDrawerItemAware {

    @ViewById(R.id.avatar_image)
    ImageView avatarImage;

    @ViewById(R.id.level_text)
    TextView levelText;

    @ViewById(R.id.health_bar)
    ProgressBar healthBar;

    @AfterViews
    void afterViews() {
        healthBar.setMax(100);

        healthBar.setProgress(87);
        levelText.setText(getString(R.string.level_text, 2));
    }

    @Override
    public int getSelfNavDrawerItem() {
        return NavDrawerManager.NAVDRAWER_ITEM_MY_CAVE;
    }
}
