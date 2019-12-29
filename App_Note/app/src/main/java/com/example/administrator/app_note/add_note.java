package com.example.administrator.app_note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class add_note extends AppCompatActivity {
    EditText edt_title;
    EditText mul_content;
    Button btn_save,btn_xoa,btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        anhxa();

        final Intent i=getIntent();


        String get_title=i.getStringExtra("title");
        mul_content.setText(i.getStringExtra("content"));
        edt_title.setText(get_title);



        //click nút xóa sẽ truyền dữ liệu id về để xóa
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();

                intent.putExtra("xoa_is_actived",1);

                intent.putExtra("id_for_update_data",i.getIntExtra("id",-1));

                setResult(RESULT_OK,intent);

                finish();

            }
        });
        //click vào nút save để lưu chỉnh sửa
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();

                //gửi tín hiệu đã click nút save
                intent.putExtra("sua_is_actived",2);
                //gửi dữ liệu từ activity này sang MainActivity
                intent.putExtra("id_for_update_data",i.getIntExtra("id",-1));
                intent.putExtra("new_title",edt_title.getText().toString().trim());
                intent.putExtra("new_content",mul_content.getText().toString().trim());

                setResult(RESULT_OK,intent);

                finish();
            }
        });
        //click vào nút add để thêm mới note

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();

                //gửi tín hiệu đã click nút add
                intent.putExtra("them_is_actived",3);

                //gửi dữ liệu thêm
                intent.putExtra("new_title",edt_title.getText().toString().trim());
                intent.putExtra("new_content",mul_content.getText().toString().trim());

                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }


    public void anhxa(){
        edt_title=(EditText)findViewById(R.id.editText);
        mul_content=(EditText)findViewById(R.id.editText2);

        btn_save=(Button)findViewById(R.id.button);
        btn_xoa=(Button)findViewById(R.id.button2);
        btn_add=(Button)findViewById(R.id.button3);
    }
}
