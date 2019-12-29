package com.example.administrator.ex_checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1,checkBox2,checkBox3;
    Button btn_show;
    TextView txt_kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ đến các thành phần
        checkBox1=(CheckBox) findViewById(R.id.checkbox1);
        checkBox2=(CheckBox) findViewById(R.id.checkBox2);
        checkBox3=(CheckBox) findViewById(R.id.checkBox3);
        btn_show=(Button) findViewById(R.id.button);
        txt_kq=(TextView) findViewById(R.id.textView2);

        //code

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()==true){
                    txt_kq.setText("Bạn có sở thích"+checkBox1.getText().toString());
                }
                else if(checkBox2.isChecked()==true){
                    txt_kq.setText("Bạn có sở thích"+checkBox2.getText().toString());
                }
            }
        });

    }
}
