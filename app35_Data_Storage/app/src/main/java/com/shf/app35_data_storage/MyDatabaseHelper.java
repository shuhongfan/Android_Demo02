package com.shf.app35_data_storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import static com.shf.app35_data_storage.Constants.*;

/**
 * @author shuho
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDatabaseHelper";

    /**
     *
     * @param context  上下文
     *  name      数据库名称
     *  factory   游标工厂
     *  version   版本
     */
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_CODE);
    }

    /**
     * 创建时的回调
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "数据库创建");
//        创建字段
        String sql = "create table "+TABLE_NAME+" (_id integer,name varchar,age integer,salary integer);";
//        执行sql
        sqLiteDatabase.execSQL(sql);
    }

    /**
     * 升级数据库时的回调
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d(TAG, "数据库升级");

        String sql = "";

        switch (oldVersion){
            case 1:
//                添加address和phone字段
                sql = "alter table "+TABLE_NAME+" add phone integer";
                sqLiteDatabase.execSQL(sql);
                break;
            case 2:
                sql = "alter table "+TABLE_NAME+" add (address varchar, address varchar)";
                sqLiteDatabase.execSQL(sql);
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
