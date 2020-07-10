package com.example.recyclerview_swipe;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_user extends RecyclerView.Adapter {

    private List<User> mData;

    private static onItemClickListener mListener;

    public Adapter_user(List<User> mData) {
        this.mData = mData;
    }


    // ViewHolder cho item_user
    public class mViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView email;
        private TextView password;

        //
        public RelativeLayout viewBackground, viewForeground;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            email = itemView.findViewById(R.id.email);
            password = itemView.findViewById(R.id.password);

            //
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

            //click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(v, getAdapterPosition());
                }
            });
        }
    }





    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User u = mData.get(position);

        ((mViewHolder) holder).img.setImageResource(u.getImg());
        ((mViewHolder) holder).email.setText(u.getEmail());
        ((mViewHolder) holder).password.setText(u.getPassword());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //xu ly Click
    public interface onItemClickListener {
        void onClick(View v, int position);
    }

    public void setOnClickListener(onItemClickListener listener) {
        this.mListener = listener;

    }

    //
    public void removeItem(int position) {
        mData.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(User item, int position) {
        mData.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
