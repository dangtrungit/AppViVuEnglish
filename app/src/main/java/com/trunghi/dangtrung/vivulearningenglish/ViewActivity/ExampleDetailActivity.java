package com.trunghi.dangtrung.vivulearningenglish.ViewActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.trunghi.dangtrung.vivulearningenglish.Adapters.ExampleDetailAdapter;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ExampleDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    DataLessonDetailModel dataLessonDetailModel;
    List<DataLessonDetailModel>dataLessonDetailModelList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detailimage);
        toolbar = findViewById(R.id.tbdetailimage);
        recyclerView = findViewById(R.id.rvdetailimage);

        toolbar.setTitle("Ví dụ chi tiết");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        boolean check = getIntent().getBooleanExtra("checkdataLessonDetailModel",false);
        dataLessonDetailModel = getIntent().getParcelableExtra("dataexamdetail");
        Log.d("geigie",dataLessonDetailModel.getKeyID()+"");

             if (dataLessonDetailModel.getListexample().size()  >0&&dataLessonDetailModel.getListtranslate().size()  >0) {
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(ExampleDetailActivity.this);
                recyclerView.setLayoutManager(mLayoutManager);
                ExampleDetailAdapter exampleDetailAdapter = new ExampleDetailAdapter(ExampleDetailActivity.this, R.layout.custom_layout_exampledetail, dataLessonDetailModel,check);
                recyclerView.setAdapter(exampleDetailAdapter);
                exampleDetailAdapter.notifyDataSetChanged();
        }
    }
    private void getDataFirebaseListEx(){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
