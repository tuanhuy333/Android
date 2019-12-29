package com.example.chat_app_firebase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat_app_firebase.Circle_with_Picasso.PicassoCircleTransformation;
import com.example.chat_app_firebase.Model.Chat;
import com.example.chat_app_firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Chats extends RecyclerView.Adapter {

        Context context;

        List<Chat> chatList;

        String url_image_user;


    public Adapter_Chats(Context context, List<Chat> chatList, String url_image_user) {
        this.context = context;
        this.chatList = chatList;
        this.url_image_user=url_image_user;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

    ImageView img_chat;
    TextView message;
    RelativeLayout container_chat;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        img_chat=(ImageView)itemView.findViewById(R.id.img_chat);
        message=(TextView)itemView.findViewById(R.id.message);
        container_chat=(RelativeLayout)itemView.findViewById(R.id.container_chat);
    }
}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=null;
        if(viewType==1){
           v=inflater.inflate(R.layout.chat_item_left,parent,false);
        }else if(viewType==2){
           v=inflater.inflate(R.layout.chat_item_right,parent,false);
        }

        Adapter_Chats.ViewHolder viewHolder=new Adapter_Chats.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Chat chat=chatList.get(position);



        ((ViewHolder)holder).message.setText(chat.getMessage());

        ImageView avatar=((ViewHolder)holder).img_chat;
        Picasso.get()
                .load(url_image_user)
                .transform(new PicassoCircleTransformation())
                .into(avatar);


    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public int getItemViewType(int position) {

        Chat chat=chatList.get(position);



        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String Uid=firebaseUser.getUid();

        if(chat.getSender().equals(Uid) ){
           return 2;
        }else {
            return 1;
        }


    }
}
