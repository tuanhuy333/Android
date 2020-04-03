package com.example.copy_note_app.RoomDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DAO {

    @Insert(onConflict = REPLACE)
    void insertNote(Note note) ;

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAll();

//    @Query("UPDATE note SET date = :newDate, content = :newContent WHERE id =:id")
//    void updateNoteById(int id,String newDate,String newContent);

}