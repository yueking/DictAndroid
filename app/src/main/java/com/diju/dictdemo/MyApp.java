package com.diju.dictdemo;

import android.app.Application;
import android.content.Context;

import com.diju.dictdemo.dict.CityListLoader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 预先加载一级列表所有城市的数据
         */
//        CityListLoader.getInstance().loadCityData(this);

        /**
         * 预先加载三级列表显示省市区的数据
         */
//        CityListLoader.getInstance().loadProData(this);

        /**
         * 预先加载字典数据
         */
        CityListLoader.getInstance().loadDictData(this);
//        refWatcher = LeakCanary.install(this);
    }


    //在自己的Application中添加如下代码
//    private RefWatcher refWatcher;

    //在自己的Application中添加如下代码
//    public static RefWatcher getRefWatcher(Context context) {
//        MyApp application = (MyApp) context.getApplicationContext();
//        return application.refWatcher;
//    }
}
