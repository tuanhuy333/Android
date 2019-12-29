package com.example.activity_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("aaa","create");
    }

    @Override
    protected void onStart() {
        Log.d("aaa","start");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("aaa","resume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("aaa","pause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("aaa","restart");
    }

    @Override
    protected void onStop() {
        Log.d("aaa","stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("aaa","destroy");
        super.onDestroy();
    }
}
