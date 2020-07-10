package com.example.broadcast1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class mBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.myBroadcast")){
            Toast.makeText(context, "đấ", Toast.LENGTH_SHORT).show();
            Log.d("a","received!");
        }

    }
}
