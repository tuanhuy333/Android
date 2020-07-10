package com.example.butterknife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt)
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        ButterKnife.bind(this);


        txt.setText("dsadas");
    }

    @OnClick(R.id.btn)
    public void clickShowToast(View view){
        Toast.makeText(this, "Butter Knife", Toast.LENGTH_SHORT).show();
    }
}