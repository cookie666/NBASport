package com.cookie.NBASport.di.module;/**
 * Created by Chen Lin Jiang on 2017/2/4.
 */

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * User: Chen Lin Jiang
 * Date: 2017-02-04
 */
@Module
public class ActivityModule {
    private Context context;

    public ActivityModule(Context context){
        this.context = context;
    }

    @Provides
    public Context getContext(){
        return context;
    }
}
