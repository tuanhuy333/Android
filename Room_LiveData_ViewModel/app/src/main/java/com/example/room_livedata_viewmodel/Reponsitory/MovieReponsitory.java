package com.example.room_livedata_viewmodel.Reponsitory;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.room_livedata_viewmodel.Room.Movie;
import com.example.room_livedata_viewmodel.Room.MovieDAO;
import com.example.room_livedata_viewmodel.Room.MovieDatabase;

import java.util.List;

public class MovieReponsitory {

    private MovieDAO movieDAO;
    private LiveData<List<Movie>>allMovie;

    //lay data tu Room
    public MovieReponsitory(Application application){

        MovieDatabase movieDatabase=MovieDatabase.getInstance(application);
        movieDAO=movieDatabase.movieDAO();
        allMovie=movieDAO.getAll();

    }

    //getter de tang tren (ViewModel) truy cap
    public LiveData<List<Movie>>getAllMovieReponsitory(){
        return allMovie;
    }

    //insert
    public void insertMovieReponsitory(Movie movie){
        new insertAsyncTask(movieDAO).execute(movie);
    }

    //delete
    public void deleteMovieReponsitory(Movie movie){
        new deleteAsyncTask(movieDAO).execute(movie);
    }

}
class insertAsyncTask extends AsyncTask<Movie,Void,Void>{

    private MovieDAO movieDAOAsyncTask;

    public insertAsyncTask(MovieDAO movieDAO){
        movieDAOAsyncTask=movieDAO;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        movieDAOAsyncTask.insertMovie(movies[0]);

        return null;
    }
}
class deleteAsyncTask extends AsyncTask<Movie,Void,Void>{
    private MovieDAO movieDAOAsyncTask;

    public deleteAsyncTask(MovieDAO movieDAO){
        movieDAOAsyncTask=movieDAO;
    }
    @Override
    protected Void doInBackground(Movie... movies) {

        movieDAOAsyncTask.deleteMovie(movies[0]);
        return null;
    }
}
