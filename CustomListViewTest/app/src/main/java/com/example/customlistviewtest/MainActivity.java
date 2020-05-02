package com.example.customlistviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview;
        ListAdapter adapter;

        // Adapter 생성
        adapter = new ListAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("Account Box Black 36dp") ;
        // 두 번째 아이템 추가.
        adapter.addItem("Account Circle Black 36dp") ;
        // 세 번째 아이템 추가.
        adapter.addItem("Assignment Ind Black 36dp") ;
    }
}
