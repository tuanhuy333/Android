package com.example.gridview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class adapter_book extends RecyclerView.Adapter<adapter_book.ViewHolder> {


    Context context;
    int layout;
    List<book> bookList;

    public adapter_book(Context context, int layout, List<book> bookList) {
        this.context = context;
        this.layout = layout;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
        ViewHolder view_book=new ViewHolder(view);
        return view_book;
    }
    //xu ly cac su kien click
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            viewHolder.ten_sach.setText(bookList.get(i).getTen_sach());
            viewHolder.gia_sach.setText(bookList.get(i).getGia_sach()+"");
            viewHolder.hinh_sach.setImageResource(bookList.get(i).getHinh_sach());


            viewHolder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(context,book_detail.class);

                    intent.putExtra("hinh sach",bookList.get(i).getHinh_sach());
                    intent.putExtra("ten sach",bookList.get(i).getTen_sach());
                    intent.putExtra("gia sach",bookList.get(i).getGia_sach());

                    context.startActivity(intent);


                }
            });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView hinh_sach;
        TextView ten_sach,gia_sach;
        CardView cardView;
        LinearLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hinh_sach=(itemView).findViewById(R.id.hinh_book);


            cardView=(itemView).findViewById(R.id.cardview);

            container=(itemView).findViewById(R.id.container);
        }
    }
}
