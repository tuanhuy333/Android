package com.example.copy_note_app.RoomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 2, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract DAO noteDAO();

    public static NoteDatabase INSTANCE = null;

    public static NoteDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, NoteDatabase.class, "Note_DB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(noteDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    //call back for populate the database
    public static NoteDatabase.Callback noteDatabaseCallback = new NoteDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // new populateAsyncTask(INSTANCE).execute();

        }
    };
}

//tao du lieu database ban dau
//tao async task de thuc hien call back trong luong ( khong thuc hien trong main UI thread)
class populateAsyncTask extends AsyncTask<Void, Void, Void> {

    private DAO noteDAO;

    public populateAsyncTask(NoteDatabase noteDatabase) {
        noteDAO = noteDatabase.noteDAO();
    }

    @Override
    protected Void doInBackground(Void... voids) {

       // noteDAO.insertNote(new Note(3, "dqwd", "qqw"));

        return null;
    }
}

