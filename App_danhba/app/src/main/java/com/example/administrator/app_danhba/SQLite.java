package com.example.administrator.app_danhba;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class SQLite extends SQLiteOpenHelper {

    private final String TABLE_NAME="note";
    private final String COLUMN_ID_NOTE="id_note";
    private final String COLUMN_TITLE_NOTE="title_note";
    private final String COLUMN_CONTENT_NOTE="content_note";


    public SQLite( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
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
    //them danh ba
    public void InsertDanhba(String ten_lienlac,String sdt,byte[] hinh_lienlac){
        SQLiteDatabase database=getWritableDatabase(); //thực hiện ghi vào database

        String sql="insert into newdanhba values(null,?,?,?)";

        SQLiteStatement statement=database.compileStatement(sql);

        statement.clearBindings(); //xóa tất cả trói buộc

        statement.bindString(1,ten_lienlac);
        statement.bindString(2,sdt);
        statement.bindBlob(3,hinh_lienlac); //lưu hình ảnh

        statement.executeInsert();
    }
    //sua danh ba
    public void edit_danhba(int id,String new_name,String new_sdt,byte[]new_hinh){

        SQLiteDatabase database=getWritableDatabase(); //thực hiện ghi vào database
        String sql="update newdanhba set ten_lienlac=?,sdt_lienlac=?,hinh_lienlac=? where id_danhba=?";


        SQLiteStatement statement=database.compileStatement(sql);

        statement.clearBindings(); //xóa tất cả trói buộc

        statement.bindString(1,new_name);
        statement.bindString(2,new_sdt);
        statement.bindBlob(3,new_hinh); //lưu hình ảnh
        statement.bindLong(4,id);


        statement.executeUpdateDelete();

    }
    public void delete_danhba(int id){
        String sql=String.format("delete from newdanhba where id_danhba=%d",id);

        MainActivity.database.queryData(sql);


    }
}
