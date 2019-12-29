package com.example.room_livedata_viewmodel.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "name")
    private String name_movie;

    @ColumnInfo(name = "type")
    private String type_movie;


    public Movie(int id, String name_movie, String type_movie) {
        this.id = id;
        this.name_movie = name_movie;
        this.type_movie = type_movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_movie() {
        return name_movie;
    }

    public void setName_movie(String name_movie) {
        this.name_movie = name_movie;
    }

    public String getType_movie() {
        return type_movie;
    }

    public void setType_movie(String type_movie) {
        this.type_movie = type_movie;
    }
}
