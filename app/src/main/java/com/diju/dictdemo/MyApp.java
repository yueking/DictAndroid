package com.diju.dictdemo;

import android.app.Application;

import com.diju.dictdemo.dict.DictListLoader;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 程序启动加载字典数据
         */
        DictListLoader.getInstance().loadDictData(this);
    }
}
