package com.example.administrator.intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {
    Button btn;
    EditText edtgiatri;
    TextView txthienthi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        btn=(Button)findViewById(R.id.btn1);
        edtgiatri =(EditText) findViewById(R.id.editText);
        txthienthi =(TextView)findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i=new Intent(Activity1.this,Activity2.class);

               i.putExtra("ten",edtgiatri.getText().toString());

               startActivity(i);
            }
        });

    }


}
