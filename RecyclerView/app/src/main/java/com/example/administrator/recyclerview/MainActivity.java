package com.example.administrator.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edt_filter;
    RecyclerView recyclerView;
    adapter_hocsinh adapter;
    List<hocsinh> students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        



        recyclerView = findViewById(R.id.RecyclerView);

        init_data();

        //xu li su kieu nhap text change cho edt
        edt_filter=(EditText)findViewById(R.id.edtSearch);
        edt_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            //sự kiện khi chữ thay đổi
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List <hocsinh>FilteredList=new ArrayList<>();

                FilteredList=getListFiltered(students,s.toString());
                adapter.setAfterFiltered(FilteredList);



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void init_data(){

        //1.implementation 'com.android.support:recyclerview-v7:28.0.0'
        //2.Define class object hocsinh.java
        //3.Define row_item.xml
        //4.Define Adapter_hocsinh extend RecyclerView.Adapter<hocsinh>


        students = new ArrayList<hocsinh>();

        //Tự phát sinh 50 dữ liệu mẫu
        for (int i = 1; i <= 50; i++) {
            students.add(new hocsinh(i,"Hoc sinh "+i,"9a"+i));
        }

        adapter = new adapter_hocsinh(this,R.layout.card_hocsinh,students);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
    //lấy list sau khi lọc từ sourceList và chữ muốn lọc
    private List<hocsinh> getListFiltered(List<hocsinh> sourceList,String textFiler){
        textFiler=textFiler.toLowerCase();

        //Filtered List
        List<hocsinh> FilteredList=new ArrayList<>(); //để chứa những item sau khi lọc xong

        for(hocsinh item:sourceList){
            if(item.getTen_hocsinh().contains(textFiler)){
                FilteredList.add(item);
            }
        }

        return FilteredList;
    }
}
