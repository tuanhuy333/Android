package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DB_sanpham db;
    ListView listView;
    List<sanpham> sanphamList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db =new DB_sanpham(this,"DB_sanpham.sqlite",null,1);

        sanphamList=new ArrayList<>();
        sanphamList=db.getAllSanpham();

        ArrayList<String> data=new ArrayList<>();

        for(sanpham item:sanphamList){
            data.add(item.getTen_sp());
        }

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);



    }
}
