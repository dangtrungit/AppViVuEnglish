package com.trunghi.dangtrung.vivulearningenglish.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.trunghi.dangtrung.vivulearningenglish.ViewActivity.ImageDetailActivity;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagesDetailLessonAdapter extends RecyclerView.Adapter<ImagesDetailLessonAdapter.ViewHolderImagesDetail> {

    Context context;
    int resource;
    DataLessonDetailModel dataLessonDetailModel;
    StorageReference storageReference;
    Bitmap bitmap;
    List<Bitmap> bitmapList;
    List<DataLessonDetailModel> dataLessonDetailModelList;
    boolean isImageDetail;
   int CODE_QUEST = 001;

    public ImagesDetailLessonAdapter(Context context, int resource,DataLessonDetailModel dataLessonDetailModel,List<Bitmap> bitmapList,boolean isImageDetail){
        this.context = context;
        this.resource = resource;
        this.dataLessonDetailModel  = dataLessonDetailModel;
        this.bitmapList = bitmapList;
        this.isImageDetail = isImageDetail;
        this.CODE_QUEST = CODE_QUEST;
    }

    public class ViewHolderImagesDetail extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txvnumimage;
        FrameLayout frkhunghinh,frContainer;

        public ViewHolderImagesDetail(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagedetaillesson);
            txvnumimage = itemView.findViewById(R.id.txvsohinh);
            frkhunghinh = itemView.findViewById(R.id.khungsohinh);
            frContainer = itemView.findViewById(R.id.frcontainer);
        }
    }
    @NonNull
    @Override
    public ImagesDetailLessonAdapter.ViewHolderImagesDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolderImagesDetail viewHolderImagesDetail = new ViewHolderImagesDetail(view);
        return viewHolderImagesDetail;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImagesDetailLessonAdapter.ViewHolderImagesDetail holder, int position) {
        holder.imageView.setImageBitmap(bitmapList.get(position));
        Log.d("bitmap",bitmapList.size()+"");
         if (!isImageDetail ){
             if (position==0){
                 holder.imageView.setImageBitmap(bitmapList.get(position));
                 int numimagegain = bitmapList.size()-1;
                 if (numimagegain>0){
                     holder.frkhunghinh.setVisibility(View.VISIBLE);
                     holder.txvnumimage.setText("+" + numimagegain);
                     holder.imageView.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Intent itdetailimage = new Intent(context, ImageDetailActivity.class);
                             itdetailimage.putExtra("datadetailimage", dataLessonDetailModel);
                             context.startActivity(itdetailimage);
                         }
                     });
                 }
             }
         }


      }

    @Override
    public int getItemCount() {
            return bitmapList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
