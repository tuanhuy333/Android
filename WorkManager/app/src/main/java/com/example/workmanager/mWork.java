package com.example.workmanager;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class mWork extends Worker {


    public mWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }


    @NonNull
    @Override
    public Result doWork() {

        Date date = new Date();
        int current_hour = date.getHours();
        int current_minute = date.getMinutes();
        int minute0 = current_hour * 60 + current_minute;

        //get shared
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("timeSchedule", MODE_PRIVATE);
        int to_hour = sharedPreferences.getInt("toHour", 11);
        int to_minute = sharedPreferences.getInt("toMinute", 30);
        int from_hour = sharedPreferences.getInt("fromHour", 7);
        int from_minute = sharedPreferences.getInt("fromMinute", 0);
        int minute1 = from_hour * 60 + from_minute;
        int minute2 = to_hour * 60 + to_minute;

        Log.d("a", "doing...");
        Log.d("t", minute1 + "");
        Log.d("t", minute2 + "");
        Log.d("t", minute0 + "");


        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        if (minute0 >= minute1 && minute0 <= minute2) {

            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            Log.d("a", "vibrate");
            return Result.retry();


        } else {
            Log.d("a", "success");
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            return Result.success();

        }


//        //get input data
//        String img_data = getInputData().getString("keyURI");
//        for(int i=0;i<30;++i){
//            Log.d("a", img_data+i);
//            SystemClock.sleep(3000);
//        }
//
//
//
//        //create output data
//        Data outputData = new Data.Builder().putString("output", "url").build();


        //upload image
        //sync data to room db
        //send log to server
        //implement long task in server


    }

}
