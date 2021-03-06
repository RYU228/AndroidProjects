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
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_eng;
    private Button btn_main_input, btn_main_search;
    private Button btn_main_pre, btn_main_next;
    private Intent intent;
    final static int REQUEST_INTENT = 0;
    final static int RESULT_OK = 1;
    final static int RESULT_CANCEL = 2;
    private int readcount = 0;
    private int curcount = 0;
    private ArrayList<QnA> qna;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qna = new ArrayList();

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

        if(qna.size() == 0)
        {
            tv_eng.setText("문제를 입력해주세요.");
        }
        else
        {
            tv_eng.setText(qna.get(0).getQuestion());
        }

        tv_eng.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int action = motionEvent.getAction();

                if(MotionEvent.ACTION_DOWN == action)
                {
                    tv_eng.setText(qna.get(curcount).getAnswer());
                }else if(MotionEvent.ACTION_UP == action)
                {
                    tv_eng.setText(qna.get(curcount).getQuestion());
                }

                return true;
            }
        });

        //조회 버튼
        btn_main_search = (Button)findViewById(R.id.btn_main_search);

        btn_main_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("QnA", qna);
                intent.putExtra("readcount", readcount);
                startActivityForResult(intent, REQUEST_INTENT);
                //startActivity(intent);
            }
        });

        btn_main_pre = (Button)findViewById(R.id.btn_main_pre);
        btn_main_next = (Button)findViewById(R.id.btn_main_next);

        btn_main_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curcount < 1)
                    curcount = 0;
                else
                    curcount--;
                tv_eng.setText(qna.get(curcount).getQuestion());
            }
        });
        btn_main_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curcount >= readcount-1)
                    curcount = readcount-1;
                else
                    curcount++;
                tv_eng.setText(qna.get(curcount).getQuestion());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_INTENT && resultCode == RESULT_OK)
        {
            qna.clear();
            qna = (ArrayList<QnA>)data.getSerializableExtra("QnA2");
            readcount = data.getIntExtra("readcount1", 1);
        }
    }

    public void DataRead()
    {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        File file = new File(getFilesDir(), "QnA.txt");
        ArrayList qna2;

        try
        {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            qna = (ArrayList)ois.readObject();
            ois.close();
            fis.close();
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
