package com.example.administrator.sqlite_image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class thaotac_img extends AppCompatActivity {
    final int REQUEST_CODE_CAMERA =1;  //code để nhận dạng
    final int REQUEST_CODE_PICKIMAGE=2;
    ImageButton ibtn_chupanh,ibtn_chonanh;
    ImageView img_hienthi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thaotac_img);

        anhxa();//ánh xã các thành phần có trong giao diện để thao tác

        ibtn_chupanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//tạo Intent tới máy ảnh
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        ibtn_chonanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK); //tạo Intent pick file
                intent.setType("image/*");  //set loại file cho intent

                startActivityForResult(intent,REQUEST_CODE_PICKIMAGE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_CAMERA && resultCode==RESULT_OK && data != null){

            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            img_hienthi.setImageBitmap(bitmap);
        }
        if(requestCode==REQUEST_CODE_PICKIMAGE && resultCode==RESULT_OK && data != null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                img_hienthi.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void anhxa(){
        ibtn_chonanh =(ImageButton)findViewById(R.id.ibtn_chonanh);
        ibtn_chupanh=(ImageButton)findViewById(R.id.ibtn_chupanh);

        img_hienthi=(ImageView)findViewById(R.id.img_hienthi);
    }
}
