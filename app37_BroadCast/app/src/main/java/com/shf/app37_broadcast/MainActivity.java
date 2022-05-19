package com.shf.app37_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mBatteryLevel;
    private BatteryLevelReceiver batteryLevelReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        /**
         * 动态注册
         */
        registerBatteryReceiver();
    }

    private void initView() {
        mBatteryLevel = findViewById(R.id.battery_level);
    }

    /**
     * 动态注册
     */
    private void registerBatteryReceiver() {
        //        第二步
//        我们要收听的频道，电量变化
        IntentFilter intentFilter = new IntentFilter();

//        第三步
//        设置频道
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

//        第四步
//        实例化广播接收者
        batteryLevelReceiver = new BatteryLevelReceiver();

//        第五步
//        注册广播
        registerReceiver(batteryLevelReceiver,intentFilter);
    }

    /**
     * 第一步，创建一个广播接收者（收音机），继承BroadcastReceiver
     */
    private class BatteryLevelReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int maxLevel = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)){
                Log.d(TAG, "收到了电量变化广播："+action);
                Log.d(TAG,"当前电量："+currentLevel);

                if (mBatteryLevel!=null){
                    double perSent = currentLevel*1.0 / maxLevel;
                    mBatteryLevel.setText("当前电量："+perSent*100+"%");
                }
            } else if (Intent.ACTION_POWER_CONNECTED.equals(action)){
                Log.d(TAG, "USB连接上了");
            } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)){
                Log.d(TAG, "USB线断开了");
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        取消广播注册，否则会导致内存泄露
        if (batteryLevelReceiver!=null){
            unregisterReceiver(batteryLevelReceiver);
        }
    }
}