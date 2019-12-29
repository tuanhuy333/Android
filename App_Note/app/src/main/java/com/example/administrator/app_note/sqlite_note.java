package com.example.administrator.app_note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class sqlite_note extends SQLiteOpenHelper {

    private final String TABLE_NAME="note";
    private final String COLUMN_ID_NOTE="id_note";
    private final String COLUMN_TITLE_NOTE="title_note";
    private final String COLUMN_CONTENT_NOTE="content_note";


    public sqlite_note( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public void queryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }
    //select
    public Cursor getData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }


}
