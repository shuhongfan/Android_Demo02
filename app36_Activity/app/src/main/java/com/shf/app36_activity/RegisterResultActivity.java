package com.shf.app36_activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_result);

        TextView result = findViewById(R.id.register_result);

        Intent intent = getIntent();
        String username = intent.getStringExtra("userNameKey");
        String password = intent.getStringExtra("passwordKey");

        result.setText("用户名："+username+" ，密码："+password);
    }
}