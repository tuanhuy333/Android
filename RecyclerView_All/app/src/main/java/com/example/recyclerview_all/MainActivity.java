package com.example.recyclerview_all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity implements Adapter_user.onItemClickListener {
    List<User> mData;
    Adapter_user adapter_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //anh xa
        RecyclerView recyclerView = findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mData = User.mData(100);
        adapter_user = new Adapter_user(mData);

        //horizontal recyclerview
        //        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), RecyclerView.HORIZONTAL, false));
        //vertical recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(adapter_user);

        //cuon toi vi tri them moi
        //    adapter_user.notifyItemInserted(mData.size() - 1);
        //    recyclerView.scrollToPosition(adapter_user.getItemCount() - 1);

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

        // Swipe
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mSimpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback mSimpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int swipePosition = viewHolder.getAdapterPosition();
            adapter_user.removeItem(swipePosition);
        }
    };


    @Override
    public void onClick(View v, int position) {

        // gửi sang Activity Detail
        sendDetailActivity(v, position);

    }

    private void sendDetailActivity(View v, int position) {
        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        // hiệu ứng cho nhiều phần tử

        androidx.core.util.Pair<View, String> p1 = androidx.core.util.Pair.create(v.findViewById(R.id.img), "img");
        androidx.core.util.Pair<View, String> p2 = androidx.core.util.Pair.create(v.findViewById(R.id.email), "txt_email");

        // shared element transition
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1, p2);
        startActivity(i, optionsCompat.toBundle());
    }
}

