package com.shf.app15_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作black_number表的DAO类
 */
public class BlackNumberDao {

    private DBHelper dbHelper;

    public BlackNumberDao(Context context){
        dbHelper = new DBHelper(context);
    }

    /**
     * 添加一条记录
     * @param blackNumber
     */
    public void add(BlackNumber blackNumber){
//        1.得到连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        2.执行insert
        ContentValues values = new ContentValues();
        values.put("number", blackNumber.getNumber());
        long id = database.insert("black_number", null, values);
        Log.i("TAG", "id="+id);
//        设置id
        blackNumber.setId((int) id);
//        3.关闭
        database.close();
    }

    /**
     * 根据ID删除一条记录
     */
    public void deleteById(int id){
//        1.得到连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        2.执行delete
        database.delete("black_number", "_id=?", new String[]{id+""});
//        3.关闭
        database.close();
    }

    /**
     * 更新一条记录
     */
    public void update(BlackNumber blackNumber){
//        1.得到连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        2.执行update
        ContentValues values = new ContentValues();
        values.put("number", blackNumber.getNumber());
        int updateCount = database.update("black_number", values, "_id=" + blackNumber.getId(), null);
        Log.i("TAG", "updateCount="+updateCount);
//        3.关闭
        database.close();
    }

    /**
     * 查询所有记录封装成List<BlackList>
     */
    public List<BlackNumber> getAll(){
        ArrayList<BlackNumber> list = new ArrayList<>();

//        1.得到连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        2.执行query
        Cursor cursor = database.query("black_number", null, null, null, null, null, "_id desc");
//        3.从cursor中取出所有数据并封装到List中
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String number = cursor.getString(1);
            list.add(new BlackNumber(id,number));
        }
//        4.关闭
        cursor.close();
        database.close();

        return list;
    }
}
