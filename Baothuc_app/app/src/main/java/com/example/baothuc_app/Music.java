package com.example.baothuc_app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


public class Music extends Service {
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        mediaPlayer=MediaPlayer.create(this,R.raw.baothuc);
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer.start();

        Log.e("fwqa","cădfwf");
        return START_NOT_STICKY;
    }

}
