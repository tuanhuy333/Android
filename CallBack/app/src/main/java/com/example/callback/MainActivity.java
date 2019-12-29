package com.example.callback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 frag1=new fragment1();
        fragment2 frag2=new fragment2();

        frag1.setCallBack(frag2);

        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();



        transaction.add(R.id.container_fragment1,frag1);
        transaction.add(R.id.container_fragment2,frag2);


        transaction.commit();


    }
}
