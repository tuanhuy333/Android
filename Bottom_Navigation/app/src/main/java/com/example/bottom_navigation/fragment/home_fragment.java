package com.example.bottom_navigation.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottom_navigation.R;
import com.example.bottom_navigation.model.SQLite;
import com.example.bottom_navigation.model.adapter_sanpham_card;
import com.example.bottom_navigation.model.sanpham;

import java.util.ArrayList;
import java.util.List;

public class home_fragment extends Fragment {
    RecyclerView recyclerView;
    adapter_sanpham_card adapter;
    List<sanpham> list;

    EditText edt_search;
    public static SQLite database;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (view).findViewById(R.id.recyclerView);



        hienthi_database();

        //chức năng search trong List
        //tìm kiếm tương ứng với thay đổi text
        edt_search=(EditText)view.findViewById(R.id.search_sp);
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //write code here
                List <sanpham>FilteredList=new ArrayList<>();

                FilteredList=getListFiltered(list,s.toString());
                adapter.setAfterFiltered(FilteredList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        //adapter.setOnClickItem(this);

        return view;
    }

    //hiển thị data trong database
    public void hienthi_database() {
        //tao database sqlite
        database = new SQLite(getActivity(), "db_sanpham.sqlite", null, 1);

        //TAO BANG
        database.queryData("CREATE TABLE IF NOT EXISTS sanpham(ma_sp INTEGER PRIMARY KEY AUTOINCREMENT,ten_sp NVARCHAR(200),gia_nhap_vao INTEGER ,gia_ban INTEGER ,soluong INTEGER,donvitinh NVARCHAR(10),hinh_sp BLOB)");

        list=new ArrayList<>();
        //lấy data từ SQLite
        Cursor data=database.getData("select * from sanpham");

        while(data.moveToNext()){

            int ma_sp=data.getInt(0);
            String ten_sp=data.getString(1);
            int gia_nhap_vao=data.getInt(2);
            int gia_ban=data.getInt(3);
            int soluong=data.getInt(4);
            String donvitinh=data.getString(5);
            byte[] hinh_sp=data.getBlob(6);

            //thêm vào list
            list.add(new sanpham(ma_sp,ten_sp,gia_nhap_vao,gia_ban,soluong,donvitinh,hinh_sp));
        }


        //set adapter
        adapter = new adapter_sanpham_card(getActivity(), R.layout.card_sanpham, list);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    //lấy list sau khi lọc từ sourceList và chữ muốn lọc
    private List<sanpham> getListFiltered(List<sanpham> sourceList,String textFiler){
        textFiler=textFiler.toLowerCase();

        //Filtered List
        List<sanpham> FilteredList=new ArrayList<>(); //để chứa những item sau khi lọc xong

        for(sanpham item:sourceList){

            if(item.getTen_sp().toLowerCase().contains(textFiler)){
                FilteredList.add(item);
            }
        }

        return FilteredList;
    }


}
