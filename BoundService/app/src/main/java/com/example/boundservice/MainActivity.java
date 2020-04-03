package com.example.boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LocalService localService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //param2 : ServiceConnection
    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalService.LocalBinder localBinder = (LocalService.LocalBinder) service;
            localService = localBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        //param1 :Intent
        Intent intent = new Intent(this, LocalService.class);
        //goi BoundService
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

        Log.d("d", "bind service");

        super.onStart();
    }

    @Override
    protected void onStop() {

        unbindService(mServiceConnection);
        mBound = false;

        Log.d("d", "unbind service");

        super.onStop();
    }

    public void click(View v) {
        if (mBound) {
            int i = localService.getRandomNumber();
            Log.d("d", i + "");
        }


    }
}
