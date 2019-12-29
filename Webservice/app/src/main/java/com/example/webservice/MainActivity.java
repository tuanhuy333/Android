package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edt_username,edt_password;
    Button btn_insert,btn_go;
    String url_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        edt_username=(EditText)findViewById(R.id.editText);
        edt_password=(EditText)findViewById(R.id.editText2);
        btn_insert=(Button)findViewById(R.id.button);
        btn_go=(Button)findViewById(R.id.button2);


        url_insert="http://192.168.1.96:8080/Webservice/insert.php";

        //insert data thông qua webservice
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them(url_insert);
            }
        });

        //di chuyển sang màn hình upload image

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(MainActivity.this,uploadImage.class);
                startActivity(i);
            }
        });

    }

    public void them(String url){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //trả về kết quả của insert.php
                        if(response.trim().equals("succesed")){
                            Toast.makeText(MainActivity.this,"Đã insert dữ liệu thành công",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params=new HashMap<>();

                //sử dụng update và delete cũng ở đây

                //đẩy những gì nhập ở app lên $_POST[''] ở webservice .php
                params.put("username",edt_username.getText().toString().trim());
                params.put("password",edt_password.getText().toString().trim());

                return params;
            }
        };

        //tạo Request Queue để chứa các request

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }
}
