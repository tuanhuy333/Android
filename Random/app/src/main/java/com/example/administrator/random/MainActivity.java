package com.example.administrator.random;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText edt_min;
    EditText edt_max;
    Button btn_random;
    TextView txt_kq ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_min=(EditText)findViewById(R.id.edt_min);
        edt_max=(EditText)findViewById(R.id.edt_max);
        btn_random=(Button)findViewById(R.id.btn_random);
        txt_kq=(TextView)findViewById(R.id.txt_kq);



        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(edt_min.getText().length()==0 || edt_max.getText().length()==0){
                    //Toast.makeText(MainActivity.this,"Bạn chưa nhập dữ liệu",Toast.LENGTH_SHORT);
                    txt_kq.setText("Bạn chưa nhập dữ liệu");
                }
                else if(Integer.parseInt(edt_min.getText().toString()) >= Integer.parseInt(edt_max.getText().toString())){
                    txt_kq.setText("Số thứ nhất phải nhỏ hơn số thứ hai");
                }
                else{
                    Random r=new Random();
                    int min=Integer.parseInt(edt_min.getText().toString());
                    int max=Integer.parseInt(edt_max.getText().toString());
                    int so=r.nextInt(max-min+1)+min;
                    txt_kq.setText(String.valueOf(so));
                }



            }
        });



    }


}
