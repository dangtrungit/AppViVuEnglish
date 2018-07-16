package com.trunghi.dangtrung.vivulearningenglish.Models;

import android.graphics.Bitmap;

import java.util.List;

public class CheckDataModel {
    public List<Bitmap> getList() {
        return list;
    }

    public void setList(List<Bitmap> list) {
        this.list = list;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    List<Bitmap> list;
    boolean isTrue;
    public CheckDataModel(List<Bitmap> list , boolean isTrue){
        this.isTrue = isTrue;
        this.list = list;
    }


}
