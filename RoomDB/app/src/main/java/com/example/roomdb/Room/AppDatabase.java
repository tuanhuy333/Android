package com.example.roomdb.Room;

import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class, Company.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmployDao employDao();


    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "Employee_DB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }


}