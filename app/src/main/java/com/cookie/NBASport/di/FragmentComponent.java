package com.cookie.NBASport.di;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import com.cookie.NBASport.di.module.FragmentModule;
import com.cookie.NBASport.ui.main.MainFragment;
import com.cookie.NBASport.ui.main.TopNewsFragment;

import dagger.Component;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-13
 */
@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(MainFragment fragment);
    void inject(TopNewsFragment todayNewsFragment);
    //void inject(NewsFragment newsFragment);
}
