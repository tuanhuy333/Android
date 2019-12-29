package com.example.retrofit2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.retrofit2.Retrofit2.APIUtils;
import com.example.retrofit2.Retrofit2.DataClient;
import com.example.retrofit2.Retrofit2.RetrofitClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button btn_upload;
    EditText edt_tenanh;

    final int CODE_PICK=111;
    String real_path="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        img=(ImageView)findViewById(R.id.img);
        btn_upload=(Button)findViewById(R.id.btn_upload);
        edt_tenanh=(EditText) findViewById(R.id.edt_ten);

        //chọn ảnh từ thư viện
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,CODE_PICK);
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }
    public void upload(){

        File file=new File(real_path);

        String file_path=file.getAbsolutePath();//lấy đường dẫn trong thiết bị

        //tạo tên + time system
        String[]name=file_path.split("\\.");
        file_path=name[0]+ System.currentTimeMillis()+"."+name[1];

        //xác nhận kiểu dữ liệu của file
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);

        MultipartBody.Part body=MultipartBody.Part.createFormData("uploaded_file",file.getName(),requestBody);

        //gọi retrofit
        DataClient dataClient= APIUtils.getData();
        //nhận về chuỗi
        Call<String>stringCall=dataClient.upload_image(body);

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response != null){
                    Toast.makeText(MainActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //upload chuoi
        DataClient dataClient1= APIUtils.getData();
        String tenanh=edt_tenanh.getText().toString();
        Call<String>stringCall1=dataClient1.insert_data(tenanh);
        stringCall1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().equals("succesed")){
                    Toast.makeText(MainActivity.this, "Insert ten anh thanh cong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CODE_PICK && resultCode== RESULT_OK && data !=null){

            Uri uri=data.getData();
            real_path=getRealPathFromURI(uri);
            Toast.makeText(this, real_path, Toast.LENGTH_LONG).show();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //lấy đường dẫn thực từ uri
    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}
