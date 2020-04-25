package com.diju.dictdemo.dict.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CityInfoBean implements Parcelable {
    private String id; /*110101*/
    private String name; /*东城区*/
    private ArrayList<CityInfoBean> cityList;
    public CityInfoBean() {
    }

    public static CityInfoBean findCity(List<CityInfoBean> list, String cityName) {
        try {
            for (int i = 0; i < list.size(); i++) {
                CityInfoBean city = list.get(i);
                if (cityName.equals(city.getName()) /*|| cityName.contains(city.getName())
                        || city.getName().contains(cityName)*/) {
                    return city;
                }
            }
        }
        catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(this.cityList);
    }

    protected CityInfoBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.cityList = in.createTypedArrayList(CityInfoBean.CREATOR);
    }

    public static final Creator<CityInfoBean> CREATOR = new Creator<CityInfoBean>() {
        @Override
        public CityInfoBean createFromParcel(Parcel source) {
            return new CityInfoBean(source);
        }

        @Override
        public CityInfoBean[] newArray(int size) {
            return new CityInfoBean[size];
        }
    };
}
