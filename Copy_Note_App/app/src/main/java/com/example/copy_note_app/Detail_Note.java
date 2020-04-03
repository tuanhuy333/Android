package com.example.copy_note_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.copy_note_app.BroadcastReceiver.mAlarmReceiver;
import com.example.copy_note_app.RoomDB.Note;
import com.example.copy_note_app.ViewModel.NoteViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class Detail_Note extends AppCompatActivity implements View.OnClickListener {

    EditText edt_detail_title, edt_detail_content;
    Button btn_remind;

    NoteViewModel noteViewModel;

    Note current_note;
    Bundle b;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__note);

        anhxa();

        //nhan du lieu tu MainActivity
        Intent intent = getIntent();
        b = intent.getBundleExtra("data");

        //lấy note từ bên kia
        if (b != null) {
            current_note = (Note) b.getSerializable("current_note");
            //luu shared
            luu_shared();

            //click để show time picker dialog
            btn_remind.setOnClickListener(this);

            //gán dữ liệu de hien thi
            edt_detail_title.setText(current_note.getTitle());
            edt_detail_content.setText(current_note.getContent());
        }


        //tạo viewModel
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);







    }

    public void anhxa() {
        //
        edt_detail_title = findViewById(R.id.edt_detail_title);
        edt_detail_content = findViewById(R.id.edt_detail_content);
        btn_remind = findViewById(R.id.btn_reminder);
    }

    public void luu_shared() {
        //lưu vào sharedPreferences để broadcast dùng chung

        sharedPreferences = getSharedPreferences("data_login", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        String title[]=new String[]{"t1,t2,t3"};


        editor.putString("current_title", current_note.getTitle());
        editor.putString("current_content", current_note.getContent());


        editor.commit();//xác nhận

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        //nếu title null
        //bắt sự kiện khi focus vào title
        if(edt_detail_title.getText().toString().isEmpty()){
            edt_detail_title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        getMenuInflater().inflate(R.menu.mymenu, menu);
                        menu.findItem(R.id.add).setVisible(true);
                    }else {
                        //không duoc focus
                    }
                }
            });
        }else {
            getMenuInflater().inflate(R.menu.mymenu, menu);
            menu.findItem(R.id.save).setVisible(true);
        }



        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String newTitle = edt_detail_title.getText().toString().trim();
        String newContent = edt_detail_content.getText().toString().trim();



        //current date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = simpleDateFormat.format(date);


        Note addNote = new Note(newTitle, currentDate, newContent);


        if (item.getItemId() == R.id.add) {
            //thuc hien update
            noteViewModel.insertNoteViewModel(addNote);

            //quay ve main activity
            startActivity(new Intent(this, MainActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)); // để khỏi trở về detail khi lại bấm nút back

            Toast.makeText(this, "Thêm thành công", LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.save) {

            int currentId = current_note.getId();
            Note newNote = new Note(newTitle, currentDate, newContent);
            newNote.setId(currentId);

            //thuc hien update
            noteViewModel.updateNoteViewModel(newNote);

            //quay ve main activity
            startActivity(new Intent(this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)); // để khỏi trở về detail khi lại bấm nút back
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        final Calendar calendar = Calendar.getInstance();
        int mHour = calendar.get(calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(calendar.MINUTE);
        


        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);

                Intent intent = new Intent(Detail_Note.this, mAlarmReceiver.class);
                //khong the intent.putExtra("a","222")
                //sang ben Broadcast sẽ bị null
                final PendingIntent pendingIntent = PendingIntent.getBroadcast(Detail_Note.this, ((int) calendar.getTimeInMillis()), intent, 0);


                // Set the alarm to start at approximately 2:00 p.m.
                final Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                c.set(Calendar.MINUTE, minute);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),
                                AlarmManager.INTERVAL_DAY, pendingIntent);
                    }
                }).start();



//                alarmManager2.setRepeating(AlarmManager.RTC_WAKEUP,30000,
//                        AlarmManager.INTERVAL_DAY, pendingIntent);


                //khi sendBroadcast thi se chuyen qua broadCast ngay lap tuc


                Toast.makeText(Detail_Note.this, hourOfDay + "  " + minute, LENGTH_SHORT).show();


            }
        }, mHour, mMinute, false);

        timePickerDialog.show();
    }
}
