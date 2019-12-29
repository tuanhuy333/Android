package com.example.administrator.sqlite_image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_themanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();

        btn_themanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,thaotac_img.class);

                startActivity(intent);

            }
        });

    }
    public void anhxa(){
        btn_themanh=(Button)findViewById(R.id.btn_them);
    }
}
