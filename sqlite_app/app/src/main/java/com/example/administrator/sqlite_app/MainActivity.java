package com.example.administrator.sqlite_app;

import android.app.Dialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    database db;
    ListView ls;

    adapter_congviec adapter;
    ArrayList <congviec> arr_congviec;
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        hienthi_data();

    }
    public void hienthi_data(){
        //KHOI TAO DATABASE
        db=new database(this,"congviec.sqlite",null,1);

        //TAO BANG
        //db.queryData("CREATE TABLE IF NOT EXISTS CONGVIEC(ID INTEGER PRIMARY KEY AUTOINCREMENT,TENCONGVIEC NVARCHAR(100))");
        //INSERT
        // db.queryData("insert into CONGVIEC(ID,TENCONGVIEC) values(null,'Tập thể dục')");
        //db.queryData("delete from CONGVIEC where id=2");


        //HIENTHI
        Cursor data=db.getData("select * from CONGVIEC");

        arr_congviec=new ArrayList<>();
        while(data.moveToNext()){
            String ten=data.getString(1);
            int id=data.getInt(0);

            arr_congviec.add(new congviec(id,ten));

        }
        adapter=new adapter_congviec(this,R.layout.row_note,arr_congviec);

        ls=(ListView)findViewById(R.id.Listhienthi);
        ls.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add: dialog_them();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dialog_them(){
        final Dialog dialog=new Dialog(this); //khởi tạo
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //để ko hiển thị thanh tiêu đề phía trên
        dialog.setContentView(R.layout.dialog_them); //đặt layout hiển thị vào dialog

        //xử lý các thành phần (button ,editText,...) có trong dialog
        Button btn_them=(Button)(dialog).findViewById(R.id.btn_menuthem);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edt_cv=(EditText)(dialog).findViewById(R.id.edt_them);

                String tenmoi=edt_cv.getText().toString().trim();

                String sql=String.format("insert into CONGVIEC(ID,TENCONGVIEC) values (null,'%s')",tenmoi);

                db.queryData(sql); //thêm vào database
                dialog.dismiss();//tắt dialog
                hienthi_data(); //hiển thị data trong sqlite
            }
        });

        Button btn_huy=(Button)(dialog).findViewById(R.id.btn_menuhuy);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //...tương tự xử lý tiếp btn_them setOnclick...
        dialog.show();  //hiển thị
    }

    //edit cong viec
    public void dialog_sua(final int id, String ten){

        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);
        //click vào hiển thị tên công việc tương ứng trên editText
         final EditText edt_hienthicv=(EditText)(dialog).findViewById(R.id.edt_sua);

        edt_hienthicv.setText(ten);//sẽ lấy tương ứng ở adapter
//
//        //btn sua

        Button btn_sua=(Button)(dialog).findViewById(R.id.btn_sua);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi=edt_hienthicv.getText().toString().trim();
                String sql=String.format("update CONGVIEC set TENCONGVIEC='%s' where ID=%d",tenmoi,id);

                db.queryData(sql);

                dialog.dismiss();

                hienthi_data();
            }
        });

        //btn huy
        Button btn_huy=(Button)(dialog).findViewById(R.id.btn_huysua);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    public void xoaCV(int id){
        String sql=String.format("delete from CONGVIEC where ID=%d",id);

        db.queryData(sql);

        hienthi_data();
    }
    public void hienthiID(int id){
        test=(TextView)findViewById(R.id.test);
        test.setText("ID "+id+"");
    }

}
