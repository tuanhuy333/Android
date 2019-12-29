package com.example.administrator.ql_hs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database_hs extends SQLiteOpenHelper {
    public database_hs( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //create,insert,update,remove

    public void query_Data(String sql){
        SQLiteDatabase database= getWritableDatabase();

        database.execSQL(sql);
    }

    public Cursor get_Data(String sql){
        SQLiteDatabase database=getWritableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
