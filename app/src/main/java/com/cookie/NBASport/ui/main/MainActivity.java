package com.cookie.NBASport.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.cookie.NBASport.R;
import com.cookie.NBASport.base.BaseActivity;
import com.cookie.NBASport.di.ActivityComponent;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.frameMain)
    FrameLayout frameMain;
    private Fragment mainFragment;

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        addMainFragment();
    }

    private void addMainFragment(){
        mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameMain,mainFragment);
        fragmentTransaction.commit();
    }
}
