package com.shf.app19_keyevent;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 连续2次Back退出应用
 *      第一次按back键，只Toast提示，不退出应用
 *      2S内连续两次点击back键才退出应用
 *
 *
 */
public class MainActivity extends AppCompatActivity {

//    标识是否需要退出
    private boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    延迟2s，将exit赋值为false
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            exit =false;
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            if (!exit){
                exit = true;
                Toast.makeText(this,"再按一次就退出应用", Toast.LENGTH_SHORT).show();
//                发消息延迟2秒将exit=false
                handler.sendEmptyMessageDelayed(1,2000);
                return true; // 不退出
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}