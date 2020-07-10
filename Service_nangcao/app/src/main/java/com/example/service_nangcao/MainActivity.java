package com.example.service_nangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.txt);

        Log.d("a","onCreate");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("DOWNLOAD_DONE"); //đăng ký nhận tín hiệu có Actionn là
        // "DOWNLOAD_DONE"
        registerReceiver(mReceiver, intentFilter);


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startService(new Intent(getBaseContext(),mService.class));
            }
        });

    }



    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("a","received");
            String message=intent.getStringExtra("message");

            txt.setText(message);
        }
    };
}
