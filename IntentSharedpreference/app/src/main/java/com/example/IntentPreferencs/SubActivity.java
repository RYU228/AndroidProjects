package com.example.IntentPreferencs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        txt = findViewById(R.id.txt_1);

        Intent intent = getIntent();
        String str = intent.getStringExtra("str");

        txt.setText(str);
    }

}
