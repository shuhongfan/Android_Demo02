package com.shf.app13_gridview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private GridView gv_main;
    private MainAdpater adapter;
    String[] names = new String[] { "手机防盗", "通讯卫士", "软件管理", "流量管理",
            "进程管理", "手机杀毒", "缓存清理", "高级工具", "设置中心" };

    int[] icons = new int[] { R.drawable.widget01, R.drawable.widget02,
            R.drawable.widget03, R.drawable.widget04, R.drawable.widget05,
            R.drawable.widget06, R.drawable.widget07, R.drawable.widget08,
            R.drawable.widget09 };

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv_main = findViewById(R.id.gv_main);
        adapter = new MainAdpater(this,names,icons);
        gv_main.setAdapter(adapter);

//        给gridView的Item设置点击监听
        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                得到当前点击的名称
                String name = names[i];
//                提示
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });

//        给gridView的item添加长按监听(只能第一个有相应)
        gv_main.setOnItemLongClickListener(this);

        sp = getSharedPreferences("shf", Context.MODE_PRIVATE);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i==0){
//        得到当前显示简单名称
        TextView tv_item_name = view.findViewById(R.id.tv_item_name);
        String name = tv_item_name.getText().toString();
//        为dialog准备输入框对象
        EditText editText = new EditText(this);
        editText.setHint(name);
//            显示AlertDialog
            new AlertDialog.Builder(this)
                    .setTitle("修改名称")
                    .setView(editText)
                    .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String newName = editText.getText().toString();
//                            1.界面更新
                            tv_item_name.setText(newName);
//                            2.将名称保存到sp中
                            sp.edit().putString("NAME", newName).commit();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
        return true;
    }
}