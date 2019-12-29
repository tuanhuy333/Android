package com.example.app_alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;


public class myAlarmReceiver extends BroadcastReceiver {
    public static MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {
        mediaPlayer=MediaPlayer.create(context,R.raw.alarm);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        //show notification
        show_notification(context);
    }
    public void show_notification(Context context){
            //rung
        Vibrator vibrator= (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if(vibrator.hasVibrator()){
            vibrator.vibrate(2000);
        }


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);// Tạo đối tượng Notification

            //tạo sự kiện khi click vào notification
            Intent intent=new Intent(context,MainActivity.class);//ý định đến MainActivity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);//lấy màn hình



            //build notification
            Notification.Builder builder=new Notification.Builder(context)
                    .setSmallIcon(R.drawable.ic_access_alarms_black_24dp)
                    .setContentTitle("Báo thức")
                    .setContentText("Bấm vào đây để tắt nè !")

                    //chạm vào notification
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);//click vào sẽ biến mất notification



            notificationManager.notify(123, builder.build());



    }
}
