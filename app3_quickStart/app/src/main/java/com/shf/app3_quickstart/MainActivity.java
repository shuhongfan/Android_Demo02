package com.shf.app3_quickstart;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button btn_main_download;

//    自动调用的方法，在其中加载布局显示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        加载布局，并生成对应的视图图像
        setContentView(R.layout.activity_main);

//        1.得到Button对象
        btn_main_download = findViewById(R.id.btn_main_download);

//        2.给Button设置点击的监听
        btn_main_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// 在回调方法中：当点击设置监听的button时调用
//            1）.提示开始下载的文本小提示
//                如何得到外部类的对象： 外部类名.this
                Toast
                        .makeText(MainActivity.this,"开始下载...",Toast.LENGTH_LONG )
                        .show();
//            2）.更新button显示的文本
                btn_main_download.setText("正在下载中....");
            }
        });

    }
}

/**
 * px:  pixels(像素) 1px长度等于对应屏幕的一个像素点的大小
 * dp/dip：  device-independent pixels（设备无关像素）
 * sp： scaled pixels（可伸缩像素），与dp类似，但是可以根据用户的字体大小首选项进行缩放
 *
 *  以dp像素为单位：差手机和好手机上显示的大小是不变的
 *  以px为单位：在差手机上显示变大，但在好手机上显示变小
 *  在布局文件中，除了字体大小，其他的都是以 【dp】 做单位，字体大小的单位用 【sp】
 *
 *  在 布局文件视图的宽高尽量使用match_parent / wrap_content
 *  如果必须指定特定的值，使用dp/dip做单位
 *  文本大小使用sp做单位
 *
 *
 */