package com.shf.app32_sy7;

import android.content.Intent;
import android.content.IntentFilter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyReceiver receiver;
    private MyStopReceiver stopReceiver;

    public static Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent1 = new Intent(this, MyService.class);

//        实例化广播接受者
        receiver = new MyReceiver();
//        实例化意图过滤器
        String action = "hello";
        IntentFilter filter = new IntentFilter(action);
//        动态注册广播接受者
        registerReceiver(receiver,filter);

//        实例化广播接受者
        stopReceiver = new MyStopReceiver();
//        实例化意图过滤器
        String action2 = "hello2";
        IntentFilter filter2 = new IntentFilter(action2);
//        动态注册广播接受者
        registerReceiver(stopReceiver,filter2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        取消注册广播接受者
        unregisterReceiver(receiver);
        unregisterReceiver(stopReceiver);
    }
}