package com.shf.app40_content_provider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.shf.app40_content_provider.utils.Constants;

import static com.shf.app40_content_provider.utils.Constants.*;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "UserDatabaseHelper";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG,"onCreate");
        String createSql = "create table "+TABLE_NAME+
                "("+FIELD_ID+" integer primary key autoincrement,"+FIELD_USER_NAME+" varchar(30),"+FIELD_PASSWORD+" varchar(30),"+FIELD_SEX+" varchar(5),"+FIELD_AGE+" integer)";
        sqLiteDatabase.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
