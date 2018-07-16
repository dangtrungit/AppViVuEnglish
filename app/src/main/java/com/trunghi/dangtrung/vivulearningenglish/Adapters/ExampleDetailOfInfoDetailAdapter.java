package com.trunghi.dangtrung.vivulearningenglish.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.trunghi.dangtrung.vivulearningenglish.ViewActivity.ExampleDetailOfInfoDetailActivity;

import java.util.List;

public class ExampleDetailOfInfoDetailAdapter extends RecyclerView.Adapter<ExampleDetailOfInfoDetailAdapter.ViewHolerExamDetailOfInfoDetail> {

    Context context;
    int resource;
    List<DataLessonDetailModel> dataLessonDetailModelList;
    int iD;
    public ExampleDetailOfInfoDetailAdapter(Context context, int resource, List<DataLessonDetailModel> dataLessonDetailModelList,int iD){
        this.iD=iD;
        this.context = context;
        this.resource=resource;
        this.dataLessonDetailModelList=dataLessonDetailModelList;
    }

    public class ViewHolerExamDetailOfInfoDetail extends RecyclerView.ViewHolder {
        TextView textViewExam,textViewTrans;
        public ViewHolerExamDetailOfInfoDetail(View itemView) {
            super(itemView);
            textViewExam = itemView.findViewById(R.id.txvexamdetail);
            textViewTrans = itemView.findViewById(R.id.txvtransdetail);
        }
    }
    @NonNull
    @Override
    public ExampleDetailOfInfoDetailAdapter.ViewHolerExamDetailOfInfoDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolerExamDetailOfInfoDetail viewHolerExamDetailOfInfoDetail = new ViewHolerExamDetailOfInfoDetail(view);
        return viewHolerExamDetailOfInfoDetail;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleDetailOfInfoDetailAdapter.ViewHolerExamDetailOfInfoDetail holder, int position) {
        if (dataLessonDetailModelList.get(iD).getListexample().size() == dataLessonDetailModelList.get(iD).getListtranslate().size()) {
            holder.textViewTrans.setText(dataLessonDetailModelList.get(iD).getListtranslate().get(position));
            holder.textViewExam.setText(dataLessonDetailModelList.get(iD).getListexample().get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dataLessonDetailModelList.get(iD).getListexample().size();
    }

}
