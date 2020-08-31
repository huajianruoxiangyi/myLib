package com.uerbeautybusiness.uersbeauty.application;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.uerbeautybusiness.uersbeauty.base.BaseApplication;


/**
 * Created by work on 17/11/15.
 */

public class MyApplication extends BaseApplication {
    private static MyApplication mApplication;
    static Context sContext;
    private static final String TAG = "MyApplication";

    public static MyApplication getInstance() {
        return mApplication;
    }

    public static Context getAppContext() {
        return mApplication.getApplicationContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        sContext = getApplicationContext();

    }

}
