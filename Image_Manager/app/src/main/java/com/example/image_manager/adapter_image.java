package com.example.image_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adapter_image extends RecyclerView.Adapter{
    Context context;
    int layout;
    List<image>imageList;


    public adapter_image(Context context, int layout, List<image> imageList) {
        this.context = context;
        this.layout = layout;
        this.imageList = imageList;
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public viewHolder(@NonNull View itemView) {


            super(itemView);
            img=(ImageView)(itemView).findViewById(R.id.hinh);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(layout,parent,false);

        //khoi tao viewHolder
        viewHolder viewHolder=new viewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        image i=imageList.get(position);

        String url_image=i.getUrl_image();

        Picasso.with(context.getApplicationContext())
                .load(url_image)
                .resize(200,300)
                .centerCrop()
                .into(((viewHolder) holder).img);

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
