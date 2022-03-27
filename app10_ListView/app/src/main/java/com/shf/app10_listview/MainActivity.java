package com.shf.app10_listview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView lv_main;
    private ArrayList<ShopInfo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = findViewById(R.id.lv_main);

//        准备基集合数据
        data = new ArrayList<>();
        data.add(new ShopInfo(R.drawable.f1,"name----1","content----1"));
        data.add(new ShopInfo(R.drawable.f2,"name----2","content----2"));
        data.add(new ShopInfo(R.drawable.f3,"name----3","content----3"));
        data.add(new ShopInfo(R.drawable.f4,"name----4","content----4"));
        data.add(new ShopInfo(R.drawable.f5,"name----5","content----5"));
        data.add(new ShopInfo(R.drawable.f6,"name----6","content----6"));
        data.add(new ShopInfo(R.drawable.f7,"name----7","content----7"));
        data.add(new ShopInfo(R.drawable.f8,"name----8","content----8"));
        data.add(new ShopInfo(R.drawable.f9,"name----9","content----9"));
        data.add(new ShopInfo(R.drawable.f10,"name----10","content----10"));
//        准备BaseAdapter对象
        MyAdapter adapter = new MyAdapter();
//        设置Adapter显示列表
        lv_main.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter{

//        返回集合数据的数量
        @Override
        public int getCount() {
            return data.size();
        }

//        返回指定下标对应的数据对象
        @Override
        public Object getItem(int i) {
            return data.get(i);
        }


        @Override
        public long getItemId(int i) {
            return 0;
        }

//        返回指定下标对应的item的View对象
//        i: 下标
//        view：可复用的缓存Item视图对象，前n+1个为null
//        viewGroup：ListView对象
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
//            如果没有复用的
            if (view==null){
//            加载item布局，得到View对象
                view = View.inflate(MainActivity.this, R.layout.item_simple_adapter, null);
            }


//            根据position设置对应的数据
//                得到当前行的数据对象
            ShopInfo shopInfo = data.get(i);
//                得到子View对象
            ImageView imageView = view.findViewById(R.id.iv_item_icon);
            TextView nameTV = view.findViewById(R.id.iv_item_name);
            TextView contentTV = view.findViewById(R.id.iv_item_content);
//                设置数据
            imageView.setImageResource(shopInfo.getIcon());
            nameTV.setText(shopInfo.getName());
            contentTV.setText(shopInfo.getContent());
            return view;
        }
    }
}