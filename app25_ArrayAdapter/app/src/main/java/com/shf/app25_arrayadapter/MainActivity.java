package com.shf.app25_arrayadapter;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView lv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = (ListView) findViewById(R.id.lv_main);

//        准备集合数据
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f1);
        map.put("name", "name---1");
        map.put("content", "content---1");
        data.add(map);
        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f2);
        map.put("name", "name---2");
        map.put("content", "content---2");
        data.add(map);
        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f3);
        map.put("name", "name---3");
        map.put("content", "content---3");
        data.add(map);
        map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f4);
        map.put("name", "name---4");
        map.put("content", "content---4");
        data.add(map);map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f5);
        map.put("name", "name---5");
        map.put("content", "content---5");
        data.add(map);
        data.add(map);map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f6);
        map.put("name", "name---6");
        map.put("content", "content---6");
        data.add(map);
        data.add(map);map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f7);
        map.put("name", "name---7");
        map.put("content", "content---7");
        data.add(map);
        data.add(map);map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f8);
        map.put("name", "name---8");
        map.put("content", "content---8");
        data.add(map);
        data.add(map);map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f9);
        map.put("name", "name---9");
        map.put("content", "content---9");
        data.add(map);
        data.add(map);map = new HashMap<String, Object>();
        map.put("icon", R.drawable.f10);
        map.put("name", "name---10");
        map.put("content", "content---10");
        data.add(map);

//        map对象中的key的数组,用于得到对应的Value
        String[] from = {"icon","name","content"};
//        Item布局文件中的子View的id的数组
        int[] to = {R.id.iv_item_icon,R.id.tv_item_name,R.id.tv_item_content};

//        准备SimpleAdapter对象
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item_array_adapter,
                from,
                to);
//        设置Adapter显示列表
        lv_main.setAdapter(adapter);
    }
}