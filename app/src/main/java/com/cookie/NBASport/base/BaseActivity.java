package com.cookie.NBASport.base;/**
 * Created by Chen Lin Jiang on 2017/1/10.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cookie.NBASport.R;
import com.cookie.NBASport.di.ActivityComponent;
import com.cookie.NBASport.di.DaggerActivityComponent;
import com.cookie.NBASport.di.module.ActivityModule;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-10
 */
public abstract class BaseActivity<T> extends AppCompatActivity {
    @Inject
    protected T mPresenter;
    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTranslate(R.color.colorPrimary);

        activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
        injectActivity(activityComponent);

        setContentView(getContentView());

        ButterKnife.bind(this);

        init();
    }

    protected abstract void injectActivity(ActivityComponent activityComponent);
    protected abstract int getContentView();
    protected abstract void init();

    public void setStatusBarTranslate(int colorResId){
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        // enable navigation bar tint
        //tintManager.setStatusBarTintColor(this.getResources().getColor(colorResId));

        tintManager.setNavigationBarAlpha(0);
        tintManager.setStatusBarAlpha(0);

    }
}
