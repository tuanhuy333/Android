package com.example.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_count;
    Button btn_cong,btn_tru;


    CountViewModel countViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


        countViewModel= ViewModelProviders.of(MainActivity.this).get(CountViewModel.class);
        displaycount();

        btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countViewModel.count++;
                displaycount();
            }
        });
        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countViewModel.count--;
                displaycount();
            }
        });
    }
    private void displaycount(){
        txt_count.setText(countViewModel.count+"");
    }
    private void initView() {
        txt_count=(TextView)findViewById(R.id.textView);
        btn_cong=(Button)findViewById(R.id.btncong);
        btn_tru=(Button)findViewById(R.id.btntru);

    }
}
