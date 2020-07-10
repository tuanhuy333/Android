package com.example.fake_notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn, btn2;
    EditText editText1, editText2;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        editText1 = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        spinner = findViewById(R.id.spinner);

        ArrayList<Integer> data = new ArrayList<>();
        data.add(3000);
        data.add(5000);

        data.add(10000);
        data.add(15000);
        data.add(30000);
        data.add(60000);
        data.add(120000);
        data.add(300000);
        data.add(600000);
        data.add(900000);
        data.add(1800000);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, data);
        spinner.setAdapter(arrayAdapter);


        // sound
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                delayNotification();
                Toast.makeText(MainActivity.this, "Đã set up sound", Toast.LENGTH_SHORT).show();

            }
        });
        // call
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Stringtime = spinner.getSelectedItem().toString();
                final int time = Integer.parseInt(Stringtime);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        playSound(R.raw.dienthoai,getApplicationContext());

                        //
                        Toast.makeText(MainActivity.this, "Đã set up call", Toast.LENGTH_SHORT).show();

                    }
                }, time);
            }
        });

    }

    public void delayNotification() {
        String Stringtime = spinner.getSelectedItem().toString();
        final int time = Integer.parseInt(Stringtime);
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                try {
        //                    Thread.sleep(time);
        //
        //                    playSound();
        //                    pushNotification();
        //                } catch (InterruptedException e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //        }).start();

        // another

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                playSound(R.raw.messenger_message,getApplicationContext());
                pushNotification(getApplicationContext(),editText1.getText().toString().trim(),
                        editText2.getText().toString().trim());
            }
        }, time);
    }

    // play sound
    public static void playSound(int sound, Context c) {
        MediaPlayer mediaPlayer = MediaPlayer.create(c,sound);
        mediaPlayer.start();
    }


    // push Noti
    public static void pushNotification(Context context,String title,String content) {

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
            mChannel.setVibrationPattern(new long[]{0, 100, 2000});
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
                new NotificationCompat.Builder(context, CHANNEL_ID).setContentTitle(title)

                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content)).setSmallIcon(R.drawable.messenger)

                .setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circle_cropped2), 256, 256, true))

                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(resultPendingIntent).setAutoCancel(true).setColor(context.getResources().getColor(R.color.colorPrimary)).addAction(R.drawable.messenger, "Thích", resultPendingIntent).addAction(R.drawable.messenger, "Trả lời", resultPendingIntent);


        if (notificationManager != null) {

            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }

    }
}