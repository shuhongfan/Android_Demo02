package com.shf.app36_activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private EditText muser_name_text;
    private EditText mpassword_text;
    private Button mregister_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

//        注册一个点击监听
        initListener();
    }

    private void initListener() {
        //        给按钮注册一个点击的监听事件
        mregister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                处理注册
                handlerRegister();
            }
        });
    }

    private void handlerRegister() {
        String usernameText = muser_name_text.getText().toString().trim();
        if (TextUtils.isEmpty(usernameText)){
            Toast.makeText(this,"用户名不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        String passwordText = mpassword_text.getText().toString().trim();
        if (TextUtils.isEmpty(passwordText)){
            Toast.makeText(this,"密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

//        注册成功，跳转页面
        Intent intent = new Intent(this,RegisterResultActivity.class);
        intent.putExtra("userNameKey", usernameText);
        intent.putExtra("passwordKey", passwordText);
        startActivity(intent);

//        结束当前页面
        finish();
    }

    private void initView() {
        muser_name_text = findViewById(R.id.user_name_text);
        mpassword_text = findViewById(R.id.password_text);
        mregister_btn = findViewById(R.id.register_btn);
    }
}