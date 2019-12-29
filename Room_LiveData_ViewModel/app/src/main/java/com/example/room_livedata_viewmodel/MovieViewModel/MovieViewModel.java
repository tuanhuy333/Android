package com.example.room_livedata_viewmodel.MovieViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.room_livedata_viewmodel.Reponsitory.MovieReponsitory;
import com.example.room_livedata_viewmodel.Room.Movie;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieReponsitory movieReponsitory;
    private LiveData<List<Movie>>allMovie;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        movieReponsitory=new MovieReponsitory(application);
        allMovie=movieReponsitory.getAllMovieReponsitory();
    }

    //getter de tang tren (view) truy cap
    public LiveData<List<Movie>>getAllMovieViewModel(){
        return allMovie;
    }

    public void insertMovieViewModel(Movie movie){

        movieReponsitory.insertMovieReponsitory(movie);
    }

    public void deleteMovieViewModel(Movie movie){
        movieReponsitory.deleteMovieReponsitory(movie);
    }
}
