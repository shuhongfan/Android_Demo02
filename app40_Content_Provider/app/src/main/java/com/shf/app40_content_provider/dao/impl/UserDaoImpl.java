package com.shf.app40_content_provider.dao.impl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.shf.app40_content_provider.dao.IUserDao;
import com.shf.app40_content_provider.db.UserDatabaseHelper;
import com.shf.app40_content_provider.pojo.User;

import java.util.ArrayList;
import java.util.List;

import static com.shf.app40_content_provider.utils.Constants.*;

public class UserDaoImpl implements IUserDao {
    private final UserDatabaseHelper userDatabaseHelper;

    public UserDaoImpl(Context context) {
        userDatabaseHelper = new UserDatabaseHelper(context);
    }

    @Override
    public long addUser(User user) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        ContentValues values = user2Values(user);
        long result = db.insert(TABLE_NAME, null, values);

        db.close();
        return result;
    }

    private ContentValues user2Values(User user) {
        ContentValues values = new ContentValues();
        values.put(FIELD_USER_NAME, user.getUserName());
        values.put(FIELD_SEX, user.getSex());
        values.put(FIELD_PASSWORD, user.getPassword());
        values.put(FIELD_AGE, user.getAge());
        return values;
    }

    @Override
    public int delUserById(int id) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        int result = db.delete(TABLE_NAME, FIELD_ID, new String[]{String.valueOf(id)});
        db.close();
        return result;
    }

    @Override
    public int updateUser(User user) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        ContentValues values = user2Values(user);
        int result = db.update(TABLE_NAME, values, FIELD_ID, new String[]{String.valueOf(user.getId())});
        db.close();
        return result;
    }

    @SuppressLint("Range")
    @Override
    public User getUserById(int id) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + FIELD_ID + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{id + ""});
        if (cursor.moveToNext()) {
            return cursor2User(cursor);
        }
        db.close();
        return null;
    }

    private User cursor2User(Cursor cursor) {
        User user = new User();
        int userID = cursor.getInt(cursor.getColumnIndex(FIELD_ID));
        user.setId(userID);

        String userName = cursor.getString(cursor.getColumnIndex(FIELD_USER_NAME));
        user.setUserName(userName);

        String userSex = cursor.getString(cursor.getColumnIndex(FIELD_SEX));
        user.setUserName(userSex);

        String userPassword = cursor.getString(cursor.getColumnIndex(FIELD_PASSWORD));
        user.setPassword(userPassword);

        int userAge = cursor.getInt(cursor.getColumnIndex(FIELD_AGE));
        user.setAge(userAge);

        return user;
    }

    @Override
    public List<User> listAllUser() {
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            users.add(cursor2User(cursor));
        }
        db.close();
        return users;
    }
}
