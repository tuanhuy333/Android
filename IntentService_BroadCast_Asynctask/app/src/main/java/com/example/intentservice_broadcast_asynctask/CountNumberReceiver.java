package com.example.intentservice_broadcast_asynctask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CountNumberReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // lấy giá trị từ sendBroadcast
        String s = intent.getStringExtra("count");

        // gọi thực hiện Asynctask
        MainActivity.UpdateValueAsynctask asynctask = new MainActivity.UpdateValueAsynctask();
        asynctask.execute(s);
    }
}
