package com.example.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<book> bookArrayList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookArrayList=new ArrayList<>();
        bookArrayList.add(new book("Khi người lớn cô đơn",10000,R.drawable.ic_launcher_background));
        bookArrayList.add(new book("Khi nào hết buồn",120000,R.drawable.ic_launcher_background));
        bookArrayList.add(new book("Tuổi trẻ vui vẻ",20000,R.drawable.ic_launcher_background));
        bookArrayList.add(new book("Những kẻ xuất chúng",30000,R.drawable.ic_launcher_background));
        bookArrayList.add(new book("Sao em ít nói đến vậy?",40000,R.drawable.ic_launcher_background));


        adapter_book adapter=new adapter_book(this,R.layout.card_book,bookArrayList);

        recyclerView=(RecyclerView)findViewById(R.id.recycleView);


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

    }
}
