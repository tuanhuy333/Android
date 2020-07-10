package com.example.jobintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d("a","restart");
            Intent mIntent = new Intent(context, SimpleJobIntentService.class);
            mIntent.putExtra("maxCountValue", 20);
            SimpleJobIntentService.enqueueWork(context, mIntent);
        }
    }
}
