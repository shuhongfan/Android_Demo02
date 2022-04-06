package com.shf.app23_sy3;

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
    private ArrayList<ShopInfo> data;
    private ListView lv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = findViewById(R.id.lv_main);

//        准备基集合数据
        data = new ArrayList<>();
        data.add(new ShopInfo(R.drawable.apple,"苹果","10元/KG"));
        data.add(new ShopInfo(R.drawable.cake,"蛋糕","300元"));
        data.add(new ShopInfo(R.drawable.kiwifruit,"猕猴桃","15元/KG"));
        data.add(new ShopInfo(R.drawable.scarf,"围巾","20元"));
        data.add(new ShopInfo(R.drawable.table,"桌子","350元"));
        data.add(new ShopInfo(R.drawable.wireclothes,"外套","300元"));

        data.add(new ShopInfo(R.drawable.apple,"苹果","10元/KG"));
        data.add(new ShopInfo(R.drawable.cake,"蛋糕","300元"));
        data.add(new ShopInfo(R.drawable.kiwifruit,"猕猴桃","15元/KG"));
        data.add(new ShopInfo(R.drawable.scarf,"围巾","20元"));
        data.add(new ShopInfo(R.drawable.table,"桌子","350元"));
        data.add(new ShopInfo(R.drawable.wireclothes,"外套","300元"));
//        准备BaseAdapter对象
        MyAdapter adapter = new MyAdapter();
//        设置Adapter显示列表
        lv_main.setAdapter(adapter);
    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //            如果没有复用的
            if (view==null){
//            加载item布局，得到View对象
                view = View.inflate(MainActivity.this, R.layout.activity_main_simple_adapter, null);
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