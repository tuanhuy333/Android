package com.example.copy_note_app.BroadcastReceiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.copy_note_app.MainActivity;
import com.example.copy_note_app.R;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.getSystemService;

public class mAlarmReceiver extends BroadcastReceiver {

    SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {

            pushNotification(context, intent);



    }

    public void pushNotification(Context context, Intent i) {
        //tap action
        // Create an explicit intent for an Activity in your app
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);

        //lay sharedPreferences
        sharedPreferences = context.getSharedPreferences("data_login", MODE_PRIVATE);

        String title = sharedPreferences.getString("current_title", "noTitle");
        String content = sharedPreferences.getString("current_content", "noContent");


        //notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "noti_002")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("â")
                .setContentText("kuky")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("rưe"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(new long[] {100, 250, 100, 500}) //rung

                .setContentIntent(pendingIntent) //tap action
                .setAutoCancel(true);


        //tao Channel
        //Android Oreo ( android 8 )phai dang ky channel truoc khi gui notification
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("noti_002", "huy", importance);
            channel.setDescription("This is my channel description");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(context, NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        //show notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
//
//        // notificationId is a unique int for each notification that you must define

        notificationManager.notify(3, builder.build());


    }


}
