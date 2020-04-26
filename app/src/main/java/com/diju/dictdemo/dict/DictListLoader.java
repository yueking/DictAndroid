package com.diju.dictdemo.dict;

import android.content.Context;

import com.diju.dictdemo.dict.model.Dict;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DictListLoader {
    public static final String BUNDATA = "bundata";

    private static List<Dict> dictListData = new ArrayList<Dict>();


    public List<Dict> getDictListData() {
        return dictListData;
    }

    private volatile static DictListLoader instance;

    private DictListLoader() {

    }

    /**
     * 单例模式
     * @return
     */
    public static DictListLoader getInstance() {
        if (instance == null) {
            synchronized (DictListLoader.class) {
                if (instance == null) {
                    instance = new DictListLoader();
                }
            }
        }
        return instance;
    }

    /**
     * 解析字典数据
     * @param context
     */
    public void loadDictData(Context context){
        String dictJson = Utils.getJson(context, Constant.DICT_DATA);
        Dict dict = Dict.parseJson(dictJson);
        dictListData = new ArrayList<>(dict.getSubDictList());
    }

}
