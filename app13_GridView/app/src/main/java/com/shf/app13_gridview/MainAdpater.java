package com.shf.app13_gridview;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class MainAdpater extends BaseAdapter {
    private Context context;
    private String[] names;
    private int[] icons;
    private SharedPreferences sp;

    public MainAdpater(Context context,String[] names, int[] icons) {
        super();
        this.context=context;
        this.names=names;
        this.icons=icons;
        sp = context.getSharedPreferences("shf", Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return names[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = View.inflate(context,R.layout.item_main,null);
        }
        ImageView imageView = view.findViewById(R.id.iv_item_icon);
        TextView tv_item_name = view.findViewById(R.id.tv_item_name);
        imageView.setImageResource(icons[i]);
        tv_item_name.setText(names[i]);

//        从sp中读取保存的名称，如果存在则显示
        if (i==0){
            String saveName = sp.getString("NAME", null);
            if (saveName!=null){
                tv_item_name.setText(saveName);
            }
        }

        return view;
    }
}
