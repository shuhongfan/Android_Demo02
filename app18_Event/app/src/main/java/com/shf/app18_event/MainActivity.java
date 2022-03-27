package com.shf.app18_event;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 用来测试MotionEvent和KeyEvent的主界面
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        设置点击监听
        findViewById(R.id.btn_main_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MotionEventTestActivity.class));
            }
        });

//        设置长按监听
        findViewById(R.id.btn_main_test2).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(MainActivity.this,KeyEventTestActivity.class));
                return true;
            }
        });
    }

//    back退出
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        监听back键
        if (event.getKeyCode()==KeyEvent.KEYCODE_BACK){
//            显示确定的dialog
            new AlertDialog.Builder(this)
                    .setMessage("你确定退出吗?")
                    .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            退出
                            finish();
                        }
                    })
                    .setNegativeButton("再看看", null)
                    .show();
            return true;
        }

        return super.onKeyUp(keyCode, event);
    }
}