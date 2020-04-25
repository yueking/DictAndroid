package com.diju.dictdemo.dict.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Dict implements Serializable , Parcelable {
    public String toJson(){
        return JSON.toJSON(this).toString();
    }
    public static Dict parseJson(String json){
        return JSON.parseObject(json, Dict.class);
    }

    private String id;
    private String dictKey;
    private String dictValue;
    private String dictType;
    private boolean root;
    private int level;
    private String parentKey;
    private String parentType;
    private String dictSpell;
    private Set<Dict> subDictList;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.dictKey);
        dest.writeString(this.dictValue);
        dest.writeString(this.dictType);
        dest.writeBoolean(this.root);
        dest.writeInt(this.level);
        dest.writeString(this.parentKey);
        dest.writeString(this.parentType);
        dest.writeString(this.dictSpell);
        dest.writeTypedList(new ArrayList(this.getSubDictList()));
    }

    public Dict(){}
    protected Dict(Parcel in) {
        this.id = in.readString();
        this.dictKey= in.readString();
        this.dictValue = in.readString();
        this.dictType = in.readString();
        this.root = in.readBoolean();
        this.level = in.readInt();
        this.parentKey= in.readString();
        this.parentType = in.readString();
        this.dictSpell = in.readString();
        this.subDictList =new HashSet<>(in.createTypedArrayList(Dict.CREATOR));

    }

    public static final Creator<Dict> CREATOR = new Creator<Dict>() {
        @Override
        public Dict createFromParcel(Parcel source) {
            return new Dict(source);
        }

        @Override
        public Dict[] newArray(int size) {
            return new Dict[size];
        }
    };
}