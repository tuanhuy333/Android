package com.example.sync_sqlite_mysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_name="nameDB";
    private static final int DB_verson=1;


    //table
    private static final String Table_name="Name";

    public DBHelper(Context context) {
        super(context,DB_name,null,DB_verson);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_NAME="CREATE TABLE "+Table_name+"(id INTEGER PRIMARY KEY,name TEXT,status_sync INTEGER)";
        db.execSQL(CREATE_TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name );

        // create new tables
        onCreate(db);
    }

    //insert
    public void insert_name(String name,int status_sync){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("status_sync",status_sync);
        db.insert(Table_name,null,values);
    }
    //update status_sync
    public void update_status(String name,int id){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("status_sync",1);
        db.update(Table_name,values,"id=?",new String[]{id+""});
    }
    //show All table
    public List<Name>getAll(){

        String sql="SELECT * FROM "+Table_name;

        List<Name>dataList=new ArrayList<>();

        SQLiteDatabase db=getReadableDatabase();



        Cursor c=db.rawQuery(sql,null);
        if (c.moveToFirst()) {
            do {
                Name n=new Name();
                n.setId(c.getInt(0));
                n.setName(c.getString(1));
                n.setStatus_sync(c.getInt(2));


                dataList.add(n);
            } while (c.moveToNext());
        }

        return dataList;

    }
}
