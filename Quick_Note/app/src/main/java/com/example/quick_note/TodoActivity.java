package com.example.quick_note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quick_note.Adapter.Adapter_tag;
import com.example.quick_note.Adapter.Adapter_todo;
import com.example.quick_note.Model.Tag;
import com.example.quick_note.Model.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    Adapter_todo adapter_todo;
    List<Todo> todoList;

    RecyclerView recyclerView_todo;

    FloatingActionButton fab;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

//        Intent intent=getIntent();
//
//        Toast.makeText(this, intent.getStringExtra("text_test"), Toast.LENGTH_SHORT).show();

        recyclerView_todo=(RecyclerView)findViewById(R.id.recyclerView_todo);
        fab=(FloatingActionButton)findViewById(R.id.fab);

        test=(TextView)findViewById(R.id.text) ;
        //todoList=new ArrayList<>();


        hienthi_database();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_addNewTodo();



            }
        });





    }
    //su kien nut Back cung (duoi man hinh dien thoai)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(TodoActivity.this,MainActivity.class));
        super.onBackPressed();
    }

    public void hienthi_database(){
        //create data
        todoList=new ArrayList<>();
        Intent intent=getIntent();
        todoList=MainActivity.db.getListTodo_byTag_id(intent.getIntExtra("tag_id",0));

        adapter_todo = new Adapter_todo(this,R.layout.row_todo,todoList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_todo.setAdapter(adapter_todo);
        adapter_todo.notifyDataSetChanged();
        recyclerView_todo.setLayoutManager(linearLayoutManager);

        //hiển thị thông báo "chưa có ...something" nếu danh sách rỗng
        if(todoList.size()!=0){
            test.setVisibility(View.INVISIBLE);
        }
    }

    public void dialog_addNewTodo(){


        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //ko hiển thị đường viền trên
        dialog.setContentView(R.layout.dialog_add_edit_todo);
        dialog.show();

        final TextInputEditText edt_addList=(dialog).findViewById(R.id.dialog_edtTodoname);
        Button btn_ok=(dialog).findViewById(R.id.dialog_btnOK);



        //click to add new list
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edt_addList.findFocus();
                String new_todo_name=edt_addList.getText().toString().trim();
                Todo new_todo=new Todo(new_todo_name,0);

                //nhan tag_id gui tu MainActivity
                Intent intent=getIntent();
                int tag_id=intent.getIntExtra("tag_id",0);

                //ko cho nhap null
                if(new_todo_name.isEmpty()){

                    Toast.makeText(TodoActivity.this, "Bạn chưa nhập tên công việc kìa !", Toast.LENGTH_SHORT).show();
                    edt_addList.requestFocus();
                }else{
                    try{
                        long create_todo=MainActivity.db.createToDo(new_todo,tag_id);


                        Toast.makeText(TodoActivity.this, "Tui thêm công việc "+new_todo_name+" rồi đó", Toast.LENGTH_SHORT).show();

                        dialog.dismiss(); //đóng dialog

                        hienthi_database();
                    }catch (Exception e){
                        Toast.makeText(TodoActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });


    }
}
