package com.shf.app14_datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

/**
 * 数据库操作的帮助类
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, int version) {
        super(context, "shf.db", null, version);
    }

    /**
     * 什么时候调用？  当数据库文件创建时调用（1次）
     * 在此方法中做什么？ 建表,插入初始化数据
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table person(\n" +
                "    _id integer primary key autoincrement ,\n" +
                "    name varchar,\n" +
                "    age int\n" +
                ")";
        sqLiteDatabase.execSQL(sql);
//        插入一些初始数据
        sqLiteDatabase.execSQL("insert into person (name, age) values ('Tom1',11)");
        sqLiteDatabase.execSQL("insert into person (name, age) values ('Tom2',12)");
        sqLiteDatabase.execSQL("insert into person (name, age) values ('Tom3',13)");
    }

//    当传入版本号大于数据库的版本号时调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e("TAG", "DBHelper onCreate()");
    }
}
