package com.example.bottom_navigation;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bottom_navigation.fragment.sanpham_fragment;
import com.example.bottom_navigation.fragment.home_fragment;
import com.example.bottom_navigation.fragment.donhang_fragment;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new home_fragment();

                    break;
                case R.id.navigation_sanpham:
                    fragment = new sanpham_fragment();

                    break;
                case R.id.navigation_notifications:
                    fragment = new donhang_fragment();

                    break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new home_fragment());



    }

    //viết hàm loadFragment để load những fragment khi click vào menu
    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            //replace fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
            return true;
        }
        return false;
    }



}

