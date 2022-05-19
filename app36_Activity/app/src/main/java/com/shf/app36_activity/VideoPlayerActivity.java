package com.shf.app36_activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class VideoPlayerActivity extends AppCompatActivity {

    private static final String TAG = "VideoPlayerActivity";
    private TextView mCurrentPlayStatus;
    private Button mPlayerControl;

    private boolean isPlayer = false;

    private boolean isStopAtAmin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        initView();
        initListener();
    }

    private void initListener() {
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClick();
            }
        });
    }

    private void handleClick() {
        if (isPlayer) {
            stop();
        } else {
            play();
        }
    }

    private void play() {
        Log.d(TAG, "播放电影");
        mCurrentPlayStatus.setText("正在播放电影，声音很大...");
        mPlayerControl.setText("暂停");
        isPlayer = true;
    }

    private void stop() {
        Log.d(TAG, "停止电影");
        mCurrentPlayStatus.setText("电影已停止...");
        mPlayerControl.setText("播放");
        isPlayer = false;
    }

    private void initView() {
        mCurrentPlayStatus = findViewById(R.id.current_play_status);
        mPlayerControl = findViewById(R.id.player_control);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart... 开始播放...");

        if (isStopAtAmin&& !isPlayer){
            play();
            isStopAtAmin=false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop... 暂停播放...");

        if (isPlayer){
//            如果当前是播放的，那么就需要把电影停止
            stop();
            isStopAtAmin = true;
        }
    }
}