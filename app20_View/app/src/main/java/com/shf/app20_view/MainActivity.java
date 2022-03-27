package com.shf.app20_view;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        设置id为content的布局的子view
        setContentView(R.layout.activity_main);

        /**
         * 1.setContentView（int layoutId）
         * 2.setContentView（View view）
         *      2.1 动态加载布局文件得到视图对象
         *      2.2 动态创建视图对象
         */

//        2.1 动态加载布局文件得到视图对象
//        View view = View.inflate(this, R.layout.activity_main, null);
//        2.2 动态创建视图对象
//        TextView view = new TextView(this);
//        view.setText("Hello world");
//        setContentView(view);

        Window window = getWindow();
        View decorView = window.getDecorView();
        Log.e("TAG", decorView.toString());
        Log.e("TAG", decorView.getClass().getSuperclass().getName());


        MytextView mytextView = new MytextView(this);
        mytextView.setText("shf textview");
        setContentView(mytextView);
    }

}