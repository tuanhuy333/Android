package com.example.administrator.app_danhba;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class insert_danhba extends AppCompatActivity {

    EditText edt_tenlienlac,edt_sdt;
    ImageView camera_capture,pick_image,temp_avatar,img_call;
    Button btn_insert,btn_save;

    final int REQUEST_CODE_PICK=111;
    final int REQUEST_CODE_CAMMERA=222;
    public int REQUEST_CALL=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_danhba);
        anhxa();

        //gán thông tin danh bạ để chỉnh sửa
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data_send");
        if(bundle != null){
            edt_tenlienlac.setText(bundle.getString("ten_lienlac"));
            edt_sdt.setText(bundle.getString("sdt"));

            byte[] Hinhanh=bundle.getByteArray("hinh");
            Bitmap bitmap=BitmapFactory.decodeByteArray(Hinhanh,0,Hinhanh.length);
           temp_avatar.setImageBitmap(bitmap);
        }

        //vo hieu hoa nut save va call neu click vao add ( vi khi do ko co du lieu trong List)
        if(intent.getBooleanExtra("is_enable_save",false) && intent.getBooleanExtra("is_enable_call",false)){
            btn_save.setEnabled(false);
            img_call.setEnabled(false);
        }


        //nếu nhập j đó vào editext sẽ enable lại nút thêm
        btn_insert.setEnabled(false);
        edt_tenlienlac.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btn_insert.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        //camera capture
        camera_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMMERA);
            }
        });
        //chọn file trong điện thoại khi click vào image view
        pick_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent implicit
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*"); //chỉ chọn hình ảnh thôi
                startActivityForResult(intent,REQUEST_CODE_PICK);

            }
        });


        //Thêm danh bạ mới vào SQLite
        btn_insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //nếu editText bị bỏ trống
                if(edt_tenlienlac.getText().toString().equals("") || edt_sdt.getText().toString().isEmpty() || temp_avatar.getDrawable() == null){
                    Toast.makeText(insert_danhba.this,"Bạn chưa nhập tên liên lạc hoặc số điện thoại hoặc chưa thêm ảnh",Toast.LENGTH_SHORT).show();

                }else {
                    //chuyển imageView sang mảng byte[] để insert vào SQLite
                    BitmapDrawable bitmapDrawable=(BitmapDrawable)temp_avatar.getDrawable();
                    Bitmap bitmap=bitmapDrawable.getBitmap();  //chuyển bitmapDrawable thành bitmap

                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                    byte[] manghinh_lienlac=byteArrayOutputStream.toByteArray();

                    //thực hiện gọi hàm InsertDanhba ở SQLite.java
                    MainActivity.database.InsertDanhba(edt_tenlienlac.getText().toString().trim(),
                            edt_sdt.getText().toString().trim(),
                            manghinh_lienlac);

                    //thông báo thêm thành công
                    Toast.makeText(insert_danhba.this,"Thêm thành công",Toast.LENGTH_SHORT).show();

                    //trở về màn hình chính
                    startActivity(new Intent(insert_danhba.this,MainActivity.class));

                }

            }
        });


        //save thay đổi

        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(edt_tenlienlac.getText().toString().equals("") || edt_sdt.getText().toString().isEmpty() || temp_avatar.getDrawable() == null){
                    Toast.makeText(insert_danhba.this,"Bạn chưa nhập tên liên lạc hoặc số điện thoại hoặc chưa thêm ảnh",Toast.LENGTH_SHORT).show();

                }else {
                    //chuyển imageView sang mảng byte[] để insert vào SQLite
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) temp_avatar.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();  //chuyển bitmapDrawable thành bitmap

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] manghinh_lienlac = byteArrayOutputStream.toByteArray();

                    Intent intent = getIntent();
                    Bundle bundle = intent.getBundleExtra("data_send");

                    //thực hiện gọi hàm edit_Danhba ở SQLite.java
                    MainActivity.database.edit_danhba(bundle.getInt("id"), edt_tenlienlac.getText().toString().trim(),
                            edt_sdt.getText().toString().trim(),
                            manghinh_lienlac);

                    //thông báo thêm thành công
                    Toast.makeText(insert_danhba.this, "Update thành cônng", Toast.LENGTH_SHORT).show();

                    //trở về màn hình chính
                    startActivity(new Intent(insert_danhba.this, MainActivity.class));
                }}
        });

        //call
        img_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xin quyen goi dien thoai
                ActivityCompat.requestPermissions(insert_danhba.this,
                        new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);


            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==REQUEST_CALL && grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //lay sdt
            Intent i=getIntent();
            Bundle b=i.getBundleExtra("data_send");
            String sdt=b.getString("sdt");
            if(b!=null){
                Intent intent = new Intent();

                intent.setAction("android.intent.action.CALL");
                intent.setData(Uri.parse("tel:"+sdt));

                startActivityForResult(intent,REQUEST_CALL);
            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //lấy hình ảnh sau khi pick ảnh
    //lấy hình ảnh sau khi chụp camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQUEST_CODE_CAMMERA && resultCode== RESULT_OK && data != null){
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");  //get("data") là mặc đinh
            temp_avatar.setImageBitmap(bitmap);
        }
        if(requestCode==REQUEST_CODE_PICK && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                temp_avatar.setImageBitmap(bitmap);
            }catch(Exception e){
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void anhxa(){

        edt_tenlienlac=(EditText)findViewById(R.id.edt_tenlienlac);
        edt_sdt=(EditText)findViewById(R.id.edt_sdt);
        btn_insert=(Button)findViewById(R.id.btn_insert);
        btn_save=(Button)findViewById(R.id.btn_save);
        img_call=(ImageView)findViewById(R.id.img_call);
        camera_capture=(ImageView)findViewById(R.id.camera_capture);
        pick_image=(ImageView)findViewById(R.id.pick_image);
        temp_avatar=(ImageView)findViewById(R.id.imgAvatar);

    }
}
