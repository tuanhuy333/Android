package com.example.viewpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.viewpaper.Adapter.adapterFragment;
import com.example.viewpaper.Fragment.fragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    com.example.viewpaper.Adapter.adapterFragment adapterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=(ViewPager)findViewById(R.id.ViewPaper);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);



        adapterFragment=new adapterFragment(getSupportFragmentManager());
        adapterFragment.addFragment(fragment.newInstance("1"),"Home");//(data,title)
        adapterFragment.addFragment(fragment.newInstance("2"),"Cart");
        adapterFragment.addFragment(fragment.newInstance("3"),"About");
        adapterFragment.addFragment(fragment.newInstance("4"),"List");

        //viewPaper
        viewPager.setAdapter(adapterFragment);


        //tabLayout
      tabLayout.setupWithViewPager(viewPager);

    }







}
