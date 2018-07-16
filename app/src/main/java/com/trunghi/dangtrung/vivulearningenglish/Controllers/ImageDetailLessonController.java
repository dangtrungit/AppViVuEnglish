package com.trunghi.dangtrung.vivulearningenglish.Controllers;

import android.content.Context;

import com.trunghi.dangtrung.vivulearningenglish.Models.DataLearnModel;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ImageDetailLessonController {
    Context context;
    DataLessonDetailModel dataLessonDetailModel;
    DatabaseReference databaseReference;
    public ImageDetailLessonController(Context context, DataLessonDetailModel dataLessonDetailModel){
        this.context = context;
        this.dataLessonDetailModel = dataLessonDetailModel;
        databaseReference = FirebaseDatabase.getInstance().getReference();
//        dataLessonDetailModels= new ArrayList<>();
//        dataLessonDetailModel= new DataLessonDetailModel();

    }
}
