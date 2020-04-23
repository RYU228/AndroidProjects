package com.example.englishstudy;

import android.content.Context;
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
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private int readcount;
    private String question[];
    private String answer[];
    private Button btn_input, btn_change, btn_delete;
    private ArrayList<QnA> qna;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent();

        intent.putExtra("QnA", qna);
//        intent.putExtra("question1", question);
//        intent.putExtra("answer1", answer);
        intent.putExtra("readcount1", 10);
        setResult(1, intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Intent intent = new Intent();
//
//        intent.putExtra("question1", question);
//        intent.putExtra("answer1", answer);
//        intent.putExtra("readcount1", 10);
//        setResult(1, intent);
//        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Intent intent = new Intent();
//
//        intent.putExtra("question1", question);
//        intent.putExtra("answer1", answer);
//        intent.putExtra("readcount1", 10);
//        setResult(1, intent);
//        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        qna = new ArrayList();

        Intent intent = getIntent();
        //qna = (ArrayList<QnA>)intent.getSerializableExtra("QnA");
//        question = intent.getStringArrayExtra("questionarray");
//        answer = intent.getStringArrayExtra("answerarray");
        readcount = intent.getIntExtra("readcount", 1);

        final ListView listView = (ListView) findViewById(R.id.lv_search);

        //이렇게 하면 리스트뷰의 테마가 정해짐. 커스텀이 안됨.
        /*arrayList = new ArrayList<QnA>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, arrayList);
        listView.setAdapter(adapter);*/

        //final List<QnA> qna = new ArrayList<>();

//        for(int i = 0; i<readcount; i++)
//            qna.add(new QnA(question[i], answer[i], null));

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

                        if(!str1.isEmpty() && !str2.isEmpty())
                        {
                            qna.add(new QnA(str1, str2, null));
                            listAdapter.notifyDataSetChanged();

                            try
                            {
                                String filename = "QnA.txt";
                                File file = new File(getFilesDir(), filename);
                                FileOutputStream fos = new FileOutputStream(file);
                                ObjectOutputStream oos = new ObjectOutputStream(fos);
                                oos.writeObject(qna);
                                oos.close();
                                fos.close();
                            } catch (Exception e)
                            {
                                e.printStackTrace();
                            }
//                            String filename = "QnA.txt";
//                            File file = new File(getFilesDir(), filename);
//                            FileWriter fw = null;
//                            BufferedWriter bufwr = null;
//
//                            try
//                            {
//                                fw = new FileWriter(file, true);
//                                bufwr = new BufferedWriter(fw);
//                                bufwr.write(str1);
//                                bufwr.newLine();
//                                bufwr.write(str2);
//                                bufwr.newLine();
//
//                                if(bufwr != null)
//                                    bufwr.close();
//                                if(fw != null)
//                                    fw.close();
//                            } catch(Exception e)
//                            {
//                                e.printStackTrace();
//                            }

                            readcount++;
                            File init_file = new File(getFilesDir(), "DataCount.txt");
                            if(init_file.exists())
                            {
                                init_file.delete();
                                CountWrite();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "문제와 정답을 입력해주세요.", Toast.LENGTH_SHORT).show();
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
//                int pos = listView.getCheckedItemPosition();
//                if(pos != ListView.INVALID_POSITION)
//                {
//                    qna.remove(pos);
//                    listView.clearChoices();
//                    listAdapter.notifyDataSetChanged();
//                }
//                File init_file = new File(getFilesDir(), "DataCount.txt");
//                File data_file = new File(getFilesDir(), "QnA.txt");
//
//                if(data_file.exists())
//                {
//                    data_file.delete();
//                }
//
//                if(init_file.exists())
//                {
//                    init_file.delete();
//                }
//                qna.clear();
                qna.clear();


                FileInputStream fis = null;
                ObjectInputStream ois = null;
                File file = new File(getFilesDir(), "QnA.txt");
                ArrayList qna2;

                try
                {
                    fis = new FileInputStream(file);
                    ois = new ObjectInputStream(fis);
                    qna = (ArrayList)ois.readObject();
                    //qna = readedObject;
                    ois.close();
                    fis.close();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public void CountWrite()
    {
        File file = null;
        String filename = "DataCount.txt";
        FileWriter fw = null;
        BufferedWriter bufwr = null;

        file = new File(getFilesDir(), filename);

        try
        {
            fw = new FileWriter(file);
            bufwr = new BufferedWriter(fw);
            String str = Integer.toString(readcount);
            bufwr.write(str);

            if(bufwr != null) bufwr.close();
            if(fw != null) fw.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
