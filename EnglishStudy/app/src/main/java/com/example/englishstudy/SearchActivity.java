package com.example.englishstudy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private int readcount;
    private String question[];
    private String answer[];
    private Button btn_input, btn_change, btn_delete;
    private ArrayList<QnA> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        question = intent.getStringArrayExtra("questionarray");
        answer = intent.getStringArrayExtra("answerarray");
        readcount = intent.getIntExtra("readcount", 1);

        final ListView listView = (ListView) findViewById(R.id.lv_search);

        //이렇게 하면 리스트뷰의 테마가 정해짐. 커스텀이 안됨.
        /*arrayList = new ArrayList<QnA>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, arrayList);
        listView.setAdapter(adapter);*/

        final List<QnA> qna = new ArrayList<>();

        for(int i = 0; i<readcount; i++)
            qna.add(new QnA(question[i], answer[i], null));
        /*qna.add(new QnA("1번문", "1번답", null));
        qna.add(new QnA("2번문", "2번답", null));
        qna.add(new QnA("3번문", "3번답", null));
        qna.add(new QnA("4번문", "4번답", null));*/

        final ListAdapter listAdapter = new ListAdapter(this, qna, listView);

        listView.setAdapter(listAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        btn_input = (Button)findViewById(R.id.search_btn_input);
        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = getLayoutInflater().inflate(R.layout.input_dialog, null);
                final EditText et_question = (EditText)dialogView.findViewById(R.id.dg_et_question);
                final EditText et_answer = (EditText)dialogView.findViewById(R.id.dg_et_answer);
                AlertDialog.Builder ad = new AlertDialog.Builder(SearchActivity.this);
                ad.setView(dialogView);
                //ad.setIcon(R.mipmap.ic_launcher);
                //ad.setTitle("문제를 입력하세요");

                //ad.setMessage("문제를 입력하세요2");

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String result = et.getText().toString();
                        String str1 = et_question.getText().toString();
                        String str2 = et_answer.getText().toString();
                        if(!str1.isEmpty())
                        {
                            qna.add(new QnA(str1, str2, null));
                            listAdapter.notifyDataSetChanged();
                        }
                        dialogInterface.dismiss();
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = ad.create();
                ad.show();
            }
        });

        btn_delete = (Button)findViewById(R.id.search_btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = listView.getCheckedItemPosition();
                if(pos != ListView.INVALID_POSITION)
                {
                    qna.remove(pos);
                    listView.clearChoices();
                    listAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
