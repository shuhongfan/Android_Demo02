package com.shf.app30_sy6;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MyHelper helper = new MyHelper(MainActivity.this, "shf.db", null, 1);
    SQLiteDatabase db;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = helper.getWritableDatabase();
        lv=findViewById(R.id.listView);
    }

    public void press(View v){
        switch (v.getId()){
            case R.id.select:
                Cursor c = db.query("shop", null,null,null,null,null,null);
                System.out.println(c);
                SimpleCursorAdapter simpleCursorAdapter =
                        new SimpleCursorAdapter(
                                this,
                                android.R.layout.simple_list_item_2,
                                c,
                                new String[]{"name", "location"},
                                new int[]{android.R.id.text1,android.R.id.text2} );
                lv.setAdapter(simpleCursorAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ListView listView = (ListView) adapterView;
                        Cursor cursor = (Cursor) listView.getItemAtPosition(i);
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.insert:
                db.execSQL("insert into shop(name,location) values(?,?)",new Object[]{"apple手机","美国"});
                db.execSQL("insert into shop(name,location) values(?,?)",new Object[]{"华为手机","中国"});
                Toast.makeText(getApplicationContext(),"插入成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:
                db.execSQL("update shop set location=? where name=?",new Object[]{"武汉","apple手机"});
                Toast.makeText(getApplicationContext(),"更新成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                db.execSQL("delete from shop where name=?",new Object[]{"apple手机"});
                Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                break;
            default:
                return;
        }
    }

    @Override
    protected void onStop() {
        db.close();
        super.onStop();
    }
}