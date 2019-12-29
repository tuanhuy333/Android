package com.example.webservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class uploadImage extends AppCompatActivity {
    ImageView img_camera,img_load;
    Button btn_upload;
    Bitmap photo;
    String url="http://192.168.1.96:8080/Webservice/upload_image.php";
    private static int CAMERA_CODE=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        //
        img_camera=(ImageView)findViewById(R.id.imageView);
        btn_upload=(Button)findViewById(R.id.button3);
        img_load=(ImageView)findViewById(R.id.imageView2);

        //load ảnh từ internet dùng thư viện Picasso
        Picasso.with(getApplicationContext())
                .load("http://192.168.1.96:8080/Webservice/upload/images/1580608476_1568117320.jpeg")
                .resize(500,100)
                .centerCrop()
                .into(img_load);
    //click vào hình để chụp hình trong điện thoại

        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_CODE);


            }
        });


        //click để upload image lên server thông qua webservice php
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upload_anh(url);
            }
        });

    }
    //nhận ảnh chụp được và show ra image view


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CAMERA_CODE && resultCode==RESULT_OK && data != null){
            photo=(Bitmap)data.getExtras().get("data");
            img_camera.setImageBitmap(photo);

        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    //chuyển ảnh sang base64 string
    public String getStringImage(Bitmap bitmap){
        //tạo luồng ghi mảng byte
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        //nén hình ảnh bitmap thành jpeg
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        //chuyển luồng ghi thành mảng byte
        byte[] imagebyte=byteArrayOutputStream.toByteArray();

        //mã hóa mảng byte base 64
        String encode= Base64.encodeToString(imagebyte,Base64.DEFAULT);

        return encode;

    }

    //hàm upload
    public void upload_anh(String url){

        //tạo String request để yêu cầu nhận về chuỗi từ file php
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();


                params.put("IMG",getStringImage(photo));

                return params;
            }
        };

        //request queue
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
