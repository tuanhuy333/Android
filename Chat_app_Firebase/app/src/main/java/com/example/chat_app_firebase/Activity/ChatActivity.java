package com.example.chat_app_firebase.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chat_app_firebase.Adapter.Adapter_Chats;
import com.example.chat_app_firebase.Model.Chat;
import com.example.chat_app_firebase.Model.User;
import com.example.chat_app_firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView_Chat;
    List<Chat>list;
    Adapter_Chats adapter_chat;
    EditText edt_message;
    ImageView img_send;

    //khai bao Firebase
    FirebaseDatabase db_firebase;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initView();


        //get data from intent
        Intent intent=getIntent();
        Bundle b=intent.getBundleExtra("data");
        u=(User)b.getSerializable("info_user");


        //change title in ActionBar
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(u.getUsername());
        actionBar.setDisplayHomeAsUpEnabled(true);


        //lay cac thong tin de luu Chat database
        //de lay UId() cua User dang login
        auth=FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        final String id_sender=firebaseUser.getUid();
        final String id_receiver=u.getKey_id();

        getDataFirebase(id_sender,id_receiver);

        //===================================them len database
        img_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeChat_toFirebase(id_sender,id_receiver,edt_message.getText().toString());

            }
        });

    }

    //nhan <- se quay ve man hinh truoc do
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void initView(){
        recyclerView_Chat=(RecyclerView)findViewById(R.id.recyclerView_Chat);
        img_send=(ImageView)findViewById(R.id.img_send);
        edt_message=(EditText)findViewById(R.id.edt_message);
    }
    public void writeChat_toFirebase(String id_sender,String id_receiver,String message){


        //de write database
        db_firebase=FirebaseDatabase.getInstance();
        reference=db_firebase.getReference();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String Uid=user.getUid();


        Chat chat=new Chat(id_sender,id_receiver,message);

        reference.child("Chats").child("Message_new").push().setValue(chat);

       // reference.child("Chats").child("Who_receiver").child(Uid).push().setValue(chat);

    }
    private void getDataFirebase(final String Uid, final String another){
        db_firebase=FirebaseDatabase.getInstance();
        reference=db_firebase.getReference();

        recyclerView_Chat=findViewById(R.id.recyclerView_Chat);

        list=new ArrayList<>();

        //read database Firebase
        final ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();

                for(DataSnapshot data:dataSnapshot.getChildren()){

                    Chat c=data.getValue(Chat.class);

                    if((c.getReceiver().equals(Uid) && c.getSender().equals(another))
                            || c.getReceiver().equals(another) && c.getSender().equals(Uid)){
                        list.add(c);
                    }



                }

                //setAdapter for RecyclerView
                adapter_chat =new Adapter_Chats(getApplicationContext(),list,u.getImage_url());
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ChatActivity.this);
                linearLayoutManager.setStackFromEnd(true);
                recyclerView_Chat.setLayoutManager(linearLayoutManager);
                adapter_chat.notifyDataSetChanged();
                recyclerView_Chat.setAdapter(adapter_chat);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        reference.child("Chats").child("Message_new").addValueEventListener(valueEventListener);
    }

    private void update_status(String status){
        reference=FirebaseDatabase.getInstance().getReference();
        FirebaseUser u=FirebaseAuth.getInstance().getCurrentUser();
        String Uid=u.getUid();

        Map<String,Object> map=new HashMap<>();
        map.put("status",status);

        reference.child("Users").child(Uid).updateChildren(map);
    }


    @Override
    protected void onResume() {
        super.onResume();
        update_status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        update_status("offline");
    }

    @Override
    protected void onStop() {
        super.onStop();
        update_status("offline");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        update_status("offline");
    }
}
