package com.trunghi.dangtrung.vivulearningenglish.Controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.trunghi.dangtrung.vivulearningenglish.Adapters.Customadapter;
import com.trunghi.dangtrung.vivulearningenglish.Adapters.ListLearnDetailAdapter;
import com.trunghi.dangtrung.vivulearningenglish.Adapters.RecyclerviewLearnAdapter;
import com.trunghi.dangtrung.vivulearningenglish.Controllers.Interfaces.DetailLearnInterface;
import com.trunghi.dangtrung.vivulearningenglish.Controllers.Interfaces.ILoadMore;
import com.trunghi.dangtrung.vivulearningenglish.Controllers.Interfaces.LearnInterfaces;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLearnModel;
import com.trunghi.dangtrung.vivulearningenglish.Models.DataLessonDetailModel;
import com.trunghi.dangtrung.vivulearningenglish.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class LearnController {
    Context context ;
    DataLearnModel dataLearnModel;
    Customadapter customadapter;
    RecyclerviewLearnAdapter recyclerviewLearnAdapter;
    DatabaseReference databaseReference;
    List<DataLearnModel> dataLearnModelList;
    boolean check;
    int lesson;
    int id;
    int code;
    ListView listView;
    boolean isLoading;
    ProgressDialog progressDialog;


    public  int currentID =0;
    public LearnController(Context context, boolean check, boolean isLoading, ProgressDialog progressDialog){
        this.context = context;
        this.check = check;
        this.progressDialog=progressDialog;
        this.isLoading = isLoading;
        dataLearnModel = new DataLearnModel();
        dataLearnModelList = new ArrayList<>();

//        this.checkpass =checkpass;
//        this.lesson=lesson;
//        this.id=id;
//        this.code=code;

    }

    public void GetDataLessonForViewLearn(RecyclerView recyclerView){

//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        footerview = layoutInflater.inflate(R.layout.footer_view,null);
//        customadapter = new Customadapter(context,dataLearnModelList,R.layout.custom_layout_recyclerview_learn,check);
//        listView.setAdapter(customadapter);

//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                if (absListView.getLastVisiblePosition()==totalItemCount-1 && isLoading==false){
//                   isLoading=true;
//                   Thread thread =new ThreadData();
//                   thread.start();
//
//                }
//            }
//        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerviewLearnAdapter = new RecyclerviewLearnAdapter(context,dataLearnModelList, R.layout.custom_layout_recyclerview_learn,check);
        recyclerView.setAdapter(recyclerviewLearnAdapter);
        LearnInterfaces learnInterfaces = new LearnInterfaces() {
            @Override
            public void onGetDataFirebase(DataLearnModel dataLearnModel) {
                    dataLearnModelList.add(dataLearnModel);
                    recyclerviewLearnAdapter.notifyDataSetChanged();
                    isLoading=true;
                    progressDialog.dismiss();
            }
        };
        GetDataFirebase(learnInterfaces);

    }

//    public class mHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 0:
//                    listView.addFooterView(footerview);
//                    break;
//                case 1:
//                    listView.removeFooterView(footerview);
//                    customadapter.AddListItemAdapter((List<DataLearnModel>) msg.obj);
//                    isLoading=false;
//                    break;
//            }
//        }
//    }
//
//
    public void GetDataFirebase(final LearnInterfaces learnInterfaces){

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.keepSynced(true);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshot1 = dataSnapshot.child("keylesson");
                for (DataSnapshot dataSnapshot11 :dataSnapshot1.getChildren()){
                    DataLearnModel dataLearnModel = dataSnapshot11.getValue(DataLearnModel.class);
                    dataLearnModel.setLesson(dataSnapshot11.getKey());

                    learnInterfaces.onGetDataFirebase(dataLearnModel);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);

    }
//
//    public class ThreadData extends Thread{
//        @Override
//        public void run() {
//            mHandler.sendEmptyMessage(0);
//            List<DataLearnModel> dataLearnModels =getMoreData();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Message message = mHandler.obtainMessage(1,dataLearnModels);
//            mHandler.sendMessage(message);
//        }
//    }
//
//    public List<DataLearnModel> getMoreData(){
//        List<DataLearnModel> dataLearnModelList=new ArrayList<>();
//        dataLearnModelList.add(dataLearnModel);
//        return dataLearnModelList;
//    }
}
