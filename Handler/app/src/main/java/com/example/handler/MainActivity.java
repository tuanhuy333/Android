package com.example.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Handler mHandler;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);

        //goi
        postTaskInsideBackgroundThread1();

    }

    private void postTaskInsideBackgroundThread() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ////tạo 1 handler từ mainThread
                // 1 handler được tạo ra thì cần có đối tượng Looper ở đây lấy Looper của MainThread
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText("Update after 2s");
                    }
                });
            }
        }).start();
    }

    private void postTaskInsideBackgroundThread1() {


        //gửi Message or runable đến MessageQueue của MainThread
        new Thread(new Runnable() {
            @Override
            public void run() {
                //giả vờ công việc lâu đến 2s
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message message = new Message();
                message.obj = "Value i";
                message.what = 1; //mã thông điệp
                mHandler.sendMessage(message); //dùng handler để gửi message đến MessageQueue của MainThread


            }
        }).start();


        //lắng nghe handlerMessage
        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        txt.setText(msg.obj.toString());
                        break;
                }

            }
        };
    }
}
