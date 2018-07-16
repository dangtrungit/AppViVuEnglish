package com.trunghi.dangtrung.vivulearningenglish.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trunghi.dangtrung.vivulearningenglish.R;

public class MethodLearnFragment extends Fragment {
    private final String SHOWTEXTMETHOD = "Phương pháp học lặp cách đoạn là phương pháp học lặp đi lặp lại ngẫu nhiên các từ kết hợp với phương châm bí kíp truyền đời \"chia để trị\"." +
            "Đây là 1 trong cách học cơ bản tương đối hiệu quả, và cách này đối với một số bạn cũng có thể không mang lại hiệu quả.\n" +
            "Nhưng dù cách học nào đi nữa thì bạn luôn phải là một người chủ động trong việc học.";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_methodlearn,container,false);
        TextView txvShow = view.findViewById(R.id.txvshowmethod);
        txvShow.setText(SHOWTEXTMETHOD);
        return view;
    }
}
