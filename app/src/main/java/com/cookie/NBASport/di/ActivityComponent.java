package com.cookie.NBASport.di;/**
 * Created by Chen Lin Jiang on 2017/2/4.
 */

import com.cookie.NBASport.di.module.ActivityModule;
import com.cookie.NBASport.ui.main.NewDetailActivity;

import dagger.Component;

/**
 * User: Chen Lin Jiang
 * Date: 2017-02-04
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
   void inject(NewDetailActivity newDetailActivity);
}
