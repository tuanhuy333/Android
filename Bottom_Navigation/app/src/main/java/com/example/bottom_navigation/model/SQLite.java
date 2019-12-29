package com.example.bottom_navigation.model;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.example.bottom_navigation.MainActivity;

import static android.content.ContentValues.TAG;
import static com.example.bottom_navigation.fragment.sanpham_fragment.database;

public class SQLite extends SQLiteOpenHelper {




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
    //them san pham
    public void InsertSanpham(String ten_sp,int gianhapvao,int giaban,int soluong,String donvitinh,byte[] hinh_sp){
        SQLiteDatabase database=getWritableDatabase(); //thực hiện ghi vào database

        String sql="insert into sanpham values(null,?,?,?,?,?,?)";

        SQLiteStatement statement=database.compileStatement(sql);

        statement.clearBindings(); //xóa tất cả trói buộc

        statement.bindString(1,ten_sp);
        statement.bindLong(2,gianhapvao);
        statement.bindLong(3,giaban);
        statement.bindLong(4,soluong);
        statement.bindString(5,donvitinh);
        statement.bindBlob(6,hinh_sp); //lưu hình ảnh

        statement.executeInsert();
    }
    //them san pham
    public void InsertDonhang(String tenkhachhang,String ten_sp,int gianhapvao,int giaban,int soluong,String donvitinh,byte[] hinh_sp){
        SQLiteDatabase database=getWritableDatabase(); //thực hiện ghi vào database

        String sql="insert into donhang values(null,?,?,?,?,?,?,?)";

        SQLiteStatement statement=database.compileStatement(sql);

        statement.clearBindings(); //xóa tất cả trói buộc

        statement.bindString(1,tenkhachhang);
        statement.bindString(2,ten_sp);
        statement.bindLong(3,gianhapvao);
        statement.bindLong(4,giaban);
        statement.bindLong(5,soluong);
        statement.bindString(6,donvitinh);
        statement.bindBlob(7,hinh_sp); //lưu hình ảnh

        statement.executeInsert();
    }
    //sua danh ba
    public void edit_sanpham(int id,String new_name,int new_gianhapvao,int new_giaban,int new_soluong,String new_donvitinh,byte[]new_hinh){

        SQLiteDatabase database=getWritableDatabase(); //thực hiện ghi vào database
        String sql="update sanpham set ten_sp=?,gia_nhap_vao=?,gia_ban=?,soluong=?,donvitinh=? where ma_sp=?";


        SQLiteStatement statement=database.compileStatement(sql);

        statement.clearBindings(); //xóa tất cả trói buộc

        statement.bindString(1,new_name);
        statement.bindLong(2,new_gianhapvao);
        statement.bindLong(3,new_giaban);
        statement.bindLong(4,new_soluong);
        statement.bindString(5,new_donvitinh);
        statement.bindBlob(6,new_hinh); //lưu hình ảnh
        statement.bindLong(7,id);


        statement.executeUpdateDelete();

    }

    public void delete_sanpham(int id){
        String sql=String.format("delete from sanpham where ma_sp=%d",id);

        database.queryData(sql);

    }
}
