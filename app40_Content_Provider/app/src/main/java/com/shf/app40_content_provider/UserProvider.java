package com.shf.app40_content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.shf.app40_content_provider.db.UserDatabaseHelper;
import com.shf.app40_content_provider.utils.Constants;

public class UserProvider extends ContentProvider {

    private UserDatabaseHelper userDatabaseHelper;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int USER_MATCH_CODE = 1;

    static {
        uriMatcher.addURI("com.shf.app40_content_provider.UserProvider", "user",USER_MATCH_CODE);
    }

    public UserProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int result = uriMatcher.match(uri);
        if (result==USER_MATCH_CODE){
            SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
            long id = db.insert(Constants.TABLE_NAME, null, values);
            Uri resultUri = Uri.parse("content://com.shf.app40_content_provider.UserProvider/user/" + id);
//            插入数据成功，通知监听的地方
            getContext().getContentResolver().notifyChange(resultUri,null);
            return resultUri;

        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        userDatabaseHelper = new UserDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int result = uriMatcher.match(uri);
        if (result==USER_MATCH_CODE){
//            匹配规则
            SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query(Constants.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        } else {
//            不匹配规则
            throw new IllegalArgumentException("参数错误");
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}