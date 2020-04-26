package com.diju.dictdemo;

import android.app.Application;

import com.diju.dictdemo.dict.DictListLoader;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 预先加载一级列表所有城市的数据
         */
//        DictListLoader.getInstance().loadCityData(this);

        /**
         * 预先加载三级列表显示省市区的数据
         */
//        DictListLoader.getInstance().loadProData(this);

        /**
         * 预先加载字典数据
         */
        DictListLoader.getInstance().loadDictData(this);
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
