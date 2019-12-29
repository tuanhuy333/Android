package com.example.chat_app_firebase.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.chat_app_firebase.Adapter.Adapter_User;
import com.example.chat_app_firebase.Model.User;
import com.example.chat_app_firebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class test extends AppCompatActivity {

    RecyclerView recyclerView_test;
    List<User>userList;
    Adapter_User adapter_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        recyclerView_test=(RecyclerView)findViewById(R.id.recyclerView_test);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        userList=new ArrayList<>();
        reference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();

                for (DataSnapshot data:dataSnapshot.getChildren()){
                    User u=data.getValue(User.class);
                    userList.add(u);
                }


                //setAdapter for RecyclerView
                adapter_user=new Adapter_User(test.this,R.layout.user_item,userList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(test.this);

                recyclerView_test.setLayoutManager(linearLayoutManager);
                recyclerView_test.setAdapter(adapter_user);
                Toast.makeText(test.this, "Load thanh cong", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
