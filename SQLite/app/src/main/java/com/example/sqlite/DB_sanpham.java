package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB_sanpham extends SQLiteOpenHelper {

    private static final String table_name="sanpham";
    private static final String ma_sp="ma_sp";
    private static final String ten_sp="ten_sp";
    private static final String gia_sp="gia_sp";
    private static final String soluong_sp="soluong_sp";


    public DB_sanpham(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+table_name+" ("+
                    ma_sp+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    ten_sp+" TEXT,"+
                    gia_sp+" INTEGER,"+
                    soluong_sp+" INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+table_name);
        onCreate(db);
    }

    //them san pham
    public void them_sp(sanpham sp){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(ten_sp,sp.getTen_sp());
        values.put(gia_sp,sp.getGia_sp());
        values.put(soluong_sp,sp.getSoluong_sp());

        db.insert(table_name,null,values);
        db.close();
    }

    //sua san pham

    public void sua_sp(sanpham sp,int id){

        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(ten_sp,sp.getTen_sp());
        values.put(gia_sp,sp.getGia_sp());
        values.put(soluong_sp,sp.getSoluong_sp());
        db.update(table_name,values,ma_sp+ "=? ",new String[]{id+""});

        db.close();
    }

    //xoa san pham
    public void xoa_sp(int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(table_name,"ma_sp=?",new String[]{id+""});

        db.close();
    }

    //lấy toàn bộ danh sách bảng
    public List<sanpham> getAllSanpham(){

        List<sanpham>sanphamList=new ArrayList<>();

        String query="select * from "+table_name;
        SQLiteDatabase db=getReadableDatabase();

        Cursor cursor=db.rawQuery(query,null);

        cursor.moveToFirst(); //dat con tro o dau tien
        while(cursor.isAfterLast() ==false){
            sanpham sp=new sanpham(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
            sanphamList.add(sp);
            cursor.moveToNext(); //di chuyen tiep
        }
        return sanphamList;
    }
}
