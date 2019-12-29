package com.example.quick_note;

import android.app.Dialog;
import android.os.Bundle;

import com.example.quick_note.Adapter.Adapter_tag;
import com.example.quick_note.Model.Tag;
import com.example.quick_note.SQLite.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static DatabaseHelper db;

    RecyclerView recyclerView;
    Adapter_tag adapter_tag;
    List<Tag> tagList;


    LinearLayout add_container;

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        db=new DatabaseHelper(getApplicationContext());




        add_container=(LinearLayout)findViewById(R.id.add_container);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        textView=(TextView)findViewById(R.id.text_main);




        hienthi_database();



        //click add new list
        add_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_addNewList();

            }
        });

    }
    public void hienthi_database(){
        tagList=new ArrayList<>();
        tagList=db.getAllTags();
        adapter_tag=new Adapter_tag(MainActivity.this,R.layout.card,tagList);


        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerView.setAdapter(adapter_tag);
        adapter_tag.notifyDataSetChanged();

        //nếu chưa có danh sách thì hiển thị text
        if(tagList.size() != 0){
            textView.setVisibility(View.INVISIBLE);
        }
    }

    public void dialog_addNewList(){


        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //ko hiển thị đường viền trên
        dialog.setContentView(R.layout.dialog_add_edit_list);
        dialog.show();

        final TextInputEditText edt_addList=(dialog).findViewById(R.id.dialog_edtListname);
        Button btn_ok=(dialog).findViewById(R.id.dialog_btnOK);



        //click to add new list
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_tag_name=edt_addList.getText().toString().trim();
                Tag new_tag=new Tag(new_tag_name);
                if(new_tag_name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập tên danh sách kìa !", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        long create_tag=db.createTag(new_tag);


                        Toast.makeText(MainActivity.this, "Tui thêm danh sách "+new_tag_name+" rồi đó", Toast.LENGTH_SHORT).show();

                        dialog.dismiss(); //đóng dialog

                        hienthi_database();
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    //Toast.makeText(MainActivity.this, edt_addList.getText().toString().trim(), Toast.LENGTH_SHORT).show();

                }
                }

        });


    }

}
