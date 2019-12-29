package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList list_sp=new ArrayList<sanpham>();
        list_sp.add(new sanpham(1,"mì gói"));
        list_sp.add(new sanpham(2,"Dầu gội"));
    }
}
