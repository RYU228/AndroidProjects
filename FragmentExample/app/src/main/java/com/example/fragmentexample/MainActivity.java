package com.example.fragmentexample;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    private Button btn_frag1, btn_frag2, btn_frag3, btn_frag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_frag1 = (Button)findViewById(R.id.btn_frag1);
        btn_frag2 = (Button)findViewById(R.id.btn_frag2);
        btn_frag3 = (Button)findViewById(R.id.btn_frag3);
        btn_frag4 = (Button)findViewById(R.id.btn_frag4);

        btn_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment1 fragment1 = new Fragment1();
                fragmentTransaction.replace(R.id.frame, fragment1);
                fragmentTransaction.commit();
            }
        });

        btn_frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment2 fragment2 = new Fragment2();
                fragmentTransaction.replace(R.id.frame, fragment2);
                fragmentTransaction.commit();
            }
        });

        btn_frag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment3 fragment3 = new Fragment3();
                fragmentTransaction.replace(R.id.frame, fragment3);
                fragmentTransaction.commit();
            }
        });

        btn_frag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment4 fragment4 = new Fragment4();
                fragmentTransaction.replace(R.id.frame, fragment4);
                fragmentTransaction.commit();
            }
        });
    }
}
