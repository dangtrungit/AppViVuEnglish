package com.trunghi.dangtrung.vivulearningenglish.ViewActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.trunghi.dangtrung.vivulearningenglish.Adapters.ExampleDetailAdapter;
import com.trunghi.dangtrung.vivulearningenglish.Adapters.ExampleDetailOfInfoDetailAdapter;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;

import java.util.List;

public class ExampleDetailOfInfoDetailActivity extends AppCompatActivity {
    List<DataLessonDetailModel> dataLessonDetailModelList;
    RecyclerView recyclerView;
    Toolbar toolbar;
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

        dataLessonDetailModelList = getIntent().getParcelableArrayListExtra("datalistLesson");
        String id = getIntent().getStringExtra("keyid");
        int iD = Integer.parseInt(id);
        Log.d("cehkk",dataLessonDetailModelList.get(0).getListexample().size()+"");

        toolbar = findViewById(R.id.tbdetailimage);
        recyclerView = findViewById(R.id.rvdetailimage);

        if (dataLessonDetailModelList.get(iD).getListexample().size() > 0) {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(ExampleDetailOfInfoDetailActivity.this);
            recyclerView.setLayoutManager(mLayoutManager);
            ExampleDetailOfInfoDetailAdapter exampleDetailOfInfoDetailAdapter = new ExampleDetailOfInfoDetailAdapter(ExampleDetailOfInfoDetailActivity.this, R.layout.custom_layout_exampledetail, dataLessonDetailModelList,iD);
            recyclerView.setAdapter(exampleDetailOfInfoDetailAdapter);
            exampleDetailOfInfoDetailAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
