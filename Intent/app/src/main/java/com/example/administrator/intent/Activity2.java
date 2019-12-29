package com.example.administrator.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    Button btn;
    TextView txthienthi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btn=(Button)findViewById(R.id.btn2);

        txthienthi=(TextView)findViewById(R.id.textView3);

        Intent i=getIntent();

        String ten=i.getStringExtra("ten");

        txthienthi.setText(ten);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent(Activity2.this,Activity1.class);
               startActivity(intent);
           }
       });
    }


}
