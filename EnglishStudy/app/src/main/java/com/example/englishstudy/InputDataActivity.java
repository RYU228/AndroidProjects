package com.example.englishstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        btn_input = (Button)findViewById(R.id.btn_input);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = et_question.getText().toString();
                String temp2 = et_answer.getText().toString();
                boolean con1 = false;
                boolean con2 = false;

                if(temp.length != 0 && temp2.length != 0) con1 = true;
                if(temp != " " && temp2 != " ") con2 = true;

                if(con1 && con2)
                {
                    String question = et_question.getText().toString();
                    String answer = et_answer.getText().toString();

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
