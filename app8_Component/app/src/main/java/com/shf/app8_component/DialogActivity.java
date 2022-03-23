package com.shf.app8_component;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

//    显示一般DIalog
    public void showAD(View v){
//        new AlertDialog.Builder(this).create().show();
        new AlertDialog
                .Builder(this)
                .setTitle("删除数据")  // 设置标题
                .setMessage("你确定删除数据吗？")   // 提示信息
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "删除数据",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "取消删除数据",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();  // 方法链调用
    }

//    显示单选列表AlertDialog
    public void showLD(View v){
        final String[] items = {"红","绿","蓝","灰"}; // final的变量在方法执行后还存在
        new AlertDialog.Builder(this)
                .setTitle("指定背景颜色")
                .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { //which就是选中的position
//                        提示颜色
                        Toast.makeText(DialogActivity.this, items[which],Toast.LENGTH_SHORT).show();
//                        移出Dialog
                        dialog.dismiss();
                    }
                })
                .show();
    }


//    显示自定义AlertDialog
    public void showCD(View v){
//        动态加载布局文件，得到对应的View对象
        View view = View.inflate(this, R.layout.dialog_view, null);
//        问题1：view的真实类型？布局文件的根标签的类型，包含了子view对象
//        问题2： 如何得到一个独立的View的子view? v.findViewById(id)
//                findViewById(id)是在setContentView()中的View中找

        EditText nameEt = view.findViewById(R.id.et_dialog_name);
        EditText pwdEt = view.findViewById(R.id.et_dialog_pwd);
        View et_dialog_name = findViewById(R.id.et_dialog_name);

        new AlertDialog.Builder(this)
                .setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        读取用户和密码
                        String username = nameEt.getText().toString();
                        String pwd = pwdEt.getText().toString();
//                        提示
                        Toast.makeText(DialogActivity.this, username+": "+pwd,Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

//    显示圆形进度Progress
    public void showPD(View v) { // 回调方法：主线程执行的
        final ProgressDialog dialog = ProgressDialog.show(this, "数据加载", "数据加载中");

//        模拟做一个长时间的工作
//        长时间的工作不能在主线程做，得启动分线程完成
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
//            休息一回儿
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //      移除
                dialog.dismiss(); // 方法在分线程执行，但内部使用Handler实现主线程移除dialog

//                不能在分线程直接更新UI
//                显示toast
                runOnUiThread(new Runnable() {  // 在分线程中执行
                    @Override
                    public void run() {// 在主线程执行
                        Toast.makeText(DialogActivity.this, "加载完成了！！！",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }

    //    显示水平进度ProgressDialog
    public void showPD2(View v){
//        1.创建dialog对象
        ProgressDialog pd = new ProgressDialog(this);
//        2.设置样式
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        3.显示
        pd.show();

//        4.启动分线程，加载数据，并显示进度，当加载完成,移除dialog
        new Thread(new Runnable(){
            @Override
            public void run() {
                int count = 20;
//                设置最大进度
                pd.setMax(count);
                for (int i = 0; i < count; i++) {
//                    休息一会
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    进度+1
                    pd.setProgress(pd.getProgress()+1);
                }
//                移除Dialog
                pd.dismiss();
            }
        }).start();
    }

    public void showDateAD(View v){
//        创建日历对象
        Calendar calendar = Calendar.getInstance();
//        得到当前的年月日
        final int year = calendar.get(Calendar.YEAR);
        final int monthOfYear = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        Log.e("TAG", year+"-"+monthOfYear+"-"+dayOfMonth);

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.e("TAG", year+"---"+(monthOfYear+1)+"---"+dayOfMonth);
            }
        },year,monthOfYear,dayOfMonth).show();
    }

    public void showTimeAD(View v){
        Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);// 得到小时
        int minute = c.get(Calendar.MINUTE);
        Log.e("TAG", hourOfDay+":"+minute);

        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.e("TAG", hourOfDay+":::"+minute);
            }
        },hourOfDay,minute,true).show();
    }
}