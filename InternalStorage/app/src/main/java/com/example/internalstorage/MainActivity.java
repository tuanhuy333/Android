package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView txt_noidung;
    EditText editText_ghi;
    Button button_write,button_read;

    private String file_name="note.txt";//ko được là đường dẫn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        txt_noidung=(TextView)findViewById(R.id.textView);
        editText_ghi=(EditText)findViewById(R.id.editText);
        button_write=(Button)findViewById(R.id.button);
        button_read=(Button)findViewById(R.id.button2);


        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ghi_file();
            }
        });

         button_read.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 read_file();
             }
         });



    }


    //ghi file method
    private void ghi_file(){

        //tạo luồng ghi file

        try {
            FileOutputStream out = this.openFileOutput(file_name, MODE_PRIVATE);


            //ghi
            out.write(editText_ghi.getText().toString().getBytes());
            out.close();

            Toast.makeText(MainActivity.this,"File đã được ghi lại",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //read file method

    private void read_file(){

        //tạo luồng đọc file
        try {
            FileInputStream in=this.openFileInput(file_name);

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));


            StringBuilder stringBuilder=new StringBuilder();
            String s=null;

            //khi đọc các dòng khác null thì vẫn làm tiếp
            while((s=bufferedReader.readLine()) != null){
                //build String
                stringBuilder.append(s).append("\n");

            }
            txt_noidung.setText(stringBuilder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
