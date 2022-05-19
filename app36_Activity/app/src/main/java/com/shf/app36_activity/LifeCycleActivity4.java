package com.shf.app36_activity;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity在横竖屏切换时的生命周期变化
 *
 *
 * onPause
 * onStop
 * onDestroy
 * onStart
 * onResume
 * ===== 横竖屏切换 ==========
 * onPause
 * onStop
 * onDestroy
 * onStart
 * onResume
 * 当我们切换成横屏的时候，Activity会销毁，并且重新创建
 *
 */
public class LifeCycleActivity4 extends AppCompatActivity {

    private static final String TAG = "LifeCycleActivity4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle4);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }
}