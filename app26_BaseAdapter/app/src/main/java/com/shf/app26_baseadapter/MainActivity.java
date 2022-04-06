package com.shf.app26_baseadapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv_main;
    ArrayList<ShopInfo> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = findViewById(R.id.lv_main);

//        准备集合数据
        data =  new ArrayList<>();
        data.add(new ShopInfo(R.drawable.f1,"name---1","content---1"));
        data.add(new ShopInfo(R.drawable.f2,"name---2","content---2"));
        data.add(new ShopInfo(R.drawable.f3,"name---3","content---3"));
        data.add(new ShopInfo(R.drawable.f4,"name---4","content---4"));
        data.add(new ShopInfo(R.drawable.f5,"name---5","content---5"));
        data.add(new ShopInfo(R.drawable.f6,"name---6","content---6"));
        data.add(new ShopInfo(R.drawable.f7,"name---7","content---7"));
        data.add(new ShopInfo(R.drawable.f8,"name---8","content---8"));
        data.add(new ShopInfo(R.drawable.f9,"name---9","content---9"));
        data.add(new ShopInfo(R.drawable.f10,"name---10","content---10"));

//        准备SimpleAdapter对象
        MyAdapter myAdapter = new MyAdapter();
//        设置Adapter显示列表
        lv_main.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseAdapter{

        /**
         * 返回集合数据的数量
         * @return
         */
        @Override
        public int getCount() {
            return data.size();
        }

        /**
         * 返回指定下标对应的数据对象
         * @param i
         * @return
         */
        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        /**
         * 返回指定下标所对应的item的View对象
         * @param i 下标
         * @param view  可复用的缓存Item视图对象，前n+1个位null，后面可以复用
         * @param viewGroup  ListView对象
         * @return
         */
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
//            加载item的布局,得到View对象

//            如果没有复用的
            if (view==null){
//                加载item布局,得到View对象
                view = View.inflate(
                        MainActivity.this,
                        R.layout.item_array_adapter,
                        null);
            }

//            根据position设置对应的数据
            ShopInfo shopInfo = data.get(i);
//            得到子view对象
            ImageView imageView = view.findViewById(R.id.iv_item_icon);
            TextView nameTv = view.findViewById(R.id.tv_item_name);
            TextView contentTv = view.findViewById(R.id.tv_item_content);
//            设置数据
            imageView.setImageResource(shopInfo.getIcon());
            nameTv.setText(shopInfo.getName());
            contentTv.setText(shopInfo.getContent());

            return view;
        }
    }
}