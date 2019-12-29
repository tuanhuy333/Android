package com.example.administrator.sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edt_user,edt_password;

    Button btn_submit;
    TextView txt_hienthi;

    CheckBox chbox_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();
        //goi sharedPreference

        final SharedPreferences prf=getApplicationContext().getSharedPreferences("my_data",MODE_PRIVATE);

        final SharedPreferences.Editor editor=prf.edit();

        txt_hienthi.setText(prf.getString("user","ko co"));// dat mac dinh khi load lai app

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_user.getText().length()==0){
                    txt_hienthi.setText("Bạn chưa nhập user");
                }else {
                    if(chbox_submit.isChecked()==true){
                        editor.putString("user",edt_user.getText().toString());
                        editor.commit();

                        Intent intent =new Intent(MainActivity.this,Main2Activity.class);

                        startActivity(intent);

                    }
                }



            }
        });


        //lay prf

       // SharedPreferences prf1=getSharedPreferences("user",MODE_PRIVATE);










    }

    public void anhxa(){
        edt_user=(EditText)findViewById(R.id.edt_user);
        edt_password=(EditText)findViewById(R.id.edt_password);

        btn_submit=(Button)findViewById(R.id.btn_submit);
        chbox_submit=(CheckBox)findViewById(R.id.chbox_save) ;
        txt_hienthi=(TextView)findViewById(R.id.txt_hienthi);
    }
}
