package com.shf.app35_data_storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.shf.app35_data_storage.Constants.TABLE_NAME;

public class Dao {

    private static final String TAG = "DAO";
    private final MyDatabaseHelper helper;

    public Dao(Context context) {
//        创建数据库
        helper = new MyDatabaseHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
    }

    public void insert(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql ="insert into "+TABLE_NAME+" (_id,name,age,salary,phone) values(?,?,?,?,?)";
        db.execSQL(sql,new Object[]{
            1,
            "BillGates",
            60,
            1,
            110
        });

        ContentValues values = new ContentValues();
        values.put("_id", 2);
        values.put("name", "china");
        values.put("age", 56);
        values.put("salary", 1212);
        values.put("phone",119);
        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public void delete(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql ="delete from "+TABLE_NAME+" where age = 60";
        db.execSQL(sql);

        int result = db.delete(TABLE_NAME, null, null);
        Log.d(TAG, "delete_result="+result);

        db.close();
    }

    public void update(){
        SQLiteDatabase db = helper.getWritableDatabase();

//        开启事务
        db.beginTransaction();

        String sql ="update "+TABLE_NAME+" set salary=2 where age = 60";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("phone",159);
        db.update(TABLE_NAME,values,null,null);

//        事务成功
        db.setTransactionSuccessful();

//        关闭事物
        db.endTransaction();
        db.close();
    }

    public void query(){
        SQLiteDatabase db = helper.getWritableDatabase();
//        String sql ="select * from "+TABLE_NAME;
//        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()){
//            int columnIndex = cursor.getColumnIndex("name");
//            String name = cursor.getString(columnIndex);
//            Log.d(TAG, "name==="+name);
//        }

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);

            Log.d(TAG, "id==="+id+" name= "+name);
        }

        cursor.close();
        db.close();
    }
}
