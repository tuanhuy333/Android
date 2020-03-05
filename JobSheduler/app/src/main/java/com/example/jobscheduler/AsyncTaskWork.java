package com.example.jobscheduler;

import android.os.AsyncTask;
import android.os.SystemClock;

public class AsyncTaskWork extends AsyncTask<Void,Void,String> {

    @Override
    protected String doInBackground(Void... voids) {
        SystemClock.sleep(2000);

        return "doing background";
    }
}
