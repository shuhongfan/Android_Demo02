package com.shf.app36_activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LifeCycleActivity2 extends AppCompatActivity {

    private static final String TAG = "LifeCycleActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle2);
    }

    /**
     * onStart 已经可见了，但是没有焦点，也就是没有获取到焦点，不可以进行操作
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    /**
     * onResume可见，并且获取到了焦点，也就是说可以操作，可以进行交互
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    /**
     * onPause其实就是暂停的意思，这个方法其实就是失去了焦点，不可操作
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    /**
     * onStop 已经不可见了
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void skip2SecondActivity(View view) {
        Intent intent = new Intent(this,LifeCycleActivity3.class);
        startActivity(intent);
    }
}