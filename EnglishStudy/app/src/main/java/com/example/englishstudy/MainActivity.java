package com.example.englishstudy;

import android.content.Intent;
import android.support.annotation.Nullable;
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
    private String origin;
    final static int REQUEST_INTENT = 0;
    final static int RESULT_OK = 1;
    final static int RESULT_CANCEL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_eng = (TextView)findViewById(R.id.tv_eng);

        origin = tv_eng.getText().toString();

        tv_eng.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int action = motionEvent.getAction();

                if(MotionEvent.ACTION_DOWN == action)
                {
                    tv_eng.setText("영어문장");
                }else if(MotionEvent.ACTION_UP == action)
                {
                    tv_eng.setText(origin);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_INTENT && resultCode == RESULT_OK)
        {
            String str = data.getStringExtra("question1");
            tv_eng.setText(str);
            origin = tv_eng.getText().toString();
        }
    }
}
