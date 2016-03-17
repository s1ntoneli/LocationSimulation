package com.kodelabs.boilerplate;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // initiate Timber
        Timber.plant(new DebugTree());

        AVOSCloud.initialize(this, LeanCloudConstants.LEANCLOUD_APPID, LeanCloudConstants.LEANCLOUD_APPKEY); }
}
