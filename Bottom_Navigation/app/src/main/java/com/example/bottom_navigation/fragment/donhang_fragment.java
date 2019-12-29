package com.example.bottom_navigation.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bottom_navigation.R;
import com.example.bottom_navigation.model.SQLite;
import com.example.bottom_navigation.model.adapter_donhang;
import com.example.bottom_navigation.model.donhang;

import java.util.ArrayList;
import java.util.List;

public class donhang_fragment extends Fragment{
    RecyclerView recyclerView;
    adapter_donhang adapter;
    List<donhang> list;


    EditText edt_search;
    public static SQLite database;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_donhang,container,false);


        recyclerView = (view).findViewById(R.id.recyclerView_dh);



        hienthi_database();

        return  view;
    }

    public void hienthi_database(){
        //tao database sqlite
        database = new SQLite(getActivity(), "db_sanpham.sqlite", null, 1);

        //TAO BANG
        database.queryData("CREATE TABLE IF NOT EXISTS donhang(ten_khachhang NVARCHAR(100),ma_sp INTEGER,ten_sp NVARCHAR(200),gia_nhap_vao INTEGER ,gia_ban INTEGER ,soluong INTEGER,donvitinh NVARCHAR(10),hinh_sp BLOB)");

        list=new ArrayList<>();
        //lấy data từ SQLite
        Cursor data=database.getData("select * from donhang");

        while(data.moveToNext()){
            String ten_khachhang=data.getString(0);
            int ma_sp=data.getInt(1);
            String ten_sp=data.getString(2);
            int gia_nhap_vao=data.getInt(3);
            int gia_ban=data.getInt(4);
            int soluong=data.getInt(5);
            String donvitinh=data.getString(6);
            byte[] hinh_sp=data.getBlob(7);

            //thêm vào list
            list.add(new donhang(ten_khachhang,ma_sp,ten_sp,gia_nhap_vao,gia_ban,soluong,donvitinh,hinh_sp));
        }


        //set adapter
        adapter = new adapter_donhang(getActivity(), R.layout.row_sp, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
   }

}
