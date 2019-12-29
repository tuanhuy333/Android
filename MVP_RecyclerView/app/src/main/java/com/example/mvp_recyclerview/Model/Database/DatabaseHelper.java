package com.example.mvp_recyclerview.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mvp_recyclerview.Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="DB";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="Employee";

    private final String CREATE_DB="CREATE TABLE "+TABLE_NAME+" ("+
           "id INTEGER PRIMARY KEY AUTOINCREMENT ,"+
           "ten_employee TEXT)";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP table  IF EXISTS "+TABLE_NAME);
         onCreate(db);
    }
    //them
    public void them(Employee employee){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("ten_employee",employee.getTenNhanVien()); //giong vs cot ten_employee trong SQLite


        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    //sua

    public void sua_sp(Employee employee){

        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("ten_employee",employee.getTenNhanVien()); //dat vao ten moi


        db.update(TABLE_NAME,values, "id = ? ",new String[]{employee.getId()+""});

        db.close();
    }

    //xoa
    public void xoa_sp(Employee employee){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{employee.getId()+""});

        db.close();
    }

    //lấy toàn bộ danh sách bảng
    public List<Employee> getAll(){

        List<Employee>employeeList=new ArrayList<>();

        String query="select * from "+TABLE_NAME;
        SQLiteDatabase db=getReadableDatabase();

        Cursor cursor=db.rawQuery(query,null);

        cursor.moveToFirst(); //dat con tro o dau tien
        while(!cursor.isAfterLast() ){
            Employee employee=new Employee(cursor.getInt(0),cursor.getString(1));
            employeeList.add(employee);
            cursor.moveToNext(); //di chuyen tiep
        }
        return employeeList;
    }



}
