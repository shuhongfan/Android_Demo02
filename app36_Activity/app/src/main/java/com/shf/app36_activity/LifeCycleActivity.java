package com.shf.app36_activity;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycleActivity";
    private static final String MSG_RECODE = "msg_recode";
    private static final String RECODE_KEY = "msg";
    SharedPreferences sharedPreferences = null;
    private Button send;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        initView();
        initListener();

//        恢复数据
        sharedPreferences = getSharedPreferences(MSG_RECODE,MODE_PRIVATE);
        String record = sharedPreferences.getString(RECODE_KEY, null);
        if (!TextUtils.isEmpty(record)){
            content.setText(record);
        }
    }

    private void initListener() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSend();
            }
        });
    }

    private void handleSend() {
        String text = content.getText().toString().trim();
        if (TextUtils.isEmpty(text)){
            Toast.makeText(this,"请输入要发送的内容！",Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG,"发送短信："+text);
    }

    private void initView() {
        content = findViewById(R.id.content);
        send = findViewById(R.id.send);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String text = content.getText().toString().trim();
        if (!TextUtils.isEmpty(text)){
//            保存数据到sharereference
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(RECODE_KEY, text);
            edit.commit();
        }
    }
}