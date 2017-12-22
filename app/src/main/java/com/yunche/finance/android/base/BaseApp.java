package com.yunche.finance.android.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by littlexs on 2017/12/21.
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //内存泄漏工具
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
