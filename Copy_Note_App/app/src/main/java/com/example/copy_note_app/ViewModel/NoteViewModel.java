package com.example.copy_note_app.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.copy_note_app.Responsitory.Responsitory;
import com.example.copy_note_app.RoomDB.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private Responsitory NoteReponsitory;
    private LiveData<List<Note>> allNote;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        NoteReponsitory = new Responsitory(application);
        allNote = NoteReponsitory.getAllNoteReponsitory();
    }

    //getter de tang tren (view) truy cap
    public LiveData<List<Note>> getAllNoteViewModel() {
        return allNote;
    }

    public void insertNoteViewModel(Note Note) {

        NoteReponsitory.insertNoteReponsitory(Note);
    }

    public void deleteNoteViewModel(Note Note) {
        NoteReponsitory.deleteNoteReponsitory(Note);
    }
    public void updateNoteViewModel(Note Note){
        NoteReponsitory.updateNoteReponsitory(Note);
    }
}

