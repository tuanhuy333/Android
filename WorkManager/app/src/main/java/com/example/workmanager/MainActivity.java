package com.example.workmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button btn_datgio, btn_huy;
    TextView toTime, fromTime;

    OneTimeWorkRequest oneTimeWorkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        anhxa();

        //set value text
        SharedPreferences sharedPreferences = getSharedPreferences("timeSchedule", MODE_PRIVATE);
        int to_hour = sharedPreferences.getInt("toHour", 11);
        int to_minute = sharedPreferences.getInt("toMinute", 30);
        int from_hour = sharedPreferences.getInt("fromHour", 7);
        int from_minute = sharedPreferences.getInt("fromMinute", 0);
       toTime.setText(to_hour+":"+to_minute);
       fromTime.setText(from_hour+":"+from_minute);

        //chon gio bat dau
        fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_Time(fromTime, "fromHour", "fromMinute");
            }
        });
        //chon gio ket thuc
        toTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_Time(toTime, "toHour", "toMinute");
            }
        });
        //huy schedule
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(MainActivity.this).cancelAllWork();
                Toast.makeText(MainActivity.this, "Đã hủy", Toast.LENGTH_SHORT).show();
            }
        });

        //create constraints object
        Constraints constraints = new Constraints.Builder().setRequiresDeviceIdle(false)


                .build();

        //create input data
        Data img_data = new Data.Builder().putString("keyURI", "uri").build();

        LocalDateTime timeAt1 = LocalDate.now().atTime(6, 5);

        LocalDateTime timeNow = LocalDateTime.now();

        Log.d("a", timeAt1.toString());
        Log.d("a", timeNow.toString());


        oneTimeWorkRequest = new OneTimeWorkRequest.Builder(mWork.class)
                .setConstraints(constraints)  //các ràng buộc
                .setInputData(img_data)  //data input output
                .setInitialDelay(5, TimeUnit.SECONDS) //delay time
                .setBackoffCriteria(BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS)
                .addTag("work_nor")


                .build();

        LocalDateTime timeAt2 = LocalDate.now().atTime(6, 6);


        //periodic task
        final PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(mWork.class, 15, TimeUnit.MINUTES)
                .setBackoffCriteria(BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(constraints)

                .build();

        //chay cong viec duoc lap lai sau 15min
        WorkManager.getInstance(this).enqueueUniquePeriodicWork("change_mode2",
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest);

//        Log.d("a","15phut chạy");

        //chay
        btn_datgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xinquyen();

                Log.d("a","15phut chạy");
                Toast.makeText(MainActivity.this, "Đã đặt giờ", Toast.LENGTH_SHORT).show();
            }
        });


//        WorkManager.getInstance(this).beginWith(Arrays.asList(oneTimeWorkRequest, oneTimeWorkRequest2))
//                .enqueue();

//        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
//            @Override
//            public void onChanged(WorkInfo workInfo) {
//                Log.d("state", workInfo.getOutputData().getString());
//
//            }
//        });

    }

    public void anhxa() {
        btn_datgio = findViewById(R.id.btn_datgio);
        btn_huy = findViewById(R.id.btn_huy);
        toTime = findViewById(R.id.to_time);
        fromTime = findViewById(R.id.from_time);
    }

    public void xinquyen() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //neu da duoc ban cho quyen
        if (notificationManager.isNotificationPolicyAccessGranted()) {
            WorkManager.getInstance(MainActivity.this).enqueue(oneTimeWorkRequest);
        } else {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivityForResult(intent, 111);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != 111) {
            this.xinquyen();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void show_Time(final TextView txt, final String keyHour, final String keyMinute) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        //luu vao shared
                        SharedPreferences sharedPreferences = getSharedPreferences("timeSchedule", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(keyHour, hourOfDay);
                        editor.putInt(keyMinute, minute);

                        editor.commit();

                        txt.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
}
