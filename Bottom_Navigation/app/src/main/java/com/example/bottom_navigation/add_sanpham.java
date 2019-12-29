package com.example.bottom_navigation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static com.example.bottom_navigation.fragment.home_fragment.database;

public class add_sanpham extends AppCompatActivity {
    ImageView img_sp;
    EditText edt_tensp,edt_gianhapvao,edt_giaban,edt_soluong,edt_donvitinh;
    Button btn_them;
    //mã request
    final int REQUEST_CODE_PICK=111;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sanpham);
        anhxa();


        //click vào hình để chọn hình từ điện thoại
        img_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent implicit
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*"); //chỉ chọn hình ảnh thôi
                startActivityForResult(intent,REQUEST_CODE_PICK);


            }
        });

        //click thêm sản phẩm vào database
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển imageView sang mảng byte[] để insert vào SQLite

                BitmapDrawable bitmapDrawable=(BitmapDrawable)img_sp.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();  //chuyển bitmapDrawable thành bitmap

                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] manghinh=byteArrayOutputStream.toByteArray();

                //thực hiện gọi hàm Insert ở SQLite.java
                database.InsertSanpham(edt_tensp.getText().toString().trim(),
                        Integer.parseInt(edt_gianhapvao.getText().toString()),
                        Integer.parseInt(edt_giaban.getText().toString()),
                        Integer.parseInt(edt_soluong.getText().toString()),
                        edt_donvitinh.getText().toString().trim(),
                        manghinh);

                //thông báo thêm thành công
                Toast.makeText(add_sanpham.this,"Thêm thành công",Toast.LENGTH_SHORT).show();

            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQUEST_CODE_PICK && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                img_sp.setImageBitmap(bitmap);
            }catch(Exception e){
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void anhxa(){
        img_sp=(ImageView)findViewById(R.id.img_sp);
        edt_tensp=(EditText)findViewById(R.id.edt_tensp);
        edt_gianhapvao=(EditText)findViewById(R.id.edt_gianhapvao);
        edt_giaban=(EditText)findViewById(R.id.edt_giaban);
        edt_soluong=(EditText)findViewById(R.id.edt_soluong);
        edt_donvitinh=(EditText)findViewById(R.id.edt_donvitinh);
        btn_them=(Button)findViewById(R.id.btn_them);
    }
}
