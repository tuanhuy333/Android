package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button btn_requeststring,btn_requestImage;
    TextView txt_getDataFromURL;
    ImageView img_getimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_getDataFromURL=(TextView)findViewById(R.id.textView);
        btn_requeststring=(Button)findViewById(R.id.button);
        btn_requestImage=(Button)findViewById(R.id.button2);
        img_getimage=(ImageView)findViewById(R.id.img);

        //gửi yêu cầu để nhận về String
        btn_requeststring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getStringFromURL().execute("http://192.168.1.96:8080/image_manager/getAll.php");
            }
        });

        //gửi yêu cầu để nhận về Image
        btn_requestImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getImageFromURL().execute("http://192.168.1.96:8080/image_manager/upload/images/hrj_1568211555.jpeg");
            }
        });



    }

    //
    class getStringFromURL extends AsyncTask<String,String,String>{
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                                        .connectTimeout(15, TimeUnit.SECONDS) //thời gian kết nối
                                        .readTimeout(3,TimeUnit.SECONDS) //thời gian đọc data
                                        .writeTimeout(10,TimeUnit.SECONDS) //thời gian ghi data
                                        .retryOnConnectionFailure(true) //kết nối lại nếu gặp lỗi
                                        .build(); //xây dựng
        //khởi tạo Moshi adapter để chuyển json thành model java

        //làm
        @Override
        protected String doInBackground(String... strings) {

            //xây dựng request
            Request.Builder builder=new Request.Builder();
            builder.url(strings[0]);

            //yêu cầu
            Request request=builder.build();

            //đáp lại
            try {
                Response response=okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //nhận kết quả
        @Override
        protected void onPostExecute(String s) {
            if(!s.equals("")){
                txt_getDataFromURL.append(s);
            }
            else{
                Toast.makeText(MainActivity.this, "Kết nối bị lỗi", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(s);
        }
    }

    //getImage from url
    class getImageFromURL extends AsyncTask<String,Void,byte[]>{
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                                .build();
        @Override
        protected byte[] doInBackground(String... strings) {
            Request.Builder builder=new Request.Builder();
            builder.url(strings[0]);

            Request request=builder.build();

            try {
                Response response=okHttpClient.newCall(request).execute();
                return response.body().bytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new byte[0];
        }

        @Override
        protected void onPostExecute(byte[] bytes) {

            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            img_getimage.setImageBitmap(bitmap);
            super.onPostExecute(bytes);
        }
    }
}

