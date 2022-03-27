package com.shf.app14_datastorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);
    }

    /**
     * 创建库
     * @param v
     */
    public void testCreateDB(View v){
        DBHelper dbHelper = new DBHelper(this, 1);
//        获取连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Toast.makeText(this,"创建数据库", Toast.LENGTH_SHORT).show();
    }

//    更新库
    public void testUpdateDB(View v){
        DBHelper dbHelper = new DBHelper(this, 2);
//        获取简介
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Toast.makeText(this,"创建数据库", Toast.LENGTH_SHORT).show();
    }

    /**
     * 添加记录
     * @param v
     */
    public void testInsert(View v){
//        1.得到连接
        DBHelper dbHelper = new DBHelper(this, 2);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        2.执行insert
        ContentValues values = new ContentValues();
        values.put("name", "Tom");
        values.put("age", 12);
        long id = database.insert("person", null, values);
//        3.关闭
        database.close();
//        4.提示
        Toast.makeText(this,"id="+id, Toast.LENGTH_LONG).show();
    }

    public void testUpdate(View v){
//        1.得到连接
        DBHelper dbHelper = new DBHelper(this, 2);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        2.执行更新
        ContentValues values = new ContentValues();
        values.put("name", "Jack");
        values.put("age", 20);
        int updateCount = database.update(
                "person", values,
                "_id=?",
                new String[]{"4"});
//        3.关闭
        database.close();
//        4.提示
        Toast.makeText(this, "updateCount="+updateCount, Toast.LENGTH_LONG).show();
    }

    public void testDelete(View v){
//        1.得到连接
        DBHelper dbHelper = new DBHelper(this, 2);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        2.删除数据
        int deleteCount = database.delete("person", "_id=?", new String[]{"2"});
//        3.关闭
        database.close();
//        4.提示
        Toast.makeText(this,"deleteCount="+deleteCount, Toast.LENGTH_LONG).show();
    }

    public void testQuery(View v){
//        1.得到连接
        DBHelper dbHelper = new DBHelper(this, 2);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        2.执行查询
        Cursor cursor = database.query("person", null, null, null, null, null, null);
//        Cursor cursor = database.query("person", null, "_id=?", new String[]{"3"}, null, null, null);

//        得到匹配的总记录数
        int count = cursor.getCount();

//        取出cursor中所有的数据
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            Log.e("TAG", id+"-"+name+"-"+age);
        }
//        3.关闭
        cursor.close();
        database.close();
//        4.提示
        Toast.makeText(this, "count="+count, Toast.LENGTH_LONG).show();
    }

    /**
     *  测试事务
     *  一个功能中对数据库进行多个操作:要成功都成功,要失败都失败
     *  事务处理的3个步骤:
     *  1.开启事务(获取连接后)
     *  2.设置事务成功(在全部正常执行完成之后)
     *  3.结束事务(获取连接后)
     * @param v
     */
    public void testTransaction(View v){
        SQLiteDatabase database = null;
        try {
//        1.得到连接
            DBHelper dbHelper = new DBHelper(this, 2);
            database = dbHelper.getReadableDatabase();

//        ① 开启事务(获取连接后)
            database.beginTransaction();

//        2.执行更新
            ContentValues values = new ContentValues();
            values.put("age", 18);
            int updateCount = database.update(
                    "person", values,
                    "_id=?",
                    new String[]{"1"});

//        出了异常
            boolean flag = true;
            if (flag){
                throw new RuntimeException("出异常啦!");
            }

            ContentValues values2 = new ContentValues();
            values2.put("age", 999);
            int updateCount2 = database.update(
                    "person", values2,
                    "_id=?",
                    new String[]{"3"});

//        ② 设置事务成功(在全部正常执行完成后)
            database.setTransactionSuccessful();
//        4.提示
            Toast.makeText(this, "updateCount="+updateCount+"  updateCount2="+updateCount2, Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "出异常啦",Toast.LENGTH_SHORT ).show();
        } finally{
//            ③ 结束事务
            if (database!=null){
                database.endTransaction();
                database.close();
            }
        }

    }
}