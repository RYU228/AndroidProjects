package com.example.englishstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_eng;
    private Button btn_main_input;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_eng = (TextView)findViewById(R.id.tv_eng);

        tv_eng.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String str = tv_eng.getText().toString();
                int action = motionEvent.getAction();

                if(MotionEvent.ACTION_DOWN == action)
                {
                    tv_eng.setText("영어문장");
                }else if(MotionEvent.ACTION_UP == action)
                {
                    tv_eng.setText(str);
                }

                return true;
            }
        });

        intent = new Intent(MainActivity.this, InputDataActivity.class);
        String data1 = intent.getStringExtra("question");
        intent.getStringExtra("answer");
        tv_eng.setText(data1);


        btn_main_input = (Button)findViewById(R.id.btn_main_input);

        btn_main_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
