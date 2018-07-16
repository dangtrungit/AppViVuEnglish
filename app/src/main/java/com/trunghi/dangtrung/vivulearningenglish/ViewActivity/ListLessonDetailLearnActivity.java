package com.trunghi.dangtrung.vivulearningenglish.ViewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.trunghi.dangtrung.vivulearningenglish.Controllers.DetailLearnController;
import com.trunghi.dangtrung.vivulearningenglish.Controllers.LearnController;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLearnModel;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListLessonDetailLearnActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    Toolbar toolbar;
    TextToSpeech textToSpeech;
    DataLearnModel dataLearnModel;;
    DataLessonDetailModel dataLessonDetailModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listlessondetaillearn);
       toolbar= findViewById(R.id.toobarlistlesson);
       recyclerView = findViewById(R.id.recyclerlearndetail);

        dataLearnModel =  getIntent().getParcelableExtra("lesson");

        String title = getIntent().getStringExtra("title");
//        dataLearnModel = getIntent().getParcelableExtra("datadetail");
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textToSpeech = new TextToSpeech(ListLessonDetailLearnActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }else {
                    Toast.makeText(getApplicationContext(),"Tính năng không hỗ trợ trên thiết bị của bạn!",Toast.LENGTH_LONG).show();
                }
            }
        });

        DetailLearnController detailLearnController = new DetailLearnController(this,dataLearnModel,textToSpeech,true);
        detailLearnController.getDataDetailforLearn(recyclerView);

    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
