package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;
    Button btnSave, btnreadFromExternalStorage;
    EditText myInputText;

    private String filename = "MySampleFile.txt";
    private String filepath = "MyFileStorage";

    File myExternalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //kiểm tra bộ nhớ ngoài có sẵn ko và có chỉ đọc (read only) ko
        //nếu "ko có sẵn" và "chỉ đọc" thì ko cho ghi
        if (!isExternalStorageAvaiable() || isExternalStorageReadOnly()) {
            btnSave.setEnabled(false);
        } else {
            //yourfile = new File(đường dẫn , tên file)
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }
    }

    private void initView() {
        myInputText = (EditText) findViewById(R.id.myInputText);
        responseText = (TextView) findViewById(R.id.responseText);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnreadFromExternalStorage = (Button) findViewById(R.id.btnDisplay);
        btnreadFromExternalStorage.setOnClickListener(this);
    }

    //kiểm tra thiết bị có bộ nhớ ngoài ko
    public boolean isExternalStorageAvaiable() {
        String externalStage = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED == externalStage) {
            return true;
        }
        return false;
    }

    //kiểm tra bộ nhớ ngoài chỉ đọc
    public boolean isExternalStorageReadOnly() {
        String externalStage = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY == externalStage) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        String myData = "";
        switch (v.getId()) {
            case R.id.btnSave:

                try {
                    //ghi
                    FileOutputStream outputStream = new FileOutputStream(myExternalFile);
                    outputStream.write(myInputText.getText().toString().getBytes());
                    outputStream.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                myInputText.setText("");
                responseText.setText("Dữ liệu đã được lưu vào bộ nhớ ngoài");

                break;
            case R.id.btnDisplay:

                try {
                    FileInputStream inputStream = new FileInputStream(myExternalFile);
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

                    String str;
                    while ((str = bufferedReader.readLine()) != null) { //sẽ đọc từng dòng
                        myData += str; //lưu vào biến string
                    }
                    dataInputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                myInputText.setText(myData);
                responseText.setText("Được lấy ra từ bộ nhớ ngoài");
                break;
        }
    }
}
