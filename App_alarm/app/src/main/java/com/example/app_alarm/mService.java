package com.example.app_alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class mService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("service","service is started");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
