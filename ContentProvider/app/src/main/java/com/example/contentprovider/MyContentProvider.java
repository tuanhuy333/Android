package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider {

    //thong tin content provider
    static final String PROVIDER_NAME="com.example.contentprovider.MyContentProvider";
    static final String URL="content://"+PROVIDER_NAME+"/students";
    static final Uri CONTENT_URI=Uri.parse(URL);


    //thong tin students
    static final String _ID="_id";
    static final String NAME="name";
    static final String GRADE="grade";

    //projection map
    private static HashMap<String,String>PROJECTION_MAP;

    //UriMatcher
    static final UriMatcher uriMatcher;
    static {
            uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

            uriMatcher.addURI(PROVIDER_NAME,"students",1);
            uriMatcher.addURI(PROVIDER_NAME,"students/#",2);
    }

    SQLiteDatabase db;

    //SQLite

    static final String DATABASE_NAME="DB_school";
    static final String TABLE_NAME="students";

    static final String SQL_CREATE=" CREATE TABLE " + TABLE_NAME +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name TEXT NOT NULL, " +
            " grade TEXT NOT NULL);";




    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME,null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {

        DatabaseHelper databaseHelper=new DatabaseHelper(getContext());

        db=databaseHelper.getWritableDatabase();
        Toast.makeText(getContext(), "da tao", Toast.LENGTH_SHORT).show();
        return (db==null)? false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder queryBuilder=new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);

        //xu li projection
        switch (uriMatcher.match(uri)){
            case 1:
                queryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case 2:
                queryBuilder.appendWhere(_ID+"="+uri.getPathSegments().get(1));
                break;
            default:
        }

        //xu li softOder
        if(sortOrder==null || sortOrder==""){
            sortOrder=NAME;
        }

        Cursor cursor=queryBuilder.query(db,projection,selection,selectionArgs,null,null,sortOrder);

        //dang ky de xem thay doi
        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case 1:
                return "vnd.android.cursor.dir/vnd.example.students";
            /**
             * Get a particular student
             */
            case 2:
                return "vnd.android.cursor.item/vnd.example.students";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        //them new record student
        long row_id=db.insert(TABLE_NAME,null,values);

        //nếu thêm thành công
        if(row_id > 0){
            Uri _uri= ContentUris.withAppendedId(CONTENT_URI,row_id);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case 1:
                count = db.delete(TABLE_NAME, selection, selectionArgs);
                break;

            case 2:
                String id = uri.getPathSegments().get(1);
                count = db.delete( TABLE_NAME, _ID +  " = " + id +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case 1:
                count = db.update(TABLE_NAME, values, selection, selectionArgs);
                break;

            case 2:
                count = db.update(TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }



}
