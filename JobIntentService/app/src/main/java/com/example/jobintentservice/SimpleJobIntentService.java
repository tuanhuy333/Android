package com.example.jobintentservice;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.util.logging.LogRecord;

public class SimpleJobIntentService extends JobIntentService {
    /**
     * Unique job ID for this service.
     */
    static final int JOB_ID = 1000;

    // Handler để gửi Runable -> update UI
    // JobIntentService hoạt động ở WorkerThread nền phải dùng Handler
    Handler mHandler = new Handler();

    // Tag
    static final String TAG = "JobIntentService";


    // để Acitivity gọi
    static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, SimpleJobIntentService.class, JOB_ID, intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showToast("JobIntentService create !");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showToast("JobIntentService destroy !");
    }

    //  1)
    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        // giả sử công việc dài ,tốn nhìu time
        int maxCount = intent.getIntExtra("maxCountValue", -1);

        for (int i = 0; i < maxCount; i++) {
            Log.d(TAG, "onHandleWork: The number is: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // Helper for showing tests
    void showToast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SimpleJobIntentService.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
