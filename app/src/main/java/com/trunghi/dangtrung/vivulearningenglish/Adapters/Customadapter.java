package com.trunghi.dangtrung.vivulearningenglish.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trunghi.dangtrung.vivulearningenglish.Models.DataLearnModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.trunghi.dangtrung.vivulearningenglish.ViewActivity.ListLessonDetailLearnActivity;
import com.trunghi.dangtrung.vivulearningenglish.ViewActivity.PracticeActivity;

import java.util.List;

public class Customadapter extends BaseAdapter {

    public Customadapter(Context context, List<DataLearnModel> dataLearnModelList,int resource,boolean check) {
        this.context = context;
        this.dataLearnModelList = dataLearnModelList;
        this.resource = resource;
        this.check= check;
    }
    public void AddListItemAdapter(List<DataLearnModel> itemplus){
        dataLearnModelList.addAll(itemplus);
        this.notifyDataSetChanged();
    }

    Context context;
    List<DataLearnModel> dataLearnModelList;
    int resource;
    boolean check;
    @Override
    public int getCount() {
        return dataLearnModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataLearnModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(resource,null);

        TextView textViewlesson = view.findViewById(R.id.txvlesson);
        LinearLayout linearLayout = view.findViewById(R.id.lncontainerlearn);
        Button buttonOntap = view.findViewById(R.id.btnontap);
        ImageView imageView = view.findViewById(R.id.imbook);
        TextView txvid = view.findViewById(R.id.txvid);
        final DataLearnModel dataLearnModel = dataLearnModelList.get(position);

        textViewlesson.setText(dataLearnModel.getTenlesson());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itlistdetail = new Intent(context, ListLessonDetailLearnActivity.class);
                itlistdetail.putExtra("lesson", dataLearnModel);
                itlistdetail.putExtra("title", dataLearnModel.getTenlesson());
                context.startActivity(itlistdetail);
            }
        });
        if (!check){
            buttonOntap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PracticeActivity.class);
                    intent.putExtra("datatopractice",dataLearnModel);
                    context.startActivity(intent);

                }
            });


        }else {
            buttonOntap.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}
