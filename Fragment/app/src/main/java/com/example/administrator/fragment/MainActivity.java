package com.example.administrator.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void getFragment(View view){
        FragmentManager fragmentManager=getSupportFragmentManager();//cho phep add,replace,remove fragment
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        switch(view.getId()){
            case R.id.button1:fragmentTransaction.add(R.id.container,new frag1());break;
            case R.id.button2:fragmentTransaction.add(R.id.container,new frag2());break;
        }
        fragmentTransaction.commit();
    }

}
