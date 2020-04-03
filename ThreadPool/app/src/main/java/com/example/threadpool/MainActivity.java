package com.example.threadpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        //dùng ExecutorService để triển khai ThreadPool

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //có 100 request
        for(int i=0;i<20;++i){
            executorService.execute(new mRunable("request_"+i));
        }
        executorService.shutdown(); //không cho ThreadPool nhận thêm nhiệm vụ

        while(!executorService.isTerminated()){
            //chờ xử lý hết các request còn chờ trong Queue
        }
    }
}
