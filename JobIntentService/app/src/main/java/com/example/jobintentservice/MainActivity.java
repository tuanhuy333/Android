package com.example.jobintentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// JobIntentService khắc phục IntentService bị ảnh hưởng bởi BackgroundLimitations
// Android 8+ IntentService ko còn hoạt động đúng nữa

public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        txt = findViewById(R.id.txt);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2)
                // thực hiện JobIntentService
                Intent intent = new Intent(MainActivity.this, SimpleJobIntentService.class);
                intent.putExtra("maxCountValue", 20);
                SimpleJobIntentService.enqueueWork(MainActivity.this, intent);
            }
        });


    }
}