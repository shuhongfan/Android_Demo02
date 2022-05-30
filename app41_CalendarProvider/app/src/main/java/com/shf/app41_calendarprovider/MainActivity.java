package com.shf.app41_calendarprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int PERMISSION_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkCalendarPermission();
        }

        queryCalendars();
    }

    private void checkCalendarPermission(){
        int readPermission = checkSelfPermission(Manifest.permission.READ_CALENDAR);
        int writePermission = checkSelfPermission(Manifest.permission.WRITE_CALENDAR);
        if (readPermission== PackageManager.PERMISSION_GRANTED && writePermission==PackageManager.PERMISSION_GRANTED){
//            有权限

        } else {
            Log.d(TAG, "requestPermission");
//            去获取权限
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==PERMISSION_REQUEST_CODE){
            if (grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED) {
//                有权限
                Log.d(TAG, "有权限");
            } else {
                Log.d(TAG, "无权限");
//                无权限
                finish();
            }
        }
    }

    private void queryCalendars() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.android.calendar/calendars");
        Cursor cursor = contentResolver.query(uri, null, null, null);
        String[] columnNames = cursor.getColumnNames();
        while (cursor.moveToNext()){
            Log.d(TAG,"=================================");

            for (String columnName : columnNames) {
                Log.d(TAG,columnName+"==="+cursor.getString(cursor.getColumnIndex(columnName)));
            }

            Log.d(TAG,"=================================");
        }
    }
}