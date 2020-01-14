package com.example.administrator.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class adapter_hocsinh extends RecyclerView.Adapter {


    Context context;
    int layout;
    List<hocsinh> list_hs;


    public adapter_hocsinh(Context context, int layout, List<hocsinh> list_hs) {
        this.context = context;
        this.layout = layout;
        this.list_hs = list_hs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView img_hocsinh;
            public TextView txt_ten;
            TextView txt_lop;
            Button btn_chitiet;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                img_hocsinh=itemView.findViewById(R.id.hinh_hocsinh);
                txt_ten=itemView.findViewById(R.id.txt_tenhocsinh);
                txt_lop=itemView.findViewById(R.id.txt_lophocsinh);
                btn_chitiet=itemView.findViewById(R.id.btn_chitiet);
            }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View viewHocsinh = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(viewHocsinh);

        return viewHolder;
    }

    //troi buoc noi dung cua tung thanh phan trong recycle view
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        hocsinh hs = list_hs.get(i);

        //ép kiểu
        ((ViewHolder) viewHolder).txt_ten.setText(hs.getTen_hocsinh());
        ((ViewHolder) viewHolder).txt_lop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        //show something
            }
                });
    }

    @Override
    public int getItemCount() {
        return list_hs.size();
    }

    public void setAfterFiltered(List<hocsinh> FilterList) {

        list_hs = new ArrayList<>();
        list_hs.addAll(FilterList);

        notifyDataSetChanged();


    }

   

}
