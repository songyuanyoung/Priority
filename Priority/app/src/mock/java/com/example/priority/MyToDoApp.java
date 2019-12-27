package com.example.priority;

import android.app.Application;

import timber.log.Timber;

public class MyToDoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }
}
