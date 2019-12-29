package com.example.chat_app_firebase.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.chat_app_firebase.Adapter.Adapter_Fragment;
import com.example.chat_app_firebase.Adapter.Adapter_User;
import com.example.chat_app_firebase.DangNhapActivity;
import com.example.chat_app_firebase.Fragment.Fragment;
import com.example.chat_app_firebase.Model.User;
import com.example.chat_app_firebase.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskAcitivity extends AppCompatActivity {

    //khai bao view

    ViewPager viewPager;
    TabLayout tabLayout;

    List<User> userList;

    com.example.chat_app_firebase.Adapter.Adapter_Fragment adapterFragment;

    //khai bao Firebase
    FirebaseDatabase db_firebase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_acitivity);


        viewPager = (ViewPager) findViewById(R.id.ViewPaper);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);





        reference = FirebaseDatabase.getInstance().getReference();

        hienthiuser_actionbar();


        adapterFragment = new Adapter_Fragment(getSupportFragmentManager());
        adapterFragment.addFragment(Fragment.newInstance("fragment_users"), "Users");
        //adapterFragment.addFragment(Fragment.newInstance("fragment_chats"),"Chats");
        adapterFragment.addFragment(Fragment.newInstance("fragment_profile"), "Profile");


        //viewPaper
        viewPager.setAdapter(adapterFragment);


        //tabLayout
        tabLayout.setupWithViewPager(viewPager);


    }


    //==================================menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                startActivity(new Intent(TaskAcitivity.this, DangNhapActivity.class));
                break;
//            case R.id.edit:
//                Toast.makeText(MainActivity.this,item.getTitle
//                        (),Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.contact:
//                Toast.makeText(MainActivity.this,item.getTitle
//                        (),Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.address:
//                Toast.makeText(MainActivity.this,item.getTitle
//                        (),Toast.LENGTH_SHORT).show();
//                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hienthiuser_actionbar() {

        reference = FirebaseDatabase.getInstance().getReference();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String Uid = user.getUid();


        //read database Firebase
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    User u = data.getValue(User.class);

                    if (u.getKey_id().equals(Uid)) {
                        ActionBar actionBar = getSupportActionBar();
                        actionBar.setTitle("Hi ," + u.getUsername());

                      //     updateUser(data.getKey(),"dsad","1212","fqwqw");
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        reference.child("Users").addValueEventListener(valueEventListener);
    }

    private void update_status(String status) {
        reference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
        String Uid = u.getUid();

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

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


