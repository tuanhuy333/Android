package com.example.room_livedata_viewmodel;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_livedata_viewmodel.Room.Movie;

import java.util.List;

public class Adapter_movie  extends RecyclerView.Adapter {

    Context context;
    int layout;
    List<Movie>movieList;


    public Adapter_movie(Context context, int layout, List<Movie> movieList) {
        this.context = context;
        this.layout = layout;
        this.movieList = movieList;
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView ten_phim,loai_phim;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            ten_phim=(itemView).findViewById(R.id.ten_movie);
            loai_phim=(itemView).findViewById(R.id.loai_movie);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(layout,parent,false);
        RecyclerView.ViewHolder viewHolder=new ViewHoler(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie=movieList.get(position);

        ((ViewHoler)holder).ten_phim.setText(movie.getName_movie());
        ((ViewHoler)holder).loai_phim.setText(movie.getType_movie());
    }

    public Movie getMovieAt(int position){
        return movieList.get(position);
    }
    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
