package com.example.administrator.intent_dataresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {
    EditText edt_data;
    Button btn_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        edt_data=(EditText)findViewById(R.id.edt_dulieu);
        btn_change=(Button)findViewById(R.id.btn_change);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(); //để nhận du lieu
                String ten=edt_data.getText().toString();
                intent.putExtra("tenmoi",ten);

                setResult(RESULT_OK,intent); //đặt request code và intent vào kết quả

                finish(); //kết thúc màn hình hiện tại

            }
        });
    }
}
