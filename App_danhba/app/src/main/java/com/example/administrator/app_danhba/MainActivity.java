package com.example.administrator.app_danhba;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {
    EditText edt_filter;
    RecyclerView recyclerView;
    adapter_danhba adapter;
    List<row_danhba> danhbaList;

    ImageView btn_insert;
    public static SQLite database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecyclerView);

        hienthi_database();


        //xử lí thêm danh bạ vào database
        btn_insert=(ImageView) findViewById(R.id.btnAdd);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,insert_danhba.class);

                //gửi tín hiệu để vô hiệu hóa nút save của insert_danhba va nut call
                intent.putExtra("is_enable_save",true);
                intent.putExtra("is_enable_call",true);

                startActivity(intent);
            }
        });

        //xu li su kieu nhap text change cho edt
        edt_filter=(EditText)findViewById(R.id.edtSearch);

        edt_filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            //sự kiện khi chữ thay đổi
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List <row_danhba>FilteredList=new ArrayList<>();

                FilteredList=getListFiltered(danhbaList,s.toString());
                adapter.setAfterFiltered(FilteredList);



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }
    //tạo data khi chưa sử dụng database SQLite sử dụng listDanhba.add(...)


    //hiển thị data trong database
    public void hienthi_database(){
        //tao database sqlite
        database=new SQLite(this,"db_danhba.sqlite",null,1);

        //TAO BANG
        database.queryData("CREATE TABLE IF NOT EXISTS newdanhba(id_danhba INTEGER PRIMARY KEY AUTOINCREMENT,ten_lienlac NVARCHAR(100),sdt_lienlac NVARCHAR(10),hinh_lienlac BLOB)");

        //INSERT
        //database.queryData("insert into note(id_note,title_note,content_note) values(null,'Tập thể dục','Chạy bộ ở công viên 10 vòng ,hít đất 10 cái')");
        //database.queryData("insert into note(id_note,title_note,content_note) values(null,'Ăn sáng','Ăn sáng lúc 8:00 AM')");
        //database.queryData("insert into danhba(id_danhba,ten_lienlac,sdt_lienlac) values(null,'Phadas','0829942121')");


        danhbaList=new ArrayList<>();

        Cursor data=database.getData("select * from newdanhba");

        while(data.moveToNext()){

            int id_danhba=data.getInt(0);
            String ten_lienlac=data.getString(1);
            String sdt_lienlac=data.getString(2);
            byte[] hinhanh=data.getBlob(3);


            danhbaList.add(new row_danhba(id_danhba,ten_lienlac,sdt_lienlac,hinhanh));

        }
//
//
//
        adapter = new adapter_danhba(this,R.layout.row_danhba,danhbaList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();


    }
    //lấy list sau khi lọc từ sourceList và chữ muốn lọc
    private List<row_danhba> getListFiltered(List<row_danhba> sourceList,String textFiler){
        textFiler=textFiler.toLowerCase();

        //Filtered List
        List<row_danhba> FilteredList=new ArrayList<>(); //để chứa những item sau khi lọc xong

        for(row_danhba item:sourceList){

            if(item.getTen_lienlac().toLowerCase().contains(textFiler)){
                FilteredList.add(item);
            }
        }

        return FilteredList;
    }







}

