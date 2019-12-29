 package com.example.administrator.autocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

 public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView auto_quocgia;
    Button btn_thongbao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<model> quocgia=new ArrayList<>();
        quocgia.add("Việt Nam");
        quocgia.add("Malaysia");
        quocgia.add("Norway");
        quocgia.add("Pháp");





        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,quocgia);

        auto_quocgia=(AutoCompleteTextView)findViewById(R.id.autocomplete_quocgia);

        auto_quocgia.setAdapter(adapter);

        auto_quocgia.setThreshold(1); //khi gõ 1 kí tự thì sẽ gợi ý

        btn_thongbao=(Button)findViewById(R.id.btn_thongbao);

        btn_thongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt="dsadasd";
                Toast.makeText(MainActivity.this,txt,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
