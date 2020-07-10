package com.example.widget;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {


    @Override
    public void onReceive(final Context context, Intent intent) {

        //khi tap and hold on widget
        //thi se vao onReceive

        if (intent.getAction().equals("my action")) {
            //THIS IS WHERE YOUR CODE WILL BE EXECUTED
            Log.d("a", "123124");

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.test();
                    playSound(R.raw.messenger_message,context);
                    pushNotification(context);
                }
            },5000);
        }

        Log.d("a", "nhan phat song");
        super.onReceive(context, intent);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        String max_width = AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH;
        // Construct the RemoteViews object
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E ,dd/MM/yyyy");
//        Date date = new Date();
//        String time = simpleDateFormat.format(date);


        SharedPreferences sharedPreferences = context.getSharedPreferences("word", Context.MODE_PRIVATE);
        String english = sharedPreferences.getString("english", "my english");
        String vietnamese = sharedPreferences.getString("vietnamese", "my vietnamese");

        //views.setTextViewText(R.id.time,time);
        views.setCharSequence(R.id.appwidget_text, "setText", english);
        views.setTextViewText(R.id.mText, vietnamese);



        //click vào và nhận broadcast
        Intent intent=new Intent(context,NewAppWidget.class);
        intent.setAction("my action");
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intent,0);


        views.setOnClickPendingIntent(R.id.btn_widget,pendingIntent);

        //click vào text view để mở app
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
//        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d("a1", "updated");

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    // play sound
    public void playSound(int sound,Context context) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context,sound);
        mediaPlayer.start();
    }

    public void pushNotification(Context context) {

        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";
        String Description = "This is my channel";

        int NOTIFICATION_ID = 234;

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);

            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{500, 500, 500});
            mChannel.setShowBadge(true);

            if (notificationManager != null) {

                notificationManager.createNotificationChannel(mChannel);
            }

        }

        // tap action to open app
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID).setContentTitle("Mỹ Thanh")

                .setContentText("editText2.").setStyle(new NotificationCompat.BigTextStyle().bigText("dsadasda"))
                .setSmallIcon(R.drawable.ic_launcher_background)


                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(resultPendingIntent).setAutoCancel(true).setColor(context.getResources().getColor(R.color.colorPrimary))
                        .addAction(R.drawable.ic_launcher_background, "Thích",
                                resultPendingIntent).addAction(R.drawable.ic_launcher_background, "Trả lời", resultPendingIntent);


        if (notificationManager != null) {

            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }

    }
}

