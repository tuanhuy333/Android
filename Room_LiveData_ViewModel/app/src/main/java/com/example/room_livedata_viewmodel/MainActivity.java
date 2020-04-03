package com.example.room_livedata_viewmodel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.room_livedata_viewmodel.MovieViewModel.MovieViewModel;
import com.example.room_livedata_viewmodel.Reponsitory.MovieReponsitory;
import com.example.room_livedata_viewmodel.Room.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Movie> movieList;
    Adapter_movie adapter_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        movieList = new ArrayList<>();

        //chi dinh viewModel
        final MovieViewModel movieViewModel = ViewModelProviders.of(MainActivity.this).get(MovieViewModel.class);


        //movieViewModel.deleteMovieViewModel(movie);
////


        //     movieViewModel.insertMovieViewModel(new Movie(3,"Naruto","Cartoon"));
        //     movieViewModel.insertMovieViewModel(new Movie(2,"Walking Dead","Honnor"));

        movieViewModel.getAllMovieViewModel().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieList = movies;

                adapter_movie = new Adapter_movie(MainActivity.this, R.layout.movie_item, movieList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter_movie);
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
                Movie movie = adapter_movie.getMovieAt(viewHolder.getAdapterPosition());

                //delete
                movieViewModel.deleteMovieViewModel(movie);
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


    }
}
