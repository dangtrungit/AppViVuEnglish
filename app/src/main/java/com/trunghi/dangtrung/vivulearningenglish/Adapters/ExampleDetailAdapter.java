package com.trunghi.dangtrung.vivulearningenglish.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;

import java.util.List;

public class ExampleDetailAdapter extends RecyclerView.Adapter<ExampleDetailAdapter.ViewHolderExampleDetail> {
    Context context;
    int resource;
    DataLessonDetailModel dataLessonDetailModel;
    boolean check;
    int iD;
    List<DataLessonDetailModel> dataLessonDetailModelList;
    public ExampleDetailAdapter(Context context, int resource,DataLessonDetailModel dataLessonDetailModel,boolean check){
        this.context = context;
        this.resource = resource;
        this.dataLessonDetailModel  = dataLessonDetailModel;
        this.dataLessonDetailModelList = dataLessonDetailModelList;
        this.check = check;
        this.iD=iD;

    }
    public class ViewHolderExampleDetail extends RecyclerView.ViewHolder {
        TextView textViewExam,textViewTrans;
        public ViewHolderExampleDetail(View itemView) {
            super(itemView);
            textViewExam = itemView.findViewById(R.id.txvexamdetail);
            textViewTrans = itemView.findViewById(R.id.txvtransdetail);
        }
    }

    @NonNull
    @Override
    public ExampleDetailAdapter.ViewHolderExampleDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolderExampleDetail viewHolderImagesDetail = new ViewHolderExampleDetail(view);
        return viewHolderImagesDetail;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleDetailAdapter.ViewHolderExampleDetail holder, int position) {
//              Log.d("ajijij",dataLess/onDetailModel.getListexample().size()+"");
        if (dataLessonDetailModel.getListexample().size()>0&& dataLessonDetailModel.getListtranslate().size()>0) {
            holder.textViewExam.setText(dataLessonDetailModel.getListexample().toString());
            holder.textViewTrans.setText(dataLessonDetailModel.getListtranslate().toString());
        }
    }

    @Override
    public int getItemCount() {
        return dataLessonDetailModel.getListexample().size();
    }


}
