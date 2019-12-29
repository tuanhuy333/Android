package com.example.readcontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button btn_load;

    List<String> stringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContact();
            }
        });
    }
    public void showContact(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},100);
        }else {
            List<String>contactList=getContact();
            ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList);
            listView.setAdapter(adapter);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showContact();
            }
            else {
                Toast.makeText(this, "Ban phai granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public List<String> getContact(){

        stringList =new ArrayList<>();

        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //nhung truong muon chon trong provider
        String []mProjection={
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        Cursor cursor= getContentResolver().query(uri,mProjection,null,null,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){


            String ten=cursor.getString(0);
            String sdt=cursor.getString(1);

            stringList.add(ten+"-"+sdt);

            cursor.moveToNext();
        }


        return stringList;
    }

    public void initView(){
        listView=(ListView)findViewById(R.id.listView);
        btn_load=(Button)findViewById(R.id.btn_load);
    }
}
