package com.example.IntentPreferencs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText edt;
    private String shared = "file";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = findViewById(R.id.edt_1);
        btn = findViewById(R.id.btn_1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str;
                str = edt.getText().toString();
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("str", str);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared", 0);

        String value = sharedPreferences.getString("str", "");
        edt.setText(value);
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = edt.getText().toString();
        editor.putString("str", value);
        editor.commit();

        super.onDestroy();
    }
}
