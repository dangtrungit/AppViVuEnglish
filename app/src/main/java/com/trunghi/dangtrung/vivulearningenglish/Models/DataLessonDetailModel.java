package com.trunghi.dangtrung.vivulearningenglish.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DataLessonDetailModel implements Parcelable {
    String malesson;
    String kieuloai;
    String phienam;
    String phiendich;
    String tuvung;

    String dapan1;
    String dapan2;
    String dapan3;
    String dapan4;
    String dapan5;
    long sotuvung;
    long score;

    List<String> images;

    List<String> listtranslate;
    List<String> listexample;




    public long getSotuvung() {
        return sotuvung;
    }

    public void setSotuvung(long sotuvung) {
        this.sotuvung = sotuvung;
    }






    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }


    public String getDapan1() {
        return dapan1;
    }

    public void setDapan1(String dapan1) {
        this.dapan1 = dapan1;
    }

    public String getDapan2() {
        return dapan2;
    }

    public void setDapan2(String dapan2) {
        this.dapan2 = dapan2;
    }

    public String getDapan3() {
        return dapan3;
    }

    public void setDapan3(String dapan3) {
        this.dapan3 = dapan3;
    }

    public String getDapan4() {
        return dapan4;
    }

    public void setDapan4(String dapan4) {
        this.dapan4 = dapan4;
    }

    public String getDapan5() {
        return dapan5;
    }

    public void setDapan5(String dapan5) {
        this.dapan5 = dapan5;
    }




    public List<String> getListtranslate() {
        return listtranslate;
    }

    public void setListtranslate(List<String> listtranslate) {
        this.listtranslate = listtranslate;
    }




    public List<String> getListexample() {
        return listexample;
    }

    public void setListexample(List<String> listexample) {
        this.listexample = listexample;
    }

    public DataLessonDetailModel(){

    }

    protected DataLessonDetailModel(Parcel in) {
        malesson = in.readString();
        kieuloai = in.readString();
        phienam = in.readString();
        phiendich = in.readString();
        tuvung = in.readString();
        keyID = in.readString();
        images = in.createStringArrayList();
        listexample = in.createStringArrayList();
        listtranslate = in.createStringArrayList();


    }

    public static final Creator<DataLessonDetailModel> CREATOR = new Creator<DataLessonDetailModel>() {
        @Override
        public DataLessonDetailModel createFromParcel(Parcel in) {
            return new DataLessonDetailModel(in);
        }

        @Override
        public DataLessonDetailModel[] newArray(int size) {
            return new DataLessonDetailModel[size];
        }
    };

    public String getMalesson() {
        return malesson;
    }

    public void setMalesson(String malesson) {
        this.malesson = malesson;
    }

    public String getKieuloai() {
        return kieuloai;
    }

    public void setKieuloai(String kieuloai) {
        this.kieuloai = kieuloai;
    }

    public String getPhienam() {
        return phienam;
    }

    public void setPhienam(String phienam) {
        this.phienam = phienam;
    }

    public String getPhiendich() {
        return phiendich;
    }

    public void setPhiendich(String phiendich) {
        this.phiendich = phiendich;
    }

    public String getTuvung() {
        return tuvung;
    }

    public void setTuvung(String tuvung) {
        this.tuvung = tuvung;
    }



    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    String keyID;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(malesson);
        dest.writeString(kieuloai);
        dest.writeString(phienam);
        dest.writeString(phiendich);
        dest.writeString(tuvung);
        dest.writeString(keyID);
        dest.writeStringList(images);
        dest.writeStringList(listexample);
        dest.writeStringList(listtranslate);
    }
}
