package com.example.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_engl, edt_viet;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        anhxa();

        //hienthi

        hienthi_preference();

        //
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String engl = edt_engl.getText().toString().trim();
                String viet = edt_viet.getText().toString().trim();
                //
                save_preference(engl, viet);
                hienthi_preference();

                Toast.makeText(MainActivity.this, "Đã lưu", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void hienthi_preference() {
        SharedPreferences preferences = getSharedPreferences("word", MODE_PRIVATE);
        edt_engl.setText(preferences.getString("english","my english"));
        edt_viet.setText(preferences.getString("vietnamese","my vietnamese"));

    }

    public void anhxa() {
        edt_engl = findViewById(R.id.edt_english);
        edt_viet = findViewById(R.id.edt_vietnamese);
        btn_save = findViewById(R.id.btn_save);
    }

    public void save_preference(String english, String vietnamese) {
        SharedPreferences preferences = getSharedPreferences("word", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("english", english);
        editor.putString("vietnamese", vietnamese);
        editor.apply();
    }
}
