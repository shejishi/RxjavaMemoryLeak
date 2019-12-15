package com.example.rxjavamemoryleak;

import android.app.Application;

import leakcanary.LeakCanary;

/**
 * @author Ellison
 * @date 2019/12/15
 * @desc 用一句话描述这个类的作用
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
