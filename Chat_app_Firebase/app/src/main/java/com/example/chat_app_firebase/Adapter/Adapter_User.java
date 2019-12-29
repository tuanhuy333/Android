package com.example.chat_app_firebase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_app_firebase.Activity.ChatActivity;
import com.example.chat_app_firebase.Circle_with_Picasso.PicassoCircleTransformation;
import com.example.chat_app_firebase.Model.User;
import com.example.chat_app_firebase.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_User extends RecyclerView.Adapter {

    Context context;
    int layout;
    List<User>userList;






    public Adapter_User(Context context, int layout, List<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView avatar_user;
        TextView txt_username;
        RelativeLayout container_user;

        ImageView img_on,img_off;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar_user=(ImageView)itemView.findViewById(R.id.avatar_user);
            txt_username=(TextView)itemView.findViewById(R.id.username);
            container_user=(RelativeLayout)itemView.findViewById(R.id.container_user);
            img_on=(ImageView)itemView.findViewById(R.id.img_on);
            img_off=(ImageView)itemView.findViewById(R.id.img_off);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(layout,parent,false);

        ViewHolder viewHolder=new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final User u=userList.get(position);



        ((ViewHolder)holder).txt_username.setText(u.getUsername());

        //load anh tu url voi Picasso
        ImageView avatar_user=((ViewHolder)holder).avatar_user;
        Picasso.get()
                .load(u.getImage_url())
                .transform(new PicassoCircleTransformation())
                .into(avatar_user);

        //click vao user de chuyen sang man hinh message
        ((ViewHolder)holder).container_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //khong can dung Firebase vi userList chinh la list tu database Firebase
                Intent intent=new Intent(context.getApplicationContext(), ChatActivity.class);
                Bundle b=new Bundle();
                b.putSerializable("info_user",u);
                intent.putExtra("data",b);
                //sang ChatActivity
                context.startActivity(intent);


            }
        });

        //status on off
        if(u.getStatus().equals("online")){
            ((ViewHolder)holder).img_on.setVisibility(View.VISIBLE);
            ((ViewHolder)holder).img_off.setVisibility(View.GONE);

        }else{
            ((ViewHolder)holder).img_on.setVisibility(View.GONE);
            ((ViewHolder)holder).img_off.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
