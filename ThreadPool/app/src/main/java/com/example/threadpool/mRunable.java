package com.example.threadpool;

import android.util.Log;

public class mRunable implements Runnable {

    String name;

    public mRunable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    @Override
    public void run() {

        try {
            Log.d("thread","start "+getName());
            Thread.sleep(1000);
            Log.d("thread","finish"+getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
