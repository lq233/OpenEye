package com.example.lq.eyes.base;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by 003 on 2019/5/15.
 */

public class BaseApp extends Application {
    private static BaseApp sApp;
    public static SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        mSharedPreferences = getSharedPreferences("userTag", MODE_PRIVATE);

    }

    public static BaseApp getApp() {
        return sApp;
    }
}
