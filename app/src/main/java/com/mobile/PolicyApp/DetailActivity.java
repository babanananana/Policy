package com.mobile.PolicyApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private com.mobile.PolicyApp.DetailAdapter detailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getData();
    }
    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        detailAdapter = new com.mobile.PolicyApp.DetailAdapter();
        recyclerView.setAdapter(detailAdapter);
    }
    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("정책1", "정책2", "정책3", "정책4", "정책5", "정책6", "정책7", "정책8"
        );
        List<String> listContent = Arrays.asList(
                "요약",
                "요약",
                "요약",
                "요약",
                "요약",
                "요약",
                "요약",
                "요약"
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            com.mobile.PolicyApp.DetailData detailData = new com.mobile.PolicyApp.DetailData();
            detailData.setTitle(listTitle.get(i));
            detailData.setContent(listContent.get(i));
            //data.setResId(listResId.get(i));
            // 각 값이 들어간 data를 adapter에 추가합니다.
            detailAdapter.addItem(detailData);
        }
        // adapter의 값이 변경되었다는 것을 알려줍니다.
        detailAdapter.notifyDataSetChanged();
    }
}