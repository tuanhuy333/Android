package com.example.lifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("msg","onCreate");

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    // ...

    //Override các hàm của Activity để xem sự thay đổi




    @Override
    protected void onStart() {
        Log.d("msg", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("msg", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("msg", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("msg", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("msg", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("msg", "onRestart");
        super.onRestart();
    }
}
