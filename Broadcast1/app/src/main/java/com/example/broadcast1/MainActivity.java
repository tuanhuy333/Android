package com.example.broadcast1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // chạy service( thí dụ download file rồi gửi broadcast "đã xong" )
        startService(new Intent(this, mService.class));

        // đăng ký nhận phát sóng
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("DOWNLOAD_DONE");

        // android 8 hết cho đăng ký nhận broadcast trong Manifest rồi
        registerReceiver(receiver, intentFilter);


    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String a = intent.getStringExtra("message");
            Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
        }
    };


    // khi activity bị hủy thì sendBroadcast

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("a", "destroy!");
        unregisterReceiver(receiver);

        // nếu đăng ký trong onResume thì hủy trong onStop để tránh rò gỉ data

    }
}
