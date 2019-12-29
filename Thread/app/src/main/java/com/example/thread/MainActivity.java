package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt=(TextView)findViewById(R.id.txt);
        postTaskInsideBackgroundTask();
    }

    private void postTaskInsideBackgroundTask(){
        Thread backgroundThread=new Thread(new Runnable() {
            @Override
            public void run() {
                // pretend to do something "background-y"
                try {
                    Thread.sleep(5000); //nghi 5s


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                txt.post(new Runnable() {
                    @Override
                    public void run() {
                        //muon tuong tac voi UI phai post
                        txt.setText("Hi from a Handler inside of a background Thread!");
                    }
                });
            }
        });

        backgroundThread.start();
    }
}
