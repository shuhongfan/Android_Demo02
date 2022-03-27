package com.shf.app18_event;

import android.util.Log;
import android.view.KeyEvent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class KeyEventTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_event_test);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("TAG", "dispatchKeyEvent keyCode="+event.getKeyCode()+" action="+event.getAction());
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("TAG", "onKeyDown keyCode="+event.getKeyCode()+" action="+event.getAction());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("TAG", "onKeyUp keyCode="+event.getKeyCode()+" action="+event.getAction());
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.e("TAG", "onKeyLongPress keyCode="+event.getKeyCode()+" action="+event.getAction());
        return super.onKeyLongPress(keyCode, event);
    }
}