package com.example.administrator.objectlistview_viewholder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<sach> arr_sach;
    adapter_listsach adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //khoi tao danh sach chua data
        arr_sach =new ArrayList<>();
        add_sach();
        //khoi tao adapter ket noi danh sach voi listview
        adapter=new adapter_listsach(MainActivity.this,R.layout.row_sach,arr_sach);
        listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);


    }

    public void add_sach(){
        arr_sach.add(new sach("Tôi thấy hoa vàng trên cỏ xanh",100000));
        arr_sach.add(new sach("Chỉ là giấc mơ",200000));
        arr_sach.add(new sach("Không thể quên ký ức ấy",150000));
        arr_sach.add(new sach("Khó thể xóa nhòa ký ức",200000));


        // arrayList.add(new SinhVien("Nguyen A","MSSV:01", R.drawable.a));
    }
}
