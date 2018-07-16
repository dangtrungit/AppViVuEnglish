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

public class GuideLearnFragment extends Fragment {
    private final String SHOWTEXTGUIDE = "Tiếng anh là ngôn ngữ chung của toàn thế giới thế nên là bạn hãy học đi nha :)) nó thật sự rất hữu ích đó ngheng." +
            " Cách học tiếng anh thì sẽ phụ thuộc vào mục đích sử dụng tiếng anh của mỗi người nhưng mục đích chung nhất là để giao tiếp." +
            " Học tiếng anh hay bất kì ngôn ngữ nào là phải trải qua quá trình tu luyện gian khổ.\n" +
            " Nếu như các bạn đã sẵn sàng tu luyện nó thì ứng dụng này sẽ giúp bạn học những từ vựng căn bản nhất giúp bạn phát triển các level tiếp theo của tiếng anh.\n" +
            " Ứng dụng chia các từ vựng thành nhiều lesson để giúp bạn nắm vững kiến thức cũng như tránh việc học trở nên chán nản, kèm theo là các hình ảnh, ví dụ câu tương ứng giúp bạn ghi nhớ nhanh và hiệu quả hơn.\n" +
            " Bạn hãy nên duy trì học tiếng anh hằng ngày khoảng 1->2(h) và tối thiểu 30', nếu trong 2h bạn học hiệu quả khoảng 70 % thời gian trở lên thì chẳng mấy chốc bạn sẽ pro thôi ^^.\n" +
            " Một điều nữa tôi muốn gửi gắm đến các bạn hãy xác định mục tiêu rõ ràng, không giới hạn phương pháp học -có phương pháp mới thì bạn hãy chiến ngay, để tìm ra phương pháp hiệu quả với bạn.\n" +
            " Túm cái váy lại:\n "+
            " \"Hãy luyện tập, thử thách, không ngại khó.\"";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_guidelearn,container,false);
        TextView textView = view.findViewById(R.id.showguideandintro);
        textView.setText(SHOWTEXTGUIDE);
        return view;
    }
}
