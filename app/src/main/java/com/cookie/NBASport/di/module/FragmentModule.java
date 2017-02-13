package com.cookie.NBASport.di.module;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-13
 */
@Module
public class FragmentModule {
    private Context context;

    public FragmentModule(Context context){
        this.context = context;
    }
    @Provides
    public Context getContext(){
        return context;
    }

}
