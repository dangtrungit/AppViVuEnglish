package com.trunghi.dangtrung.vivulearningenglish.Models;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DataLearnModel implements Parcelable {
    public DataLearnModel(){

    }
    public DataLearnModel(String tenlesson){
        this.tenlesson =tenlesson;
    }

    protected DataLearnModel(Parcel in) {
        malesson = in.readString();
        tenlesson = in.readString();

    }

    public static final Creator<DataLearnModel> CREATOR = new Creator<DataLearnModel>() {
        @Override
        public DataLearnModel createFromParcel(Parcel in) {
            return new DataLearnModel(in);
        }

        @Override
        public DataLearnModel[] newArray(int size) {
            return new DataLearnModel[size];
        }
    };

    public String getLesson() {
        return malesson;
    }

    public void setLesson(String malesson) {
        this.malesson = malesson;
    }

    String malesson;

    public String getTenlesson() {
        return tenlesson;
    }

    public void setTenlesson(String tenlesson) {
        this.tenlesson = tenlesson;
    }

    String tenlesson;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(malesson);
        dest.writeString(tenlesson);
    }
}
