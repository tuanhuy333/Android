package com.example.broadcastreceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class mService extends Service {
    mBroadcast mBroadcast;

    @Override
    public void onCreate() {
        mBroadcast = new mBroadcast();
        IntentFilter filter = new IntentFilter("android.intent.action.AIRPLANE_MODE");

        Log.d("service","đã đăng ký broadcast");
        registerReceiver(mBroadcast, filter);

        push();

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {




        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("service", "service bị hủy");
        Log.d("service", "đã hủy broadcast");
        unregisterReceiver(mBroadcast);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //gắn service foreground để ko bị background limitation
    public void push() {
        //tap action
        // Create an explicit intent for an Activity in your app
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent1, 0);

        //notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "noti_001")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification")
                .setContentText("Ứng dụng vẫn đang chạy.")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Ứng dụng vẫn đang chạy"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                .setContentIntent(pendingIntent) //tap action
                .setAutoCancel(true);


        //tao Channel
        //Android Oreo ( android 8 )phai dang ky channel truoc khi gui notification
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("noti_001", "huy", importance);
            channel.setDescription("This is my channel description");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        //show notification
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
//
//        // notificationId is a unique int for each notification that you must define
//        notificationManager.notify(1, builder.build());

        Notification notification = builder.build();

        startForeground(2, notification); //chạy tiền cảnh để ko bị hủy
    }

}
