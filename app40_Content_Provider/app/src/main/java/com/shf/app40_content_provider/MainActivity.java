package com.shf.app40_content_provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shf.app40_content_provider.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.shf.app40_content_provider.UserProvider/user");
        contentResolver.registerContentObserver(uri, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Log.d(TAG, "用户数据发生变化");
            }
        });

    }

    public void getRemoteUsers(View view) {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.shf.app40_content_provider.UserProvider/user");
        Cursor cursor = contentResolver.query(uri, null, "userName=?",new String[]{"root"}, null);
        String[] columnNames = cursor.getColumnNames();
        for (String columnName : columnNames) {
            Log.d(TAG,"columnName="+columnName);
        }

        while (cursor.moveToNext()){
            Log.d(TAG,"======================");
            for (String columnName : columnNames) {
                String value = cursor.getString(cursor.getColumnIndex(columnName));
                Log.d(TAG,columnName+"==="+value);
            }
            Log.d(TAG,"======================");
        }

        cursor.close();

    }

    public void addUser(View view) {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.shf.app40_content_provider.UserProvider/user");

        ContentValues values = new ContentValues();
        values.put(Constants.FIELD_USER_NAME, "BILLGATES");
        values.put(Constants.FIELD_PASSWORD, "123456");
        values.put(Constants.FIELD_SEX, "male");
        values.put(Constants.FIELD_AGE, 59);

        contentResolver.insert(uri,values);
    }
}