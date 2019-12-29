package com.example.administrator.intent_dataresult;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_tieude,txt_gt;
    Button btn_edit;
    int REQUEST_CODE_EDIT=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_tieude=(TextView)findViewById(R.id.txt_tieude);
        txt_gt=(TextView)findViewById(R.id.txt_gtsosanh);
        btn_edit=(Button)findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Activity2.class);
                startActivityForResult(intent,REQUEST_CODE_EDIT);//request code la minh dat tuy y
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQUEST_CODE_EDIT && resultCode==RESULT_OK && data != null){

            String data_ten=data.getStringExtra("tenmoi");//lấy dữ liệu từ intent bên kia

            if(data_ten.compareTo(txt_gt.getText().toString()) ==0){
                txt_tieude.setText("Hai kết quả giống nhau");
            }else {
                txt_tieude.setText("Khác nhau");
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
