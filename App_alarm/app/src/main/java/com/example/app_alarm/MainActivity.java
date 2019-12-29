package com.example.app_alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    DatePicker datePicker;
    Button btn_datgio,btn_test;
    TextView txt_giobaothuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        timePicker=(TimePicker)findViewById(R.id.time);
        btn_datgio=(Button)findViewById(R.id.btn_datgio);
        txt_giobaothuc=(TextView)findViewById(R.id.txt_giobaothuc);
        btn_test=(Button)findViewById(R.id.button);
        datePicker=(DatePicker)findViewById(R.id.datepicker);

        //cập nhật text khi chỉnh đồng hồ
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                txt_giobaothuc.setText("Giờ báo thức : "+hourOfDay+" :"+minute+"" );


            }
        });

        //click đặt giờ
        btn_datgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                if(Build.VERSION.SDK_INT >=23){
                    calendar.set(
                    calendar.get(calendar.YEAR),
                    calendar.get(calendar.MONTH),
                    calendar.get(calendar.DAY_OF_MONTH),
                    timePicker.getCurrentHour(),
                    timePicker.getCurrentMinute()-1,
                    30
                    );
                }else
                {
                    calendar.set(

                            datePicker.getYear(),
                            datePicker.getMonth(),
                            datePicker.getDayOfMonth(),

                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute()-1,
                            30
                    );

                }


                setAlarm(calendar);


            }
        });

        //dừng nhạc
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlarmReceiver.mediaPlayer.stop();
            }
        });
    }

    private void setAlarm(Calendar c) {
        AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent=new Intent(this,myAlarmReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(this, "Đã đặt báo thức ", Toast.LENGTH_SHORT).show();
    }
}
