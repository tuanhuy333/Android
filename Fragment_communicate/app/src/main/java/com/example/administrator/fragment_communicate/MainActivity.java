package com.example.administrator.fragment_communicate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtMain;
    Button btnMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truy cap vao cac thanh phan cua Fragment phai lay duoc Fragment Manager
                fragment1 fragment1=(com.example.administrator.fragment_communicate.fragment1)getSupportFragmentManager().findFragmentById(R.id.fragment1);
                fragment1.change_text("Fragment 1 is changed");

            }
        });

    }
    public void anhxa(){
        txtMain=(TextView)findViewById(R.id.txtMain);
        btnMain=(Button)findViewById(R.id.btnMain);
    }
}
