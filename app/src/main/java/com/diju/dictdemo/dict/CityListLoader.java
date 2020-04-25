package com.diju.dictdemo.dict;

import android.content.Context;

import com.diju.dictdemo.dict.model.CityInfoBean;
import com.diju.dictdemo.dict.model.Dict;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CityListLoader {
    public static final String BUNDATA = "bundata";

    private static List<Dict> dictListData = new ArrayList<Dict>();

    private static List<CityInfoBean> mCityListData = new ArrayList<>();

    private static List<CityInfoBean> mProListData = new ArrayList<>();

    /**
     * 解析所有的城市数据 357个数据
     */
    public List<CityInfoBean> getCityListData() {
        return mCityListData;
    }

    /**
     * 只解析省份34个数据
     */
    public List<CityInfoBean> getProListData() {
        return mProListData;
    }

    public List<Dict> getDictListData() {
        return dictListData;
    }

    private volatile static CityListLoader instance;

    private CityListLoader() {

    }

    /**
     * 单例模式
     * @return
     */
    public static CityListLoader getInstance() {
        if (instance == null) {
            synchronized (CityListLoader.class) {
                if (instance == null) {
                    instance = new CityListLoader();
                }
            }
        }
        return instance;
    }

    /**
     * 解析357个城市数据
     * @param context
     */
    public void loadCityData(Context context) {

        String cityJson = utils.getJson(context, Constant.CITY_DATA);
        Type type = new TypeToken<ArrayList<CityInfoBean>>() {
        }.getType();

        //解析省份
        ArrayList<CityInfoBean> mProvinceBeanArrayList = new Gson().fromJson(cityJson, type);
        if (mProvinceBeanArrayList == null || mProvinceBeanArrayList.isEmpty()) {
            return;
        }

        for (int p = 0; p < mProvinceBeanArrayList.size(); p++) {

            //遍历每个省份
            CityInfoBean itemProvince = mProvinceBeanArrayList.get(p);

            //每个省份对应下面的市
            ArrayList<CityInfoBean> cityList = itemProvince.getCityList();

            //遍历当前省份下面城市的所有数据
            for (int j = 0; j < cityList.size(); j++) {
                mCityListData.add(cityList.get(j));
            }

        }

        CityInfoBean c1 = new CityInfoBean();
        CityInfoBean c2 = new CityInfoBean();
        c1.setId("1");
        c1.setName("c1");
        c2.setId("2");
        c2.setName("c2");
        mCityListData.add(c1);
        mCityListData.add(c2);

    }

    /**
     * 解析34个省市直辖区数据
     * @param context
     */
    public void loadProData(Context context) {
        String cityJson = utils.getJson(context, Constant.CITY_DATA);
        Type type = new TypeToken<ArrayList<CityInfoBean>>() {
        }.getType();

        //解析省份
        mProListData = new Gson().fromJson(cityJson, type);
    }

    /**
     * 解析字典数据
     * @param context
     */
    public void loadDictData(Context context){
        String dictJson = utils.getJson(context, Constant.DICT_DATA);
        Dict dict = Dict.parseJson(dictJson);
        dictListData = new ArrayList<>(dict.getSubDictList());
    }

}
