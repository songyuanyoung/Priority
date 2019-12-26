package com.example.priority.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;


public class MainThreadExecutor implements Executor {

    private Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable command) {
        mMainThreadHandler.post(command);
    }
}
