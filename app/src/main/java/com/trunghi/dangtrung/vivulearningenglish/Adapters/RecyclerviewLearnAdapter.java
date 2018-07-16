package com.trunghi.dangtrung.vivulearningenglish.Adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trunghi.dangtrung.vivulearningenglish.Controllers.Interfaces.ILoadMore;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLearnModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.trunghi.dangtrung.vivulearningenglish.ViewActivity.ListLessonDetailLearnActivity;
import com.trunghi.dangtrung.vivulearningenglish.ViewActivity.PracticeActivity;


import java.util.List;

public class RecyclerviewLearnAdapter extends RecyclerView.Adapter<RecyclerviewLearnAdapter.ViewHolderLearn> {
    Context context;
    int resource;
    List<DataLearnModel> dataLearnModelList;
    boolean check;
    boolean checkpass;
    int lesson;
    int id;
    int code;
    /*=-----*/
    private final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    ILoadMore loadMore;
    boolean isLoading;
    Activity activity;
    int visibleThreshold=5;
    int lastVisibleItem,totalItemCount;

    public RecyclerviewLearnAdapter(Context context, List<DataLearnModel> dataEnglishModels, int resource, boolean check){
        this.context =context;
        this.check = check;
        this.checkpass = checkpass;
        this.dataLearnModelList = dataEnglishModels;
        this.resource=resource;
        this.lesson=lesson;
        this.id=id;
        this.code=code;



    }
    public class ViewHolderLearn extends RecyclerView.ViewHolder {
        TextView textViewlesson,txvid;
        LinearLayout linearLayout;
        Button buttonOntap;
        ImageView imageView;
        /*----*/

        public ViewHolderLearn(View itemView) {
            super(itemView);
            textViewlesson = itemView.findViewById(R.id.txvlesson);
            linearLayout = itemView.findViewById(R.id.lncontainerlearn);
            buttonOntap = itemView.findViewById(R.id.btnontap);
            imageView = itemView.findViewById(R.id.imbook);
            txvid = itemView.findViewById(R.id.txvid);

        }
    }

    @NonNull
    @Override
    public RecyclerviewLearnAdapter.ViewHolderLearn onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolderLearn viewHolderData = new ViewHolderLearn(v);
        return viewHolderData;
    }
    Context mContext;
    @Override
    public void onBindViewHolder(@NonNull RecyclerviewLearnAdapter.ViewHolderLearn holder, int position) {
        final DataLearnModel dataLearnModel= dataLearnModelList.get(position);
        holder.textViewlesson.setText(dataLearnModel.getTenlesson());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itlistdetail = new Intent(context, ListLessonDetailLearnActivity.class);
                itlistdetail.putExtra("lesson", dataLearnModel);
                itlistdetail.putExtra("title", dataLearnModel.getTenlesson());
                context.startActivity(itlistdetail);
            }
        });
        if (!check){
            holder.buttonOntap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PracticeActivity.class);
                    intent.putExtra("datatopractice",dataLearnModel);
                    context.startActivity(intent);

                }
            });


        }else {
            holder.buttonOntap.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dataLearnModelList.size();
    }
}
