package com.example.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_permission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_permission=findViewById(R.id.btn);
        btn_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosomething();

            }
        });
    }


    public void dosomething(){

        //neu ban build lon hon ban marshmallow 6.0 (android 6.0) va xac dinh ban ko duoc cho cap quyen
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){

            //thi xin quyen

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},100);

        }
        else {
            //do something
            Toast.makeText(this, "Ban co quyen...do something now!", Toast.LENGTH_SHORT).show();
        }
    }

    //ket qua sau khi xin quyen

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dosomething();
            }else {
                Toast.makeText(this, "Ban da ko cho phep quyen nay", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
