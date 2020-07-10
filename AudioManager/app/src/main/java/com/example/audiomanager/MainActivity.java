package com.example.audiomanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_vibrate, btn_silent, btn_sound;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        Toast.makeText(this, audioManager.getRingerMode() + "", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, audioManager.getMode() + "", Toast.LENGTH_SHORT).show();

        anhxa();


        //che do im lang phai xin quyen trong cai dat
        //vi anh huong den cac thong bao cua ung dung khac cua dien thoai

        btn_silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xinquyen_silent();
            }
        });
        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
        });
        btn_vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            }
        });

    }

    public void anhxa() {
        btn_silent = findViewById(R.id.btn_silent);
        btn_sound = findViewById(R.id.btn_sound);
        btn_vibrate = findViewById(R.id.btn_vibrate);
    }

    public void xinquyen_silent() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //neu da duoc ban cho quyen
        if (notificationManager.isNotificationPolicyAccessGranted()) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        } else {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivityForResult(intent, 111);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //neu khong duoc dong y tu nguoi dung thi xin quyen lai
        if (requestCode != 111) {
            this.xinquyen_silent();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
