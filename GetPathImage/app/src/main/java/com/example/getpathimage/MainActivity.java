package com.example.getpathimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView img_thumnail;
    Button btn_pick, btn_capture;
    int CAMERA_CODE = 111;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_thumnail = findViewById(R.id.thumnail);
        btn_capture = findViewById(R.id.btn_capture);
        btn_pick = findViewById(R.id.btn_pick);




        btn_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //thuc hien xin quyen camera
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);

            }
        });

    }


    //pick image
    public void pick(View v) {
        Toast.makeText(this, "weqw", Toast.LENGTH_SHORT).show();
    }

    //result

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Nếu requestCode và resultCode phù hợp và data != null thì lấy hình ảnh chụp được
        if (requestCode == CAMERA_CODE && resultCode == RESULT_OK && data != null) {


            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            uri = getImageUri(this, bitmap);
            ///img_thumnail.setImageBitmap(bitmap);
             img_thumnail.setImageURI(uri);
            Toast.makeText(this, getRealPathFromURI(uri), Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);


    }
    //android 6 tro len thi phai xin quyen


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (requestCode == CAMERA_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_CODE);
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
}
