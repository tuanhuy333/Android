package com.example.administrator.app_note;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;


import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    sqlite_note database;
    FloatingActionButton btn_add;
    ListView listView;
    adapter_note adapter_note;
    ArrayList<note>list_data;
    int REQUEST_CODE=111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        hienthi_database();

        //click vào từng view trong list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i=new Intent(MainActivity.this,add_note.class);

                i.putExtra("id",list_data.get(position).getId_note());
                i.putExtra("title",list_data.get(position).getTitle_note());
                i.putExtra("content",list_data.get(position).getContent_note());


                startActivityForResult(i,REQUEST_CODE);

               // Toast.makeText(MainActivity.this,list_data.get(position),Toast.LENGTH_SHORT).show();
            }
        });





        //click vào FAB(Floating Action Button) thì chuyển sang màn hình add_note
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,add_note.class);
                startActivityForResult(i,REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode== REQUEST_CODE && resultCode==RESULT_OK && data!=null){

            int xoa_is_actived=data.getIntExtra("xoa_is_actived",0);
            int sua_is_actived=data.getIntExtra("sua_is_actived",0);
            int them_is_actived=data.getIntExtra("them_is_actived",0);

            //nếu tín hiệu trả về là 1 ( đã click nút delete) thì gọi hàm delete_note()
            if(xoa_is_actived==1){

                delete_note(data.getIntExtra("id_for_update_data",-1));
            }
            if(sua_is_actived==2){
                int id=data.getIntExtra("id_for_update_data",-1);
                String new_title=data.getStringExtra("new_title");
                String new_content=data.getStringExtra("new_content");

                //gọi hàm edit_note(id,new_title,new_content)
                edit_note(id,new_title,new_content);
            }
            if(them_is_actived==3){

                String new_title=data.getStringExtra("new_title");
                String new_content=data.getStringExtra("new_content");

                //gọi hàm add_note(id,new_title,new_content)
                add_note(new_title,new_content);
            }
        }



        super.onActivityResult(requestCode, resultCode, data);
    }
    //xoa note
    public void delete_note(int id){
        String sql=String.format("delete from note where id_note=%d",id);

        database.queryData(sql);

        hienthi_database();
    }

    //sua note
    public void edit_note(int id,String new_title,String new_content){


                String sql=String.format("update note set title_note='%s',content_note='%s' where id_note=%d",new_title,new_content,id);

                database.queryData(sql);

                hienthi_database();


    }
    //thêm note
    public void add_note(String new_title,String new_content){


        String sql=String.format("insert into note(id_note,title_note,content_note) values(null,'%s','%s')",new_title,new_content);

        database.queryData(sql);

        hienthi_database();


    }
    public void hienthi_database(){
        //tao database sqlite
        database=new sqlite_note(this,"db_note.sqlite",null,1);

        //TAO BANG
        database.queryData("CREATE TABLE IF NOT EXISTS note(id_note INTEGER PRIMARY KEY AUTOINCREMENT,title_note NVARCHAR(100),content_note NVARCHAR(100))");

        //INSERT
       //database.queryData("insert into note(id_note,title_note,content_note) values(null,'Tập thể dục','Chạy bộ ở công viên 10 vòng ,hít đất 10 cái')");
       //database.queryData("insert into note(id_note,title_note,content_note) values(null,'Ăn sáng','Ăn sáng lúc 8:00 AM')");


        list_data=new ArrayList<>();

        Cursor data=database.getData("select * from note");

        while(data.moveToNext()){

            int id=data.getInt(0);
            String title=data.getString(1);
            String content=data.getString(2);


            list_data.add(new note(id,title,content));

        }


        adapter_note=new adapter_note(this,R.layout.row_note,list_data);

        listView.setAdapter(adapter_note);
        adapter_note.notifyDataSetChanged();

    }
    public void anhxa(){

        btn_add=(FloatingActionButton) findViewById(R.id.btn_add_note);
        listView=(ListView)findViewById(R.id.main_note);
    }
}
