package com.shf.app8_component;

import android.view.*;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 测试menu
 * OptionMenu
 * 1.如何触发Menu的显示？  点击menu键
 * 2.如何向Menu中添加MenuItem？
     *  重写onCreateOptionMenu
     *  menu.add()
     *  菜单文件
 * 选择某个MenuItem时如何响应？
 *
 *
 * ContentMenu
 * 1.如何触发Menu的显示？长按某个视图 view.setOnCreateContextMenuListener(this)
 * 2.如何向Menu中添加MenuItem？重写onCreateContextMenu（），menu.add（）
 * 3.选择某个MenuItem时如何响应？重写onOptionItemSelected()，根据itemid做响应
 *
 */
public class MenuActivity extends AppCompatActivity {

    private Button btn_test2_show_cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_test2_show_cm = findViewById(R.id.btn_test2_show_cm);
//        测试创建上下文菜单的监听
        btn_test2_show_cm.setOnCreateContextMenuListener(this);
    }

//    如何触发Menu的显示
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        添加菜单项
        menu.add(0,1,0,"添加");
        menu.add(0,4,0,"删除");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

//    选择某个MenuItem时如何响应
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(this,"添加", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this,"删除", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    //    用来显示OptionMenu的方法：向menu中添加Item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        纯编码方式
        menu.add(0,2,0,"添加");
        menu.add(0,3,0,"删除");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 2:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
//    显示OptionMenu的回调方法，在此方法中向menu中添加MenuItem
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        菜单文件方式
//        1.得到菜单加载器对象
        MenuInflater menuInflater = getMenuInflater();
//        2.加载菜单文件
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    当选择某个菜单项的回调方法
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

 */
}