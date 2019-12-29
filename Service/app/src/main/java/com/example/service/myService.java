package com.example.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;



public class myService extends Service {
    MediaPlayer mediaPlayer;
    Notification notification;
    @Override
    public void onCreate() {
        super.onCreate();
       mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.baothuc);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // Service này là loại không giàng buộc (Un bounded)
        // Vì vậy method này ko bao giờ được gọi.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer.start();


        return START_STICKY;
    }
}
