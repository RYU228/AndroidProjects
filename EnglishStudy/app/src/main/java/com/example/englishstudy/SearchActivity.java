package com.example.englishstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private int readcount;
    private String question[];
    private String answer[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        question = intent.getStringArrayExtra("questionarray");
        answer = intent.getStringArrayExtra("answerarray");

        final ListView listView = (ListView) findViewById(R.id.lv_search);

        final List<QnA> qna = new ArrayList<>();

        for(int i = 0; i<question.length; i++)
            qna.add(new QnA(question[i], answer[i], null));
        /*qna.add(new QnA("1번문", "1번답", null));
        qna.add(new QnA("2번문", "2번답", null));
        qna.add(new QnA("3번문", "3번답", null));
        qna.add(new QnA("4번문", "4번답", null));*/

        final ListAdapter listAdapter = new ListAdapter(this, qna, listView);

        listView.setAdapter(listAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }
}
