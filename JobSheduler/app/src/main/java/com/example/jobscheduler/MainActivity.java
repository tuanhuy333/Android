package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private JobScheduler jobScheduler;
    private JobInfo jobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }


    public void StartJob(View v) {
        JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getApplicationContext(), mJobScheduler.class);
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
             .setPeriodic(15*60*1000)
                .setPersisted(true)

                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)

                .build();
        int i=jobScheduler.schedule(jobInfo);

        if(i == JobScheduler.RESULT_SUCCESS){
            Log.d("a","Job Scheduled");
        }

        Toast.makeText(this, "Đã lập lịch", Toast.LENGTH_SHORT).show();
    }

    public void ClearJob(View v) {
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(1);
        Log.d("a1","dung lichj");
    }
}
