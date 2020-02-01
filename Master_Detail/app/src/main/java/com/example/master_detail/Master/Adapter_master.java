package com.example.master_detail.Master;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.master_detail.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_master extends RecyclerView.Adapter {

    private List<String> mdata;
    private onItemClickListener mListener;

    public void setmListener(onItemClickListener listener) {
        this.mListener = listener;
    }

    //contructor
    public Adapter_master(){
        mdata = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        ViewHolder viewHolder = new ViewHolder(view,mListener);

        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_ten;
        private onItemClickListener mListener;
        //TextView text;


        public ViewHolder(@NonNull View itemView, onItemClickListener Listener) {
            super(itemView);

            mListener = Listener;
            txt_ten=itemView.findViewById(android.R.id.text1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(mdata.get(getAdapterPosition()));
                }
            });
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).txt_ten.setText(mdata.get(position));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    //xử lý sự kiện click trong recycler view
    public interface onItemClickListener {
        void onClick(String result);
    }

    //thêm data
    public void addData(List<String> data){
        if(data == null){
            return; //không làm gì hết
        }
        mdata.addAll(data);
        notifyDataSetChanged();
    }
}
