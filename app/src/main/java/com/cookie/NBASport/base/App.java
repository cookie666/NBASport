package com.cookie.NBASport.base;/**
 * Created by Chen Lin Jiang on 2017/1/13.
 */

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.smtt.sdk.QbSdk;

/**
 * User: Chen Lin Jiang
 * Date: 2017-01-13
 */
public class App extends Application {
    private static App app;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app= this;
        Fresco.initialize(this);
        QbSdk.initX5Environment(this,null);
        refWatcher = LeakCanary.install(this);
    }

    public static App getApplication(){
        return app;
    }

    public static RefWatcher getRefWatcher() {
        return app.refWatcher;
    }
}
