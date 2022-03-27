package com.shf.app15_sqlite;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

/**
 * 使用 ListActivity优化功能
 * 1. extends ListActivity
 * 2. 布局文件中的<ListView>的id必须是系统定义的id:list
 * 3. 如果还想在没有数据时显示一个文件,可以在布局中定义一个<TextView>(id必须为empty)
 */
public class MainActivity extends ListActivity {
    private ListView lv_main;
    private BlackNumberAdapter adapter;
    private List<BlackNumber> data;
    private BlackNumberDao dao;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = getListView();
        adapter = new BlackNumberAdapter();
        dao = new BlackNumberDao(this);
        data = dao.getAll();

        lv_main.setAdapter(adapter);

//        给listView设置创建contextMenu的监听
        lv_main.setOnCreateContextMenuListener(this);
    }

//    展示右键监听
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        添加2个item
        menu.add(0,1,0,"更新");
        menu.add(0,2,0,"删除");

//        得到长按的position
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = info.position;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        BlackNumber blackNumber = data.get(position);
        switch (item.getItemId()){
            case 1:  // 更新
//                1.显示更新的Dialog
                showUpdateDialog(blackNumber);
                break;
            case 2: // 删除
//                1. 删除数据表对应的数据
                dao.deleteById(blackNumber.getId());
//                2. 删除List对应的数据
                data.remove(position);
//                3.通知更新列表
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * 显示更新的Dialog
     * @param blackNumber
     */
    private void showUpdateDialog(BlackNumber blackNumber) {
        final EditText editText = new EditText(this);
        editText.setHint(blackNumber.getNumber());
        new AlertDialog.Builder(this)
                .setTitle("更新黑名单")
                .setView(editText)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        1.更新LIST对应的数据
                        String newNumber = editText.getText().toString();
                        blackNumber.setNumber(newNumber);

//                        2.更新数据表对应的数据
                        dao.update(blackNumber);

//                        3.通知更新列表
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    //    添加
    public void add(View v){
        EditText editText = new EditText(this);
        editText.setHint("输入黑名单号");
//        1.显示添加dialog(带输入框)
        new AlertDialog.Builder(this)
                .setTitle("添加黑名单")
                .setView(editText)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        1.保存到数据表中
                        String number = editText.getText().toString();
                        BlackNumber blackNumber = new BlackNumber(-1, number);
                        dao.add(blackNumber);
//                        2.保存到数据到List
                        data.add(0,blackNumber);
//                        3.通知更新列表
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
//        2.在确定的会掉进方法实现
//            2.1 保存数据到数据表中
//            2.2 保存数据到List
//            2.3 通知更新列表
    }

    class BlackNumberAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            }
            BlackNumber blackNumber = data.get(position);
            TextView textView = convertView.findViewById(android.R.id.text1);
            textView.setText(blackNumber.getNumber());
            return convertView;
        }
    }
}