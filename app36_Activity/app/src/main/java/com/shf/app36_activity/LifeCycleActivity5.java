package com.shf.app36_activity;

import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 配置不敏感  屏幕旋转不销户Activity
 * android:configChanges="keyboardHidden|screenSize|orientation"
 */
public class LifeCycleActivity5 extends AppCompatActivity {
    private static final String TAG = "LifeCycleActivity4";
    private SeekBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle5);

        initView();

    }

    private void initView() {
        mProgress = findViewById(R.id.progress);
        Log.d(TAG, "mProgress=="+mProgress.toString());

//        初始化数据
        mProgress.setMax(100);
        mProgress.setProgress(0);
        mProgress.post(new Runnable() {
            @Override
            public void run() {
                mProgress.setProgress(0);
            }
        });
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