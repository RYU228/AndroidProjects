package com.example.englishstudy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static com.example.englishstudy.MainActivity.RESULT_CANCEL;

public class InputDataActivity extends AppCompatActivity {
    private EditText et_question;
    private EditText et_answer;
    private Button btn_input;
    private Button btn_cancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        et_question = (EditText)findViewById(R.id.et_question);
        et_answer = (EditText)findViewById(R.id.et_answer);

        et_question.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        btn_input = (Button)findViewById(R.id.btn_input);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = et_question.getText().toString();
                String temp2 = et_answer.getText().toString();
                boolean con1 = false;
                boolean con2 = false;

                if(temp.length() != 0 && temp2.length() != 0) con1 = true;
                if(temp != " " && temp2 != " ") con2 = true;

                if(con1 && con2)
                {
                    String question = et_question.getText().toString();
                    String answer = et_answer.getText().toString();

                    String filename = "QnA.txt";
                    File file = new File(getFilesDir(), filename);
                    FileWriter fw = null;
                    BufferedWriter bufwr = null;

                    try
                    {
                        fw = new FileWriter(file, true);
                        bufwr = new BufferedWriter(fw);
                        bufwr.write(question);
                        bufwr.newLine();
                        bufwr.write(answer);
                        bufwr.newLine();

                        if(bufwr != null)
                            bufwr.close();
                        if(fw != null)
                            fw.close();
                    } catch(Exception e)
                    {
                        e.printStackTrace();
                    }


                    Intent intent = new Intent();

                    intent.putExtra("question1", question);
                    intent.putExtra("answer1", answer);
                    setResult(1, intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "문제와 정답을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCEL);
                finish();
            }
        });
    }
}
