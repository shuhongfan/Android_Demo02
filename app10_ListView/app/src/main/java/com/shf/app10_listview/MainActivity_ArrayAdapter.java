package com.shf.app10_listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_ArrayAdapter extends AppCompatActivity {

    private ListView lv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = findViewById(R.id.lv_main);

//        准备基集合数据
        String[] data = {"A","B","C","D","E","F","G","H","I","J","K","L","I"};
//        准备ArrayAdapter对象
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_array_adapter,
                data);
//        设置Adapter显示列表
        lv_main.setAdapter(adapter);
    }
}