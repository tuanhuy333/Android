package com.example.administrator.intent;

import android.content.Intent;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    TextView txt_chuoi,txt_so,txt_mangchuoi,txt_doituong,txt_dulieuBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        anhxa();

        Intent intent=getIntent();
       Bundle bundle=intent.getBundleExtra("dulieu");


        if(bundle!= null){
            //txt_dulieuBundle.setText(bundle.getString("tensp"));
            hocsinh hs=(hocsinh)bundle.getSerializable("thongtin_hs");
            txt_dulieuBundle.setText(hs.getId()+""+hs.getTen());
        }



//        txt_chuoi.setText(intent.getStringExtra("ma_sp"));
//       txt_so.setText(intent.getIntExtra("so_trangsach",100)+"");

//      txt_mangchuoi.setText(intent.getStringArrayExtra("cacloaiao")[1]);
//        hocsinh hs=(hocsinh)intent.getSerializableExtra("thongtin_hs");
//        txt_doituong.setText(hs.getTen());
    }
    public void anhxa(){
        txt_chuoi=(TextView)findViewById(R.id.txt_getString);
        txt_so=(TextView)findViewById(R.id.txt_getInt);
        txt_mangchuoi=(TextView)findViewById(R.id.txt_getArr);
        txt_doituong=(TextView)findViewById(R.id.txt_getObject);
        txt_dulieuBundle=(TextView)findViewById(R.id.txt_dataBundle);

    }
}
