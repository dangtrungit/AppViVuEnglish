package com.trunghi.dangtrung.vivulearningenglish.ViewActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.trunghi.dangtrung.vivulearningenglish.Adapters.ImagesDetailLessonAdapter;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailActivity extends AppCompatActivity{
    DataLessonDetailModel dataLessonDetailModel;
    RecyclerView rvDetailimage;
    Toolbar toolbar;
    StorageReference storageReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detailimage);
        rvDetailimage= findViewById(R.id.rvdetailimage);
        toolbar = findViewById(R.id.tbdetailimage);
        dataLessonDetailModel = getIntent().getParcelableExtra("datadetailimage");
        String title = dataLessonDetailModel.getTuvung();
        String title1 = dataLessonDetailModel.getPhiendich();
        toolbar.setTitle(title+" - "+title1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final ProgressDialog progress = new ProgressDialog(this,5);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setMax(dataLessonDetailModel.getImages().size());
        progress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                 int jumpTime =0;
                try {

                    while (progress.getProgress() <= progress.getMax()) {
                        Thread.sleep(200);
                        jumpTime+= 1;
                        progress.setProgress(jumpTime);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if (dataLessonDetailModel.getImages().size()>0){
            final List<Bitmap> bitmapList = new ArrayList<>();
            for (String link : dataLessonDetailModel.getImages()){

                long ONE_MEGABYTE= 1024 * 1024;
                storageReference = FirebaseStorage.getInstance().getReference().child("hinhanhs").child(link);
                storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {

                        Bitmap bitmap =BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        bitmapList.add(bitmap);

                        if (bitmapList.size()==dataLessonDetailModel.getImages().size() && bitmapList.size()>0){
                            ImagesDetailLessonAdapter imagesDetailLessonAdapter = new ImagesDetailLessonAdapter(ImageDetailActivity.this, R.layout.custom_layout_imagelessondetail, dataLessonDetailModel, bitmapList, true);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ImageDetailActivity.this, 1);
                            rvDetailimage.setHasFixedSize(true);
                            rvDetailimage.setLayoutManager(layoutManager);
                            rvDetailimage.setAdapter(imagesDetailLessonAdapter);
                            imagesDetailLessonAdapter.notifyDataSetChanged();
                            progress.dismiss();
                        }
                    }
                });

            }
        }else {
            progress.dismiss();
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
