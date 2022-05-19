package com.shf.app36_activity;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 这是第三个界面 通过隐式意图跳转
 *
 * 隐式意图：一般用于应用之间的跳转
 */
public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView info = this.findViewById(R.id.info);

        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        String password = intent.getStringExtra("password");

        Log.d(TAG,"account==="+account);
        Log.d(TAG,"password==="+password);
        info.setText("您的账户："+account+" ,密码为："+password);
    }
}