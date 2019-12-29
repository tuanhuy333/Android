package com.example.administrator.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner normal_spinner,custom_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //normal_spinner();

        custom_spinner=(Spinner)findViewById(R.id.custom_spinner);


        //fill data
        ArrayList<quocgia> list_quocgia=new ArrayList<>();

        list_quocgia.add(new quocgia("Mỹ",323800));
        list_quocgia.add(new quocgia("Việt Nam",123803));
        list_quocgia.add(new quocgia("Canada",223220));
        list_quocgia.add(new quocgia("Nhật",56800));

        adapter_quocgia adapter1=new adapter_quocgia(this,R.layout.row_quocgia,list_quocgia);

        custom_spinner.setAdapter(adapter1);

    }
    public void normal_spinner(){
        //data normal spinner
        final String quocgia[]={"Việt Nam","Lào","Thái Lan","Sinrapo","Nhật Bản"};


        normal_spinner=(Spinner)findViewById(R.id.normal_spinner);

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,quocgia);


        normal_spinner.setAdapter(adapter);

        normal_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Bạn đã chọn "+normal_spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
