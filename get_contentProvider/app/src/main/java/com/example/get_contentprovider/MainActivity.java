package com.example.get_contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String URL = "content://com.example.contentprovider.MyContentProvider";



        Uri students = Uri.parse(URL);

        // CursorLoader cursorLoader=new CursorLoader(MainActivity.this,students,null,null,null,"name");
        // Cursor c =cursorLoader.loadInBackground();
        Cursor c=getContentResolver().query(students,null,null,null,"name");
        c.moveToFirst();

        do{

            String id=c.getString(c.getColumnIndex("_id")) ;
            String name=c.getString(c.getColumnIndex( "name")) ;
            String grade=c.getString(c.getColumnIndex( "grade"));

            Toast.makeText(this, id+name+grade, Toast.LENGTH_SHORT).show();

        } while (c.moveToNext());

    }
}
