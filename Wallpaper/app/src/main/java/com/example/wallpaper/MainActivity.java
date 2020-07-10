package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_setWall;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();



        //click
        btn_setWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lapLich();


            }
        });

    }

    private void lapLich() {
        JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getApplicationContext(), MyJob.class);
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(15*60*1000) //15phut
                .setPersisted(true)

                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)

                .build();
        int i=jobScheduler.schedule(jobInfo);

        if(i == JobScheduler.RESULT_SUCCESS){
            Log.d("a","Job Scheduled");
        }

        Toast.makeText(this, "Đã lập lịch", Toast.LENGTH_SHORT).show();
    }



    private void initView() {


        btn_setWall = findViewById(R.id.button);
        txt = findViewById(R.id.textView);
    }
}
