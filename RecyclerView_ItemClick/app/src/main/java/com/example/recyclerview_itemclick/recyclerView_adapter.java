package com.example.recyclerview_itemclick;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class recyclerView_adapter extends RecyclerView.Adapter {

    private Context context;
    private int layout;
    private List<item> itemList;

    public recyclerView_adapter(Context context, int layout, List<item> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    //chuyen layout xml sang code
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
       viewHolder v=new viewHolder(view);
       return v;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((viewHolder)viewHolder).txt_id.setText(itemList.get(i).getId_item()+"");
        ((viewHolder)viewHolder).txt_ten.setText(itemList.get(i).getTen_item());

        ((viewHolder)viewHolder).setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int i, boolean b) {
                if(b==true){
                    //nguoi dung da nhan longclick
                    Toast.makeText(context,"Long Click"+itemList.get(i).getId_item(),Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(context,"On Click"+itemList.get(i).getId_item(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    interface ItemClickListener {
        void onClick(View v,int i,boolean b);

    }

    //viewHolder
    //gom nhung thanh phan trong row-item de chi goi findView 1 lan
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        TextView txt_id,txt_ten;



        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id=(TextView)itemView.findViewById(R.id.id_item);
            txt_ten=(TextView)itemView.findViewById(R.id.ten_item);

            itemView.setOnClickListener(this); //set su kien onClick cho view
            itemView.setOnLongClickListener(this);
        }

        private ItemClickListener itemClickListener; //khai bao interface

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }



        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);//goi interface false vi la onClick
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}
