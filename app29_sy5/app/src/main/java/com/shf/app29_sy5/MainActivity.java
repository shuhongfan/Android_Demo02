package com.shf.app29_sy5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestPermissions(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private boolean RequestPermissions(@NonNull Context context, @NonNull String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            Log.i("requestMyPermissions",": 【 " + permission + " 】没有授权，申请权限");
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, 100);
            return false;
        } else {
            Log.i("requestMyPermissions",": 【 " + permission + " 】有权限");
            return true;
        }
    }

    /**
     * 内部文件读
     * @param view
     */
    public void read1(View view) {
        String data = "";
        FileInputStream fis = null;
        try {
            fis = openFileInput("data.txt");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            data = new String(buffer);

            Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SharedPreferences读
     * @param view
     */
    public void read2(View view) {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String data = sp.getString("name", "");
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
    }

    /**
     * 外部文件读
     * @param view
     */
    public void read3(View view) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            File SDPath = Environment.getExternalStorageDirectory();
            File file = new File(SDPath,"data.txt");
            FileInputStream fis = null;
            BufferedReader br = null;
            try {
                fis = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(fis));
                String data = br.readLine();

                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 内部文件写
     * @param view
     */
    public void write1(View view) {
        String fileName = "data.txt";
        String content = "内部文件读写";
        FileOutputStream fos = null;
        try {
            fos=openFileOutput(fileName,MODE_PRIVATE);
            fos.write(content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SharedPreferences写
     * @param view
     */
    public void write2(View view) {
        SharedPreferences sp = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", "SharedPreferences读写");
        editor.putInt("age",8);
        editor.commit();
    }

    /**
     * 外部文件写
     * @param view
     */
    public void write3(View view) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            File SDPath = Environment.getExternalStorageDirectory();
            File file = new File(SDPath,"data.txt");
            String data = "外部文件读写";
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    MyHelper helper = new MyHelper(MainActivity.this, "shf.db", null, 1);
    public void SQLiteRead(View view) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("information", null, "_id=?", new String[]{"10"}, null, null, null);
        if (cursor.getCount()!=0){
            while (cursor.moveToNext()){
                @SuppressLint("Range") String _id = cursor.getString(cursor.getColumnIndex("_id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));

                Toast.makeText(getApplicationContext(),_id+"---"+name+"---"+price,Toast.LENGTH_LONG).show();
            }
        }
    }

    public void SQLiteAdd(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","SQLiteAdd");
        values.put("price",9999);
        db.insert("information",null,values);
        db.close();
    }

    public int SQLiteUpdate(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price",7777);
        int number = db.update("information",values,"name=?",new String[]{"SQLiteAdd"});
        db.close();
        Toast.makeText(getApplicationContext(),number+"",Toast.LENGTH_LONG).show();
        return number;
    }

    public int SQLiteDelete(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int number = db.delete("information","_id=?",new String[]{"1"});
        db.close();
        Toast.makeText(getApplicationContext(),number+"",Toast.LENGTH_LONG).show();
        return number;
    }
}