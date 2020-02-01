package com.example.image_manager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class add_image extends AppCompatActivity {

    EditText edt_tenhinh;
    ImageView img_hinh;
    Button btn_chonanh, btn_chupanh, btn_upload;

    Bitmap photo;
    private final static int CAMERA_CODE = 111;

    String url = "http://192.168.1.96:8080/image_manager/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        //
        edt_tenhinh = (EditText) findViewById(R.id.editText);
        img_hinh = (ImageView) findViewById(R.id.img);
        btn_chonanh = (Button) findViewById(R.id.btn_chonanh);
        btn_chupanh = (Button) findViewById(R.id.btn_chupanh);
        btn_upload = (Button) findViewById(R.id.btn_upload);


        //chụp ảnh
        //click vào hình để chụp hình trong điện thoại

        btn_chupanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1.Gọi Intent Camera
                // 2.Lấy ảnh chụp được ( @Override lại onActivityResult(..) )

                //----------------1-----------
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_CODE);






            }
        });











        //upload
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_anh(url);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            if (requestCode == CAMERA_CODE && resultCode == RESULT_OK && data != null) {
                photo = (Bitmap) data.getExtras().get("data");
                img_hinh.setImageBitmap(photo);

            }
            super.onActivityResult(requestCode, resultCode, data);

    }

    //chuyển ảnh sang base64 string
    public String getStringImage(Bitmap bitmap) {
        //tạo luồng ghi mảng byte
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //nén hình ảnh bitmap thành jpeg
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        //chuyển luồng ghi thành mảng byte
        byte[] imagebyte = byteArrayOutputStream.toByteArray();

        //mã hóa mảng byte base 64
        String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);

        return encode;

    }

    //hàm upload
    public void upload_anh(String url) {

        //tạo String request để yêu cầu nhận về chuỗi từ file php
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(add_image.this, MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("ten_image", edt_tenhinh.getText().toString().trim());
                params.put("IMG", getStringImage(photo));

                return params;
            }
        };

        //request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
