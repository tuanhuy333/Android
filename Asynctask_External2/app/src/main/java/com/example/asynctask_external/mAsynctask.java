package com.example.asynctask_external;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class mAsynctask extends AsyncTask<Void, Void, Void> {
    ProgressDialog mProgress;
    ProgressBar progressBar;
    Context context;
    Activity activity;

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public mAsynctask(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Progress Dialog
        //        mProgress = new ProgressDialog(context);
        //            mProgress.setMessage("dsad");
        //            mProgress.setCancelable(false);
        //            mProgress.show();

        // Progress Bar
        Log.d("a", "bat dau");
        try {
            Thread.sleep(2000);
            progressBar = activity.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected Void doInBackground(Void... voids) {

        // công việc tốn nhiều time
        mTask();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //mProgress.dismiss();
        Log.d("a", "xong");
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void mTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
