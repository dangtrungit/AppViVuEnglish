package com.trunghi.dangtrung.vivulearningenglish.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Build;
import android.os.Parcelable;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.trunghi.dangtrung.vivulearningenglish.ViewActivity.ExampleDetailActivity;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListLearnDetailAdapter extends RecyclerView.Adapter<ListLearnDetailAdapter.ViewHolderLearnDetail>  {
    int resource;
    Context context;
    List<DataLessonDetailModel> dataLessonDetailModelList;
    StorageReference storageReference;
     DataLessonDetailModel dataLessonDetailModel;
     boolean check;
     boolean check2;
     TextToSpeech textToSpeech;
     boolean isloadedImage;
    public ListLearnDetailAdapter(Context context, List<DataLessonDetailModel> dataLessonDetailModelList,int resource,TextToSpeech textToSpeech,boolean isloadedImage){
        this.context =context;
        this.textToSpeech = textToSpeech;
        this.isloadedImage = isloadedImage;
        this.check=check;
        this.check2=check2;
        this.resource = resource;
        this.dataLessonDetailModelList = dataLessonDetailModelList;
    }

    public class ViewHolderLearnDetail extends RecyclerView.ViewHolder {
        TextView txvngonnguchinh,txvloaitu,txvphienam,txvphiendich,txvNumvocabulary,textViewExam1,textViewExam11,textViewExam2,textViewExam21,textViewExam3,textViewExam4,textViewExam5,textViewExam31,textViewExam41,textViewExam51;
        RecyclerView recyclerViewlistlessondetaillearn;
        LinearLayout lnspeakUk,lnspeakUS;
        ImageButton imageButtonUS,imbtnSpeakex1,imbtnSpeakex2,imbtnSpeakex3,imbtnSpeakex4,imbtnSpeakex5;
        CircleImageView cirMore;
        public ViewHolderLearnDetail(View itemView) {
            super(itemView);
            txvngonnguchinh = itemView.findViewById(R.id.txvngonnguchinh);
            txvphienam = itemView.findViewById(R.id.txvphienam);
            txvloaitu = itemView.findViewById(R.id.txvloaitu);
            txvphiendich = itemView.findViewById(R.id.txvphiendich);
            txvNumvocabulary = itemView.findViewById(R.id.txvnumvocabulary);
            textViewExam1 = itemView.findViewById(R.id.txvexample1);
            textViewExam2 = itemView.findViewById(R.id.txvexample2);
            textViewExam3 = itemView.findViewById(R.id.txvexample3);
            textViewExam4 = itemView.findViewById(R.id.txvexample4);
            textViewExam5 = itemView.findViewById(R.id.txvexample5);
            textViewExam11 = itemView.findViewById(R.id.txvexample11);
            textViewExam21 = itemView.findViewById(R.id.txvexample21);
            textViewExam31 = itemView.findViewById(R.id.txvexample31);
            textViewExam41 = itemView.findViewById(R.id.txvexample41);
            textViewExam51 = itemView.findViewById(R.id.txvexample51);
            imbtnSpeakex1 = itemView.findViewById(R.id.btnspeakex1);
            imbtnSpeakex2 = itemView.findViewById(R.id.btnspeakex2);
            imbtnSpeakex3 = itemView.findViewById(R.id.btnspeakex3);
            imbtnSpeakex4 = itemView.findViewById(R.id.btnspeakex4);
            imbtnSpeakex5 = itemView.findViewById(R.id.btnspeakex5);
            imageButtonUS = itemView.findViewById(R.id.imbtnspeakus);
            lnspeakUS = itemView.findViewById(R.id.lnspeakerus);
            recyclerViewlistlessondetaillearn = itemView.findViewById(R.id.recyclerlistlessondetaillearn);


        }

    }
    @NonNull
    @Override
    public ListLearnDetailAdapter.ViewHolderLearnDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolderLearnDetail viewHolderLearnDetail = new ViewHolderLearnDetail(view);
        return viewHolderLearnDetail;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListLearnDetailAdapter.ViewHolderLearnDetail holder, int position) {

            dataLessonDetailModel = dataLessonDetailModelList.get(position);
            final List<Bitmap> bitmapList = new ArrayList<>();
            /*Dowload Image*/
            DowloadDataImage(dataLessonDetailModel,bitmapList,holder);
            /*Text TO Speech*/
            holder.txvngonnguchinh.setText(dataLessonDetailModel.getTuvung());
            holder.txvloaitu.setText(dataLessonDetailModel.getKieuloai());
            holder.txvphienam.setText(dataLessonDetailModel.getPhienam());
            holder.txvphiendich.setText(dataLessonDetailModel.getPhiendich());

            /*get data for textview*/
            if (dataLessonDetailModel.getListexample().size()>0  && dataLessonDetailModel.getListtranslate().size()>0){
                holder.textViewExam1.setText("-"+dataLessonDetailModel.getListexample().get(0));
                holder.textViewExam11.setText(" ->"+dataLessonDetailModel.getListtranslate().get(0));
                holder.textViewExam2.setText("-"+dataLessonDetailModel.getListexample().get(1));
                holder.textViewExam21.setText(" ->"+dataLessonDetailModel.getListtranslate().get(1));
                holder.textViewExam3.setText("-"+dataLessonDetailModel.getListexample().get(2));
                holder.textViewExam31.setText(" ->"+dataLessonDetailModel.getListtranslate().get(2));
                holder.textViewExam4.setText("-"+dataLessonDetailModel.getListexample().get(3));
                holder.textViewExam41.setText(" ->"+dataLessonDetailModel.getListtranslate().get(3));
                holder.textViewExam5.setText("-"+dataLessonDetailModel.getListexample().get(4));
                holder.textViewExam51.setText(" ->"+dataLessonDetailModel.getListtranslate().get(4));
            }

            String num = String.valueOf(dataLessonDetailModel.getSotuvung());
            holder.txvNumvocabulary.setText(num);
            SettingOnClick(holder);
    }


    @Override
    public int getItemCount() {
        return dataLessonDetailModelList.size();
    }

    private void SettingOnClick(final ListLearnDetailAdapter.ViewHolderLearnDetail holder){
        final String ngonnguchinh = holder.txvngonnguchinh.getText().toString();
        final String txtex1  = holder.textViewExam1.getText().toString();
        final String txtex2  = holder.textViewExam2.getText().toString();
        final String txtex3  = holder.textViewExam3.getText().toString();
        final String txtex4  = holder.textViewExam4.getText().toString();
        final String txtex5 = holder.textViewExam5.getText().toString();

        holder.txvngonnguchinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              speak(ngonnguchinh,1.0f,0.1f);
            }
        });
        holder.imageButtonUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(ngonnguchinh,0.4f,0.1f);
            }
        });
        holder.imbtnSpeakex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(txtex1,1.0f,0.9f);
            }
        });
        holder.imbtnSpeakex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(txtex2,0.35f,0.9f);
            }
        });
        holder.imbtnSpeakex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(txtex3,1.0f,0.9f);
            }
        });
        holder.imbtnSpeakex4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(txtex4,0.35f,0.9f);
            }
        });
        holder.imbtnSpeakex5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(txtex5,1.0f,0.9f);
            }
        });

    }

    private void DowloadDataImage(final DataLessonDetailModel dataLessonDetailModel, final List<Bitmap> bitmapList, final ListLearnDetailAdapter.ViewHolderLearnDetail holder){
        if (dataLessonDetailModel.getImages().size()>0){
            for (String link : dataLessonDetailModel.getImages()){
                long ONE_MEGABYTE= 1024 * 1024;
                storageReference = FirebaseStorage.getInstance().getReference()
                        .child("hinhanhs").child(link);
                storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap =BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        bitmapList.add(bitmap);
                        if (bitmapList.size()==dataLessonDetailModel.getImages().size() && bitmapList.size()>0){
                            ImagesDetailLessonAdapter imagesDetailLessonAdapter = new ImagesDetailLessonAdapter(context, R.layout.custom_layout_imagelessondetail, dataLessonDetailModel,bitmapList,false);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,1);
                            holder.recyclerViewlistlessondetaillearn.setHasFixedSize(true);
                            holder.recyclerViewlistlessondetaillearn.setLayoutManager(layoutManager);
                            holder.recyclerViewlistlessondetaillearn.setAdapter(imagesDetailLessonAdapter);
                            imagesDetailLessonAdapter.notifyDataSetChanged();
                            isloadedImage=true;
                        }
                    }
                });
            }
        }else {
            holder.recyclerViewlistlessondetaillearn.setVisibility(View.GONE);
        }
    }

    private void TextToSpeechClickListener(final ListLearnDetailAdapter.ViewHolderLearnDetail holder, final Locale locale){
//        holder.textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status!= holder.textToSpeech.ERROR){
//                    holder.textToSpeech.setLanguage(locale);
//                }else {
//                    holder.textToSpeech.shutdown();
//                    holder.textToSpeech.stop();
//                }
//
//            }
//        });

    }

    private void speak(final String string, float setpitch,float speech){
        textToSpeech.setPitch(setpitch);
        textToSpeech.setSpeechRate(speech);
        textToSpeech.speak(string,TextToSpeech.QUEUE_FLUSH,null);
    }

}

