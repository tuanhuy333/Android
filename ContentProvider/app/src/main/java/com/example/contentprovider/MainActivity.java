package com.example.contentprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btn_load,btn_writeContentProvider;

    List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //write content provider
        btn_writeContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ContentValues contentValues=new ContentValues();
                contentValues.put(MyContentProvider.NAME,"mythanh");
                contentValues.put(MyContentProvider.GRADE,"9a8");

                Uri uri=getContentResolver().insert(MyContentProvider.CONTENT_URI,contentValues);

                Toast.makeText(MainActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        //load content provider
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringList =new ArrayList<>();

                // Retrieve student records


                String URL = "content://com.example.contentprovider.MyContentProvider";

                Uri students = Uri.parse(URL);

               // CursorLoader cursorLoader=new CursorLoader(MainActivity.this,students,null,null,null,"name");
               // Cursor c =cursorLoader.loadInBackground();
                Cursor c=getContentResolver().query(students,null,null,null,"name");
                c.moveToFirst();

                do{

                    String id=c.getString(c.getColumnIndex(MyContentProvider._ID)) ;
                    String name=c.getString(c.getColumnIndex( MyContentProvider.NAME)) ;
                    String grade=c.getString(c.getColumnIndex( MyContentProvider.GRADE));

                    stringList.add(id+"-"+name+"-"+grade);

                } while (c.moveToNext());




               ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,stringList);
                listView.setAdapter(adapter);
            }
        });

    }



    public void initView(){
        listView=(ListView)findViewById(R.id.listView);
        btn_load=(Button)findViewById(R.id.btn_load);
        btn_writeContentProvider=(Button)findViewById(R.id.btn_writeContentProvider);
    }
}
