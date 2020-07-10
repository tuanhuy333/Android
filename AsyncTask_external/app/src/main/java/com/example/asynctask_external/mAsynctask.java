package com.example.asynctask_external;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class mAsynctask extends AsyncTask<String,Void,Void> {
    ProgressDialog progressDialog;
    TextView txt;
    Activity activity;
    Context context;

    public mAsynctask(Activity activity){
        this.activity = activity;
        this.context = activity;
        this.progressDialog = new ProgressDialog(activity);
        this.progressDialog.setMessage("âs");
        this.progressDialog.show();
    }

    @Override
    protected void onPreExecute() {


        progressDialog.setCancelable(false);
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... strings) {
        congviec(); // làm công việc tốn 5s
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        progressDialog.dismiss();

        super.onPostExecute(aVoid);
    }


    //cong viec
    public void congviec(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
