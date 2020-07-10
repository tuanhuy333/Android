package com.example.broadcast1;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class mService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("a","service");

        //gửi tín hiệu cho Activity là đã load xong
        Intent intent1 = new Intent();
        intent1.setAction("DOWNLOAD_DONE");
        intent1.putExtra("message","load xong !");
        sendBroadcast(intent1); //gửi phát sóng
        sendOrderedBroadcast(intent1,null);



        Log.d("a","sended");


        return START_STICKY;
    }

}
