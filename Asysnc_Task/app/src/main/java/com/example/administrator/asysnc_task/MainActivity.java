package com.example.administrator.asysnc_task;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btn_load;
    ImageView img_hinh;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anh xa
        btn_load = (Button) findViewById(R.id.button);
        img_hinh = (ImageView) findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);


        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thực hiện class load hình từ internet
                new loadHinhInternet().execute("https://i.pinimg.com/originals/25/6c/27/256c272bb8ae16312ce52b1e52c11c1e.jpg");
            }
        });


    }

    //viet class để xử lí Async Task

    public class loadHinhInternet extends AsyncTask<String, Void, Bitmap> {

        Bitmap bitmaphinh = null;

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                //dùng URL để internet
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmaphinh = BitmapFactory.decodeStream(inputStream);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmaphinh;
        }
        //chạy trước
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dùng ProgressDialog
            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
            //dùng progressBar
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img_hinh.setImageBitmap(bitmap);

            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            // values[0] là i nhận được từ doInBackground

        }
    }
}
