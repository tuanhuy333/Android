package com.example.recyclerview_all;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity  implements Adapter_user.onItemClickListener {
    List<User> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //anh xa
        RecyclerView recyclerView = findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mData=User.mData(100);
        Adapter_user adapter_user = new Adapter_user(mData);

        //horizontal recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(),RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(adapter_user);

        //cuon toi vi tri them moi
        adapter_user.notifyItemInserted(mData.size()-1);
        recyclerView.scrollToPosition(adapter_user.getItemCount()-1);

        //toi uu kich thuoc
        //recyclerView.setHasFixedSize(true);

        //onClick
//        adapter_user.setOnClickListener(new Adapter_user.onItemClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Toast.makeText(MainActivity.this, mData.get(position).getEmail(), Toast.LENGTH_SHORT).show();
//            }
//        });

        adapter_user.setOnClickListener(this);

    }


    @Override
    public void onClick(View v, int position) {
        Toast.makeText(this, position+"", Toast.LENGTH_SHORT).show();
    }
}

