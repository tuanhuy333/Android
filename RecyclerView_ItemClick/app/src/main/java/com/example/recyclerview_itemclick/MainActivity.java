package com.example.recyclerview_itemclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    recyclerView_adapter adapter;
    List<item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        //tao data
        list=new ArrayList<>();
        list.add(new item(1,"pen"));
        list.add(new item(2,"pencil"));
        list.add(new item(3,"T-shirt"));
        list.add(new item(4,"ruler"));

        adapter=new recyclerView_adapter(this,R.layout.row_item,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

    }
}
