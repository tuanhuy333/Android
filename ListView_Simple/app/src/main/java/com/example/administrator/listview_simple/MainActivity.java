package com.example.administrator.listview_simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView lv=(ListView)findViewById(R.id.list);
        final TextView txt=(TextView)findViewById(R.id.txt_thongbao);

        ArrayList<String> arr=new ArrayList<>();
        arr.add("data1");
        arr.add("data2");
        arr.add("data3");

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arr);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt.setText(String.valueOf(position));
            }
        });

    }
}
