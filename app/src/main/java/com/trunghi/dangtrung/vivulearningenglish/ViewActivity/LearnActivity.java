package com.trunghi.dangtrung.vivulearningenglish.ViewActivity;



import android.app.ProgressDialog;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.trunghi.dangtrung.vivulearningenglish.Adapters.Customadapter;
import com.trunghi.dangtrung.vivulearningenglish.Controllers.LearnController;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLearnModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import static java.lang.Thread.sleep;


public class LearnActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;
    boolean isLoading = false;
    ProgressDialog progress;
    boolean check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_learn);
        recyclerView = findViewById(R.id.recyclerview);


//       recyclerView = findViewById(R.id.recyclerlearn);
        toolbar = findViewById(R.id.toolbarlayoutlearn);
        toolbar.setTitle("Danh sách Lesson");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        check = intent.getBooleanExtra("check", true);
        progress = new ProgressDialog(this,5);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setMax(149);
        progress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int jumpTime = 0;
                try {
                    while (progress.getProgress() <= progress.getMax()) {
                        Thread.sleep(100);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        LearnController learnController = new LearnController(LearnActivity.this,check,isLoading,progress);
        learnController.GetDataLessonForViewLearn(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        FirebaseDatabase.getInstance().getReference();

        Intent i = new Intent(LearnActivity.this,MenuActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
