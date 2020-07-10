package com.example.nation_info;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
    TextView txt_dientich, txt_danso;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar=getSupportActionBar();


        //
        img = findViewById(R.id.img);
        txt_dientich = findViewById(R.id.textView);
        txt_danso = findViewById(R.id.textView2);

        //nhận dữ liệu từ Ac1
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("dataCountry");

        String tenquocgia="",danso = "", dientich = "", maquocgia = "";
        if (b != null) {
            tenquocgia=b.getString("tenquocgia");
            danso = b.getString("danso");
            dientich = b.getString("dientich");
            maquocgia = b.getString("maquocgia"); //chữ in VN

            //update Actionbar
            actionBar.setTitle(tenquocgia);

            //update UI
            String url_img="https://img.geonames.org/flags/x/"+maquocgia.toLowerCase()+".gif";

            //load ảnh từ url
            Picasso.get().load(url_img).into(img);

            txt_dientich.setText("Diện tích : "+dientich+" km2");
            txt_danso.setText("Dân số : "+danso+" người");
        }


    }
}
