package com.shf.app8_component;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 测试进度条
 */
public class ProgressActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private LinearLayout ll_progress_loading;
    private ProgressBar pb_progress_loading;
    private SeekBar sb_progress_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        ll_progress_loading = findViewById(R.id.ll_progress_loading);
        pb_progress_loading = findViewById(R.id.pb_progress_loading);
        sb_progress_loading = findViewById(R.id.sb_progress_loading);

//        给seekbar设置监听
        sb_progress_loading.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}