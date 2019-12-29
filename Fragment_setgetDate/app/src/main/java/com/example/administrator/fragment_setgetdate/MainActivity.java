package com.example.administrator.fragment_setgetdate;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtMain;
    Button btnMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

                //bó dữ liệu vào bundle
                Bundle bundle=new Bundle();
                bundle.putString("tenhs",edtMain.getText().toString());

                //truyền bundle vào fragment
                fragment1 fragment1=new fragment1();
                fragment1.setArguments(bundle);

                //them fragment vào Activity
                fragmentTransaction.add(R.id.container,fragment1);

                //xac nhan
                fragmentTransaction.commit();
            }
        });

    }
    private void anhxa(){
        edtMain=(EditText)findViewById(R.id.edtMain);
        btnMain=(Button)findViewById(R.id.btnMain);
    }
}
