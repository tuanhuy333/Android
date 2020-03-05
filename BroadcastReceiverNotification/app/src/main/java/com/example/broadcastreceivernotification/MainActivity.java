package com.example.broadcastreceivernotification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btn_start;
    EditText edt_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        btn_start = (Button) findViewById(R.id.btn_rung);
        edt_time = (EditText) findViewById(R.id.time);


        //


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                //sau so giay se thong bao
                calendar.add(calendar.SECOND, Integer.parseInt(edt_time.getText().toString()));

                setAlarm(calendar);
            }
        });


    }

    private void setAlarm(Calendar c) {
        Intent intent = new Intent(MainActivity.this, alarmReceiver.class);
        intent.putExtra("thoigian", c.get(c.SECOND));



        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        Toast.makeText(MainActivity.this, "Còn " + c.SECOND + "s sẽ thông báo", Toast.LENGTH_SHORT).show();

    }
}
