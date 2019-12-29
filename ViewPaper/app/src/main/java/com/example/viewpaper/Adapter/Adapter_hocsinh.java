package com.example.viewpaper.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpaper.Model.hocsinh;
import com.example.viewpaper.R;

import java.util.List;

public class Adapter_hocsinh extends RecyclerView.Adapter {

    Context context;
    int layout;
    List<hocsinh>hocsinhList;

    public Adapter_hocsinh(Context context, int layout, List<hocsinh> hocsinhList) {
        this.context = context;
        this.layout = layout;
        this.hocsinhList = hocsinhList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_hs;
        TextView txt_tenhs,txt_lophs;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            img_hs=(itemView).findViewById(R.id.img_hs);
            txt_tenhs=(itemView).findViewById(R.id.txt_tenhs);
            txt_lophs=(itemView).findViewById(R.id.txt_lophs);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        hocsinh hs=hocsinhList.get(position);

        ((ViewHolder)holder).txt_tenhs.setText(hs.getTen());
        ((ViewHolder)holder).txt_lophs.setText(hs.getLop());



    }

    @Override
    public int getItemCount() {
        return hocsinhList.size();
    }
}
