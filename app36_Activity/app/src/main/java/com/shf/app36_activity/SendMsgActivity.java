package com.shf.app36_activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class SendMsgActivity extends AppCompatActivity {

    private static final String TAG = "SendMsgActivity";
    private EditText mreceiverEt;
    private EditText mcontentEt;
    private Button msentBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);

        initView();

        Intent intent = getIntent();
        if (intent!=null){
            String targetNumKey = intent.getStringExtra("targetNumKey");
            Uri data = intent.getData();
            Log.d(TAG, "targetNumKey==="+targetNumKey);
            Log.d(TAG, "data==="+data);

            if (targetNumKey!=null){
                mreceiverEt.setText(targetNumKey);
            } else {
                return;
            }
            if (data!=null){
                String content = data.toString().replace("msg", "");
                mcontentEt.setText(content );

            } else {
                return;
            }
        }
    }

    private void initView() {
        mreceiverEt = findViewById(R.id.receiver_phone_number_et);
        mcontentEt = findViewById(R.id.msg_content_et);
        msentBtn = findViewById(R.id.send_msg_btn);
    }
}