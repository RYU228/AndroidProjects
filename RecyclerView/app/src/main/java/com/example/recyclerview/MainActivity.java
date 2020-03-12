package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Profile> arrayList;
    private MainAdapter mainadapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        mainadapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainadapter);

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile maindata = new Profile(R.mipmap.ic_launcher, "이름", "내용");
                arrayList.add(maindata);
                mainadapter.notifyDataSetChanged();
            }
        });
    }
}