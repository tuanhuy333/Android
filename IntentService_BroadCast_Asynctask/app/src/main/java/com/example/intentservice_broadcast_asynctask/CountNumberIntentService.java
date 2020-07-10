package com.example.intentservice_broadcast_asynctask;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.Nullable;

public class CountNumberIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    boolean checkCount = false;

    // khai báo Receiver
    CountNumberReceiver countNumberReceiver=new CountNumberReceiver();

    public CountNumberIntentService() {
        super("CountNumberIntentService");
    }

    // chỉ chạy 1 lần
    @Override
    public void onCreate() {
        super.onCreate();

        // đăng ký nhận phát sóng
        registerReceiver(countNumberReceiver,new IntentFilter("countNumber"));
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // công việc
        // nếu ko có đếm thì đếm
        if(checkCount == false){
            checkCount = true;

            for (int i=0;i<100;++i){
                // gửi Broadcast
                sendBroadcast(new Intent("countNumber").putExtra("count",i + ""));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(countNumberReceiver);
        super.onDestroy();
        checkCount = false;
    }
}
