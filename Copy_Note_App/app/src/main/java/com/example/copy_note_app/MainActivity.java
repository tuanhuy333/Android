package com.example.copy_note_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.copy_note_app.Adapter.NoteAdapter;
import com.example.copy_note_app.RoomDB.Note;
import com.example.copy_note_app.Service.ClipBoardService;
import com.example.copy_note_app.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<Note> mData;
    NoteAdapter mAdapter;

    NoteViewModel noteViewModel;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.mRecyclerView);
        mData = new ArrayList<>();


        //bat dau service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, ClipBoardService.class));
        } else {
            startService(new Intent(this, ClipBoardService.class));

        }


        //tao data với viewmodel
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
//        noteViewModel.insertNoteViewModel(new Note( "09/03/2020", "Code app"));
//        noteViewModel.insertNoteViewModel(new Note( "09/03/2020", "Code app 2"));


        Intent i = getIntent();

        Bundle b = i.getBundleExtra("data");
        if (b != null) {
            String note = b.getString("txt", "default");
            String date = getCurrentDay();
            noteViewModel.insertNoteViewModel(new Note("NoTitle", date, note));
        }

        //quan sat de update len recycler view
        noteViewModel.getAllNoteViewModel().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                mData = notes;

                mAdapter = new NoteAdapter(MainActivity.this, R.layout.item_note, mData);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnClickListener(onItemClickListener);

            }
        });


        //swipe for delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Note Note = mAdapter.getNoteAt(viewHolder.getAdapterPosition());

                //delete
                noteViewModel.deleteNoteViewModel(Note);
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(mRecyclerView);


        //click them

        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,Detail_Note.class));
            }
        });


    }

    //get current day
    public String getCurrentDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String mdate = dateFormat.format(date);
        return mdate;
    }

    //click vào detail note sẽ chuyển sang activity khác để hiển thị
    private NoteAdapter.onItemClickListener onItemClickListener = new NoteAdapter.onItemClickListener() {

        @Override
        public void onClick(View v, final int position) {


            Note current_note = mData.get(position);

            Intent intent = new Intent(MainActivity.this, Detail_Note.class);
            Bundle b = new Bundle();
            b.putSerializable("current_note",current_note);
            intent.putExtra("data",b);

            startActivity(intent);

        }
    };


}
