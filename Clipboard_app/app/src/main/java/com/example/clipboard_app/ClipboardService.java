package com.example.clipboard_app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ClipboardService extends Service {


    private ClipboardManager clipboardManager;


//    @Override
//    public void onCreate() {
//
//        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//        Log.d("a", "service is starting");
////        ClipData clip = ClipData.newPlainText("copy", "daas");
////        clipboardManager.setPrimaryClip(clip);
//
//        clipboardManager.addPrimaryClipChangedListener(listener);
//
//        super.onCreate();
//    }


    //khi chay startService() se thuc hien onStartCommand
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        Log.d("a", "service is starting");
//        ClipData clip = ClipData.newPlainText("copy", "daas");
//        clipboardManager.setPrimaryClip(clip);
        push();
        clipboardManager.addPrimaryClipChangedListener(listener);



        return START_STICKY; //se restart service khi he thong co dung luong
    }

    public ClipboardManager.OnPrimaryClipChangedListener listener = new ClipboardManager.OnPrimaryClipChangedListener() {
        @Override
        public void onPrimaryClipChanged() {
            String charSequence = clipboardManager.getPrimaryClip().getItemAt(0)
                    .coerceToText(getApplicationContext()).toString();


            System.out.println("copied " + charSequence);
            Log.d("a", charSequence);


            //mo app
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Bundle b = new Bundle();
            b.putString("txt", charSequence);
            intent.putExtra("data", b);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK); //mo activity voi new task


            startActivity(intent);





        }
    };
    public void push(){
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

        Notification notification=builder.build();

        startForeground(2,notification);
    }

    @Override
    public void onDestroy() {
        Log.d("a", "destroy");
//        if (clipboardManager != null) {
//            clipboardManager.removePrimaryClipChangedListener(listener);
//        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
