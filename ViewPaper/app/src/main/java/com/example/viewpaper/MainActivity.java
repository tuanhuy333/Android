package com.example.viewpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.viewpaper.Adapter.Adapter_viewpager;
import com.example.viewpaper.Adapter.adapterFragment;
import com.example.viewpaper.Fragment.fragment;
import com.example.viewpaper.Model.hocsinh;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    com.example.viewpaper.Adapter.adapterFragment adapterFragment;

    //image slide
    List<hocsinh> datas;
    Adapter_viewpager adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.ViewPaper);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //tạo data trong ViewPager

        datas = new ArrayList<>();
        datas.add(new hocsinh(R.drawable.ic_launcher_background, "Tuấn Huy", "9a3"));
        datas.add(new hocsinh(R.drawable.ic_launcher_foreground, "Mỹ Thanh", "9a8"));
        datas.add(new hocsinh(R.drawable.ic_launcher_background, "Tuấn Huy 1", "9a3"));
        datas.add(new hocsinh(R.drawable.ic_launcher_foreground, "Mỹ Thanh 1", "9a8"));

        adapter = new Adapter_viewpager(datas, R.layout.row_hocsinh, this);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);
        viewPager.setPageMargin(50);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Toast.makeText(MainActivity.this, positionOffsetPixels+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        adapterFragment=new adapterFragment(getSupportFragmentManager());
//        adapterFragment.addFragment(fragment.newInstance("1"),"Home");//(data,title)
//        adapterFragment.addFragment(fragment.newInstance("2"),"Cart");
//        adapterFragment.addFragment(fragment.newInstance("3"),"About");
//        adapterFragment.addFragment(fragment.newInstance("4"),"List");
//
//        //viewPaper
//        viewPager.setAdapter(adapterFragment);
//
//        viewPager.setPadding(120,0,120,0);
//
//
//        //tabLayout
//      tabLayout.setupWithViewPager(viewPager);

    }


}
