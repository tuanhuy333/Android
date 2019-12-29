package com.example.administrator.giaotiep_fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt_tieude;
    Button btn_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt_tieude=(TextView)findViewById(R.id.textView);
        btn_change =(Button)findViewById(R.id.button);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              A fragmentA=(A)getSupportFragmentManager().findFragmentById(R.id.fragmenta);
                fragmentA.txta.setText("Khi nào sẽ về");

            }
        });

    }
}
