package com.example.room_livedata_viewmodel.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Movie.class},version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDAO movieDAO();

    public static MovieDatabase INSTANCE = null;

    public static MovieDatabase getInstance(Context context){
        if(INSTANCE == null ){
            INSTANCE= Room.databaseBuilder(context, MovieDatabase.class,"Movie_DB")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(movieDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    //call back for populate the database
    public static MovieDatabase.Callback movieDatabaseCallback=new MovieDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

           // new populateAsyncTask(INSTANCE).execute();

        }
    };
}

//tao du lieu database ban dau
//tao async task de thuc hien call back trong luong ( khong thuc hien trong main UI thread)
class populateAsyncTask extends AsyncTask<Void,Void,Void>{

    private MovieDAO movieDAO;
    public populateAsyncTask(MovieDatabase movieDatabase){
       movieDAO=movieDatabase.movieDAO();
    }
    @Override
    protected Void doInBackground(Void... voids) {

        movieDAO.insertMovie(new Movie(3,"dqwd","qqw"));

        return null;
    }
}
