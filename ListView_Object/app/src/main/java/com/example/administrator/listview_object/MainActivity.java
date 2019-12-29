package com.example.administrator.listview_object;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<traicay> traicayArrayList;
    ListView lv;
    adapter_traicay adapterTraicay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();
        adapterTraicay =new adapter_traicay(this,R.layout.dong_traicay,traicayArrayList);

        lv.setAdapter(adapterTraicay);



    }
    public void anhxa(){
        lv=(ListView)findViewById(R.id.ListView);

        traicayArrayList =new ArrayList<>();
        traicayArrayList.add(new traicay("chuối","đặc sản bến tre",R.drawable.ic_launcher_background));
        traicayArrayList.add(new traicay("táo","là loại trái cây tốt cho sức khỏe",R.drawable.ic_launcher_foreground));
    }
}
