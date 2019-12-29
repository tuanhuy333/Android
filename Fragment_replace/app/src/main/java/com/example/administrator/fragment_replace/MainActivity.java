package com.example.administrator.fragment_replace;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void goiFragment(View view){

        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        android.support.v4.app.Fragment fragment=null;

        switch(view.getId()){
            case R.id.btn1:fragment =new Fragment_1();//su dung đa hình
            break;
            case R.id.btn2:fragment=new Fragment_2();
            break;

        }
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }

}
