package com.shf.app37_broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static com.shf.app37_broadcast.Constants.ACTION_SEND_MSG;
import static com.shf.app37_broadcast.Constants.KEY_CONTENT;

public class SendBroadCastActivity extends AppCompatActivity {

    private EditText mSendEt;
    private MessageReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broad_cast);

//        注册广播监听
        register();

        mSendEt = findViewById(R.id.be_seng_msg_et);
    }

    public void sendBroadCastMsg(View view) {
//        被调用后，发送广播
        String content = mSendEt.getText().toString();
        Intent intent = new Intent();
        intent.setAction(ACTION_SEND_MSG);
        intent.putExtra(KEY_CONTENT, content);
//        发送广播
        sendBroadcast(intent);
    }

    /**
     * 动态注册广播接收者
     */
    private void register(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_SEND_MSG);

        receiver = new MessageReceiver();
        registerReceiver(receiver, intentFilter);
    }

    /**
     * 为了防止内存泄漏，动态注册广播别忘了注销注册的广播；
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}