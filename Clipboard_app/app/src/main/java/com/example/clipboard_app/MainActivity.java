package com.example.clipboard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bat dau service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, ClipboardService.class));
        }else{
            startService(new Intent(this, ClipboardService.class));
        }

        Intent i = getIntent();


        Bundle b = i.getBundleExtra("data");
        if (b != null) {
            String txt = b.getString("txt", "default");
            TextView textView = findViewById(R.id.txt);
            textView.setText(txt);
        }


    }

//    @Override
//    protected void onResume() {
//        startService(new Intent(this, ClipboardService.class));
//        Log.d("a","resume");
//        super.onResume();
//    }
}
