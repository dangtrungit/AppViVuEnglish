package com.trunghi.dangtrung.vivulearningenglish.ViewActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trunghi.dangtrung.vivulearningenglish.Adapters.ImagesDetailLessonAdapter;
import com.trunghi.dangtrung.vivulearningenglish.Controllers.DetailLearnController;
import com.trunghi.dangtrung.vivulearningenglish.Controllers.Interfaces.DetailLearnInterface;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLearnModel;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoDetailActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txvngonnguchinh, txvloaitu,txvphienam,txvphiendich,textViewExam1,textViewExam2,textViewExam3,textViewExam4,textViewExam5,textViewExam11,textViewExam21,textViewExam31,textViewExam41,textViewExam51,txvNumvocabulary;
   ImageButton imbtnSpeakex1,imbtnSpeakex2,imageButtonUS,imbtnSpeakex3,imbtnSpeakex4,imbtnSpeakex5;;
   LinearLayout lnspeakUS;
   RecyclerView recyclerViewlistlessondetaillearn;
   CircleImageView cirMore;
    List<DataLessonDetailModel> dataLessonDetailModelList;
    DataLearnModel dataLearnModel;
    DataLessonDetailModel dataLessonDetailModel;
    StorageReference storageReference;
    TextToSpeech textToSpeech;
    int ID;
    String sotuvung;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout_listlessondetaillearn);
        FindViewByID();
        dataLessonDetailModelList = getIntent().getParcelableArrayListExtra("datalist");
        dataLearnModel = getIntent().getParcelableExtra("ahihidatalearnthongtin");
        String id  = getIntent().getStringExtra("id");
         sotuvung  = getIntent().getStringExtra("sotuvung");
        ID = Integer.parseInt(id);

        textToSpeech = new TextToSpeech(InfoDetailActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }else {
                    Toast.makeText(getApplicationContext(),"Tính năng không hỗ trợ trên thiết bị của bạn!",Toast.LENGTH_LONG).show();
                }
            }
        });

        getData();
        txvngonnguchinh.setOnClickListener(this);
        imageButtonUS.setOnClickListener(this);
        imbtnSpeakex1.setOnClickListener(this);
        imbtnSpeakex2.setOnClickListener(this);
        imbtnSpeakex3.setOnClickListener(this);
        imbtnSpeakex4.setOnClickListener(this);
        imbtnSpeakex5.setOnClickListener(this);

    }

    private void getData(){
        if (dataLessonDetailModelList.size()>0){
            DowloadDataImage();
            txvngonnguchinh.setText(dataLessonDetailModelList.get(ID).getTuvung());
            txvphienam.setText(dataLessonDetailModelList.get(ID).getPhienam());
            txvloaitu.setText(dataLessonDetailModelList.get(ID).getKieuloai());
            txvphiendich.setText(dataLessonDetailModelList.get(ID).getPhiendich());
            txvNumvocabulary.setText(sotuvung);
            if (dataLessonDetailModelList.get(ID).getListexample().size()>0 && dataLessonDetailModelList.get(ID).getListtranslate().size()>0){
                textViewExam1.setText("-"+dataLessonDetailModelList.get(ID).getListexample().get(0));
                textViewExam2.setText("-"+dataLessonDetailModelList.get(ID).getListexample().get(1));
                textViewExam3.setText("-"+dataLessonDetailModelList.get(ID).getListexample().get(2));
                textViewExam4.setText("-"+dataLessonDetailModelList.get(ID).getListexample().get(3));
                textViewExam5.setText("-"+dataLessonDetailModelList.get(ID).getListexample().get(4));

                textViewExam11.setText(" ->"+dataLessonDetailModelList.get(ID).getListtranslate().get(0));
                textViewExam21.setText(" ->"+dataLessonDetailModelList.get(ID).getListtranslate().get(1));
                textViewExam31.setText(" ->"+dataLessonDetailModelList.get(ID).getListtranslate().get(2));
                textViewExam41.setText(" ->"+dataLessonDetailModelList.get(ID).getListtranslate().get(3));
                textViewExam51.setText(" ->"+dataLessonDetailModelList.get(ID).getListtranslate().get(4));
            }

        }

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
    public void onClick(View v) {
        String s =txvngonnguchinh.getText().toString();
        String sEx1 =textViewExam1.getText().toString();
        String sEx2 =textViewExam2.getText().toString();
        String sEx3 =textViewExam3.getText().toString();
        String sEx4 =textViewExam4.getText().toString();
        String sEx5 =textViewExam5.getText().toString();
        switch (v.getId()){
            case R.id.txvngonnguchinh:
                speak(s,1.0f,0.1f);
                break;
            case R.id.imbtnspeakus:
                speak(s,0.4f,0.1f);

                break;
            case R.id.btnspeakex1:
                speak(sEx1,1.0f,0.9f);
                break;
            case R.id.btnspeakex2:
                speak(sEx2,0.35f,0.9f);
                break;
                case R.id.btnspeakex3:
                    speak(sEx3,1.0f,0.9f);
                break;
                case R.id.btnspeakex4:
                    speak(sEx4,0.35f,0.9f);
                break;
                case R.id.btnspeakex5:
                    speak(sEx5,1.0f,0.9f);
                break;

        }

    }

    private void speak(final String string, float setpitch,float speech){
        textToSpeech.setPitch(setpitch);
        textToSpeech.setSpeechRate(speech);
        textToSpeech.speak(string,TextToSpeech.QUEUE_FLUSH,null);
    }

    private void DowloadDataImage(){
        final ProgressDialog progress = new ProgressDialog(this,5);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setMax(100);
        progress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int jumpTime = 0;
                try {
                    while (progress.getProgress() <= progress.getMax()) {
                        Thread.sleep(200);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if (dataLessonDetailModelList.get(ID).getImages().size()>0){
             final List<Bitmap> bitmapList = new ArrayList<>();
            for (String link : dataLessonDetailModelList.get(ID).getImages()){
                long ONE_MEGABYTE= 1024 * 1024;
                storageReference = FirebaseStorage.getInstance().getReference()
                        .child("hinhanhs").child(link);
                storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        bitmapList.add(bitmap);
                        if (bitmapList.size()>0){
                            ImagesDetailLessonAdapter imagesDetailLessonAdapter = new ImagesDetailLessonAdapter(getApplication(), R.layout.custom_layout_imagelessondetail, dataLessonDetailModel,bitmapList,true);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplication(), 1);
                            recyclerViewlistlessondetaillearn.setHasFixedSize(true);
                            recyclerViewlistlessondetaillearn.setLayoutManager(layoutManager);
                            recyclerViewlistlessondetaillearn.setAdapter(imagesDetailLessonAdapter);
                            imagesDetailLessonAdapter.notifyDataSetChanged();
                            progress.dismiss();
                        }
                    }
                });
            }
        }else {
            recyclerViewlistlessondetaillearn.setVisibility(View.GONE);
            progress.dismiss();
        }
    }

    /*Anh xa*/
    private void FindViewByID(){
        txvngonnguchinh = findViewById(R.id.txvngonnguchinh);
        txvphienam = findViewById(R.id.txvphienam);
        txvloaitu = findViewById(R.id.txvloaitu);
        txvphiendich = findViewById(R.id.txvphiendich);
        txvNumvocabulary = findViewById(R.id.txvnumvocabulary);
        textViewExam1 = findViewById(R.id.txvexample1);
        textViewExam2 = findViewById(R.id.txvexample2);
        textViewExam3 = findViewById(R.id.txvexample3);
        textViewExam4 = findViewById(R.id.txvexample4);
        textViewExam5 = findViewById(R.id.txvexample5);
        textViewExam11 = findViewById(R.id.txvexample11);
        textViewExam21 = findViewById(R.id.txvexample21);
        textViewExam31 = findViewById(R.id.txvexample31);
        textViewExam41 = findViewById(R.id.txvexample41);
        textViewExam51 = findViewById(R.id.txvexample51);
        imbtnSpeakex1 = findViewById(R.id.btnspeakex1);
        imbtnSpeakex2 = findViewById(R.id.btnspeakex2);
        imbtnSpeakex3 = findViewById(R.id.btnspeakex3);
        imbtnSpeakex4 = findViewById(R.id.btnspeakex4);
        imbtnSpeakex5 = findViewById(R.id.btnspeakex5);
        imageButtonUS = findViewById(R.id.imbtnspeakus);
        lnspeakUS = findViewById(R.id.lnspeakerus);
        recyclerViewlistlessondetaillearn = findViewById(R.id.recyclerlistlessondetaillearn);
    }
}
