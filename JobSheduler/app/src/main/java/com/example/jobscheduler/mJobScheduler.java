package com.example.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class mJobScheduler extends JobService {

    private boolean isWorking = false;
    private boolean jobCanceled = false;

    private AsyncTaskWork mWork;

    @Override
    public boolean onStartJob(final JobParameters params) {


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doWork(params);
//            }
//        }).start();

        mWork = new AsyncTaskWork() {
            @Override
            protected void onPostExecute(String s) {
                for(int i=0;i<20;++i){
                    Toast.makeText(getApplicationContext(), i+s, Toast.LENGTH_SHORT).show();
                    jobFinished(params,false);
                    if(jobCanceled){
                        return;
                    }

                }


            }

        };

        mWork.execute();


        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("a", "stop job before finished");
        jobCanceled = true;
        return true;
    }

//    private class DoItTask extends AsyncTask<Void, Void, Void> {
//
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            Log.d("DoItTask", "Clean up the task here and call jobFinished...");
//            Toast.makeText(getApplicationContext(), "aaaa", Toast.LENGTH_SHORT).show();
//            jobFinished(params, true);
//            super.onPostExecute(aVoid);
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            Log.d("DoItTask", "Working here...");
//            SystemClock.sleep(2000);
//            return null;
//        }

    public void doWork(JobParameters parameters) {
        for (int i = 0; i < 20; ++i) {
            Log.d("a", "run" + i);

            if (jobCanceled) {
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        jobFinished(parameters, false);

    }
}
