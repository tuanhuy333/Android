package com.example.administrator.sharedpreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView txt_hienthikq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt_hienthikq =(TextView)findViewById(R.id.txt_hienthiuser);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("my_data",MODE_PRIVATE);

        txt_hienthikq.setText(sp.getString("user","0"));

    }
}
