package com.example.administrator.framelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FrameLayout sẽ có các view con chồng lên nhau
        //view nào đặt trên(trong xml) sẽ hiện thị sau
        //view nào đặt sau (trong xml) sẽ hiện thị trước
    }
}
