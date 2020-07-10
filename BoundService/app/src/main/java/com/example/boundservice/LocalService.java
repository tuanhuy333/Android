package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class LocalService extends Service {


    private Random mGenerator = new Random();

    // 2) Tạo instance (sẽ trả về cho client)
    private IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder; // trả về instance của LocalBinder
    }


    // 1) Khai báo 1 class trong "Service" extends Binder
    // trong class implements lại getService() để trả về Instances của "Service"
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

    public void get_Log(){
        for(int i=0;i<20;++i){
            try {
                Thread.sleep(1000);
                Log.d("a",i+"");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
