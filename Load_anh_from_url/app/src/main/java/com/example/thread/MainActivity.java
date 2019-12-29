package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button btn_load;
    ProgressBar progressBar;//hien thi khi nguoi dung cho load du lieu tu database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        img=(ImageView)findViewById(R.id.img);
        btn_load=(Button)findViewById(R.id.button3);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(MainActivity.this)
                        .load("https://anhnendep.net/wp-content/uploads/2015/07/hinh-nen-naruto-dep-hd-7.jpg")
                        .resize(300,400) //resize lại kích thước ảnh
                        .placeholder(R.drawable.ic_launcher_background) //load ảnh này trong thời gian đợi
                        .error(R.drawable.ic_image_black_24dp) //nếu không load được ảnh
                        .into(img);//đến Image View

            }
        });
    }
    public class loadfromURL extends AsyncTask<String,Integer, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            for(int i=0;i<=100;++i){
                SystemClock.sleep(10);
                publishProgress(i);
            }


            Bitmap bitmap = null;
            try {
                InputStream inputStream=new java.net.URL(strings[0]).openStream();
                 bitmap= BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
           
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //dung progressBar de cho khi load anh tu csdl mat thoi gian lau
            progressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
        }
    }
}
