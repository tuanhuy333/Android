package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class LocalService extends Service {
    //được trả về cho Client
    private IBinder mBinder = new LocalBinder();

    private Random mGenerator = new Random();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        //trả về instance của LocalService để client có thể gọi phương thức công khai
        LocalService getService() {
            return LocalService.this;
        }
    }

    /**
     * method for clients
     */
    //phương thức cho client
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
