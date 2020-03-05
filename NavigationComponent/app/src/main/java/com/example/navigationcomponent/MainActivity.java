package com.example.navigationcomponent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //dùng NavigationUI để update components như
        // : toolbar , action bar,collapsing bar, drawer,bottom nav



        //trước tiên gọi navController
        NavController navController=Navigation.findNavController(this,R.id.nav_host_frag);
        // toolbar , action bar,collapsing bar, drawer,bottom nav
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        //set NavigationUI
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
}
