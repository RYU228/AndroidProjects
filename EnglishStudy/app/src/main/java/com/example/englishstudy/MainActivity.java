package com.example.englishstudy;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_eng;
    private Button btn_main_input, btn_main_search;
    private Button btn_main_pre, btn_main_next;
    private Intent intent;
    final static int REQUEST_INTENT = 0;
    final static int RESULT_OK = 1;
    final static int RESULT_CANCEL = 2;
    private String question[];
    private String answer[];
    private int readcount = 0;
    private int curcount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = new String[20];
        answer = new String[20];

        File init_file = new File(getFilesDir(), "DataCount.txt");
        File data_file = new File(getFilesDir(), "QnA.txt");

        if(data_file.exists())
        {
            //data_file.delete();
            DataRead();
        }


        if(init_file.exists())
        {
            //init_file.delete();
            CountRead();
        }

        tv_eng = (TextView)findViewById(R.id.tv_eng);

        if(question[0] == null)
        {
            tv_eng.setText("문제를 입력해주세요.");
        }
        else
        {
            tv_eng.setText(question[0]);
        }

        tv_eng.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int action = motionEvent.getAction();

                if(MotionEvent.ACTION_DOWN == action)
                {
                    tv_eng.setText(answer[curcount]);
                }else if(MotionEvent.ACTION_UP == action)
                {
                    tv_eng.setText(question[curcount]);
                }

                return true;
            }
        });

        btn_main_input = (Button)findViewById(R.id.btn_main_input);

        btn_main_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, InputDataActivity.class);
                //intent.putExtra("question")
                startActivityForResult(intent, REQUEST_INTENT);
            }
        });


        //조회 버튼
        btn_main_search = (Button)findViewById(R.id.btn_main_search);

        btn_main_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("questionarray", question);
                intent.putExtra("answerarray", answer);
                intent.putExtra("readcount", readcount);
                startActivity(intent);
                /*File init_file = new File(getFilesDir(), "DataCount.txt");
                File data_file = new File(getFilesDir(), "QnA.txt");

                if(data_file.exists())
                {
                    data_file.delete();
                }


                if(init_file.exists())
                {
                    init_file.delete();
                }
                readcount = 0;*/
            }
        });

        btn_main_pre = (Button)findViewById(R.id.btn_main_pre);
        btn_main_next = (Button)findViewById(R.id.btn_main_next);

        btn_main_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File init_file = new File(getFilesDir(), "DataCount.txt");
                File data_file = new File(getFilesDir(), "QnA.txt");

                if(data_file.exists())
                {
                    data_file.delete();
                }


                if(init_file.exists())
                {
                    init_file.delete();
                }
                readcount = 0;
                /*if(curcount < 1)
                    curcount = 0;
                else
                    curcount--;
                tv_eng.setText(question[curcount]);*/
            }
        });
        btn_main_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curcount >= readcount)
                    curcount = readcount;
                else
                    curcount++;
                tv_eng.setText(question[curcount]);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_INTENT && resultCode == RESULT_OK)
        {
            question[readcount] = data.getStringExtra("question1");
            answer[readcount] = data.getStringExtra("answer1");
            readcount++;
            //tv_eng.setText(question[0]);
        }
    }

    public void DataRead()
    {
        File file = new File(getFilesDir(), "QnA.txt");
        FileReader fr = null;
        BufferedReader bufrd = null;

        try
        {
            fr = new FileReader(file);
            bufrd = new BufferedReader(fr);

            for(int i = 0; ; i++)
            {
                question[i] = bufrd.readLine();
                answer[i] = bufrd.readLine();

                if(question[i] == null || answer[i] == null)
                {
                    question[i] = null;
                    answer[i] = null;

                    readcount = i;
                    break;
                }
            }

            if(bufrd != null) bufrd.close();
            if(fr != null) fr.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        CountWrite();
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

    public void CountRead()
    {
        File file = null;
        String filename = "DataCount.txt";
        FileReader fr = null;
        BufferedReader bufrd = null;

        file = new File(getFilesDir(), filename);

        try
        {
            fr = new FileReader(file);
            bufrd = new BufferedReader(fr);

            String str = bufrd.readLine();
            readcount = Integer.parseInt(str);

            if(bufrd != null) bufrd.close();
            if(fr != null) fr.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
