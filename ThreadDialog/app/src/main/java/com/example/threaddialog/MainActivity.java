package com.example.threaddialog;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_exit, btn_dialog;
    TextView tv_result;
    Boolean isThread = false;
    Thread thread;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "쓰레드 동작중입니다.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_exit = (Button)findViewById(R.id.btn_exit);
        btn_dialog = (Button)findViewById(R.id.btn_dialog);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isThread = true;

                thread = new Thread()
                {
                    public void run()
                    {
                        while(isThread)
                        {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            handler.sendEmptyMessage(0);
                        }
                    }
                };
                thread.start();
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isThread = false;
            }
        });

        tv_result = (TextView)findViewById(R.id.tv_result);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setIcon(R.mipmap.ic_launcher);
                ad.setTitle("경고메세지");
                ad.setMessage("적용하시겠습니까?");

                final EditText et = new EditText(MainActivity.this);
                ad.setView(et);
                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String str = et.getText().toString();
                        tv_result.setText(str);
                        dialogInterface.dismiss();
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                ad.show();

            }
        });
    }

}
