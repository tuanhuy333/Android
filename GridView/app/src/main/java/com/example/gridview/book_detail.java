package com.example.gridview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class book_detail extends AppCompatActivity {
    ImageView img_chitiet;
    TextView txt_tenchitiet,txt_giachitiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        anhxa();

        Intent intent=getIntent();

        img_chitiet.setImageResource(intent.getIntExtra("hinh sach",0));
        txt_tenchitiet.setText(intent.getStringExtra("ten sach"));
        txt_giachitiet.setText(intent.getIntExtra("gia sach",0)+"");


    }

    public void anhxa(){
        img_chitiet=(ImageView)findViewById(R.id.img_bookdetail);
        txt_tenchitiet=(TextView)findViewById(R.id.txt_tensachchitiet);
        txt_giachitiet=(TextView)findViewById(R.id.txt_giasachchitiet);
    }
}
