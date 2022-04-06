package com.shf.app24_listview;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ListView lv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = findViewById(R.id.lv_main);

//        准备集合数据
        String[] data = {"A","B","C","D","E","F","G","H","i","J","K","L"};
//        准备ArrayAdapter对象
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_array_adapter,data);
//        设置Adapter显示列表
        lv_main.setAdapter(adapter);
    }
}