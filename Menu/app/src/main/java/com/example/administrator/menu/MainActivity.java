package com.example.administrator.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //khởi tạo menu với phương thức onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display,menu); //dùng getMenuInflater.inflate để chuyển xml sang code

        return super.onCreateOptionsMenu(menu);
    }
    //xử lý sự kiện click

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        txt=(TextView)findViewById(R.id.txt_hienthi);
        switch(item.getItemId()){
            case R.id.setting:txt.setText(item.getTitle());
            break;
            case R.id.about:txt.setText(item.getTitle());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
