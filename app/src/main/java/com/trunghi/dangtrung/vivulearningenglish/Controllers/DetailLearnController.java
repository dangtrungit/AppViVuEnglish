package com.trunghi.dangtrung.vivulearningenglish.Controllers;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.trunghi.dangtrung.vivulearningenglish.Adapters.ListLearnDetailAdapter;
import com.trunghi.dangtrung.vivulearningenglish.Adapters.RecyclerviewLearnAdapter;
import com.trunghi.dangtrung.vivulearningenglish.Controllers.Interfaces.DetailLearnInterface;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLearnModel;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailLearnController {
    DatabaseReference databaseReference;
    Context context;
    ListLearnDetailAdapter listLearnDetailAdapter;
    List<DataLessonDetailModel> dataLessonDetailModels;
    DataLessonDetailModel dataLessonDetailModel;
    DataLearnModel dataLearnModel;
    boolean check;
    boolean check2;
    TextToSpeech textToSpeech;
    boolean isloadedImage;

    public DetailLearnController(Context context, DataLearnModel dataLearnModel,TextToSpeech textToSpeech, boolean isloadedImage){
        this.context = context;
        this.dataLearnModel = dataLearnModel;
        this.isloadedImage = isloadedImage;
        this.check = check;
        this.check2 = check2;
        this.textToSpeech=textToSpeech;

        dataLessonDetailModels= new ArrayList<>();
        dataLessonDetailModel= new DataLessonDetailModel();

    }

    public void getDataDetailforLearn(RecyclerView recyclerView){

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        listLearnDetailAdapter = new ListLearnDetailAdapter(context,dataLessonDetailModels, R.layout.custom_layout_listlessondetaillearn,textToSpeech,isloadedImage);
        recyclerView.setAdapter(listLearnDetailAdapter);

        DetailLearnInterface detailLearnInterface = new DetailLearnInterface() {
            @Override
            public void onGetDataDetailFirebase(DataLessonDetailModel dataLessonDetailModel) {
                dataLessonDetailModels.add(dataLessonDetailModel);
                listLearnDetailAdapter.notifyDataSetChanged();
            }
        };
        getDataFirebase(detailLearnInterface);
    }

    public void getDataforPractice(){
        DetailLearnInterface detailLearnInterface = new DetailLearnInterface() {
            @Override
            public void onGetDataDetailFirebase(DataLessonDetailModel dataLessonDetailModel) {
                dataLessonDetailModels.add(dataLessonDetailModel);
            }
        };
        getDataFirebase(detailLearnInterface);

    }


    public void getDataFirebase(final DetailLearnInterface detailLearnInterface){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshot1 = dataSnapshot.child("englishlesson").child(dataLearnModel.getLesson());
                for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()){
                    DataLessonDetailModel dataLessonDetailModel = dataSnapshot2.getValue(DataLessonDetailModel.class);
                    dataLessonDetailModel.setMalesson(dataLearnModel.getLesson());
                    dataLessonDetailModel.setKeyID(dataSnapshot2.getKey());

                    DataSnapshot dataSnapshotHinhanh = dataSnapshot.child("images")
                            .child(dataLessonDetailModel.getMalesson()).child(dataLessonDetailModel.getKeyID());
                    List<String> listimages = new ArrayList<>();
                    for (DataSnapshot valueimages : dataSnapshotHinhanh.getChildren()){
                        listimages.add(valueimages.getValue(String.class));
                    }
                    dataLessonDetailModel.setImages(listimages);
                    DataSnapshot dataSnapshotExampledetail = dataSnapshot.child("exampledetail")
                            .child(dataLessonDetailModel.getMalesson()).child(dataLessonDetailModel.getKeyID());

                    List<String> listexampledetail = new ArrayList<>();
                    for (DataSnapshot valuelistexampledetail : dataSnapshotExampledetail.getChildren()){
                        listexampledetail.add(valuelistexampledetail.getValue(String.class));
                    }
                    dataLessonDetailModel.setListexample(listexampledetail);

                    DataSnapshot datatranslate = dataSnapshot.child("translate")
                            .child(dataLessonDetailModel.getMalesson()).child(dataLessonDetailModel.getKeyID());
                    List<String> listtranslate = new ArrayList<>();
                    for (DataSnapshot valuelisttranslate : datatranslate.getChildren()){
                        listtranslate.add(valuelisttranslate.getValue(String.class));
                    }
                    dataLessonDetailModel.setListtranslate(listtranslate);

                    detailLearnInterface.onGetDataDetailFirebase(dataLessonDetailModel);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

}
