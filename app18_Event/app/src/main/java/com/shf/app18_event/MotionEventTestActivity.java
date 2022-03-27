package com.shf.app18_event;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 事件产生的顺序为： down --> move --> move.... -> up
 */
public class MotionEventTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event_test);

//        得到MyImageView
        MyImageView iv_touch_test = findViewById(R.id.iv_touch_test);
//        设置touch监听
        iv_touch_test.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e("TAG", " MyImageView OnTouchListener onTouch() "+motionEvent.getAction());

                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    return true;
                }
                return false;
//                return true;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("TAG", "dispatchTouchEvent"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "onTouchEvent"+event.getAction());
//        return super.onTouchEvent(event);
        return true;
    }
}