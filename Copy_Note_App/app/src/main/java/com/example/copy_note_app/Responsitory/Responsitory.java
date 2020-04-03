package com.example.copy_note_app.Responsitory;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.copy_note_app.RoomDB.DAO;
import com.example.copy_note_app.RoomDB.Note;
import com.example.copy_note_app.RoomDB.NoteDatabase;

import java.util.List;

public class Responsitory {

    private DAO DAO;
    private LiveData<List<Note>> allNote;

    //lay data tu Room
    public Responsitory(Application application) {

        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        DAO = noteDatabase.noteDAO();
        allNote = DAO.getAll();

    }

    //getter de tang tren (ViewModel) truy cap
    public LiveData<List<Note>> getAllNoteReponsitory() {
        return allNote;
    }

    //insert
    public void insertNoteReponsitory(Note note) {
        new insertAsyncTask(DAO).execute(note);
    }

    //delete
    public void deleteNoteReponsitory(Note note) {
        new deleteAsyncTask(DAO).execute(note);
    }

    //update
    public void updateNoteReponsitory(Note note){
        new updateAsyncTask(DAO).execute(note);
    }

}

class insertAsyncTask extends AsyncTask<Note, Void, Void> {

    private DAO DAOAsyncTask;

    public insertAsyncTask(DAO DAO) {
        DAOAsyncTask = DAO;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        DAOAsyncTask.insertNote(notes[0]);

        return null;
    }
}
class updateAsyncTask extends AsyncTask<Note, Void, Void> {

    private DAO DAOAsyncTask;

    public updateAsyncTask(DAO DAO) {
        DAOAsyncTask = DAO;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        DAOAsyncTask.updateNote(notes[0]);

        return null;
    }
}

class deleteAsyncTask extends AsyncTask<Note, Void, Void> {
    private DAO DAOAsyncTask;

    public deleteAsyncTask(DAO DAO) {
        DAOAsyncTask = DAO;
    }

    @Override
    protected Void doInBackground(Note... notes) {

        DAOAsyncTask.deleteNote(notes[0]);
        return null;
    }
}