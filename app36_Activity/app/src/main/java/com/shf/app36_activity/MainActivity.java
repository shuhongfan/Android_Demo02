package com.shf.app36_activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText maccount;
    private EditText mpassword;
    private Button mlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "login clicked...");
                handleLogin();
            }
        });
    }

    /**
     * 登录方法
     */
    private void handleLogin() {
        String accountText = maccount.getText().toString().trim();
//        判空
        if (TextUtils.isEmpty(accountText)){
            Toast.makeText(this, "输入的账号为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        String passwordText = mpassword.getText().toString().trim();
        if (TextUtils.isEmpty(passwordText)){
            Toast.makeText(this, "输入的密码为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        /**
         * 显示意图跳转 完全限定类名

//        有密码有账号后，我们把数据传递到另一个界面
//        先创建一个意图对象，然后通过startActivity方法来跳转
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("account", accountText);
        intent.putExtra("password", passwordText);
        startActivity(intent);
        */

        /**
         * 隐式意图
         */
        Intent intent = new Intent();
        intent.putExtra("account", accountText);
        intent.putExtra("password", passwordText);
        intent.setAction("com.shf.LOGIN_INFO");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }

    private void initView() {
        maccount = findViewById(R.id.account);
        mpassword = findViewById(R.id.password);
        mlogin = findViewById(R.id.login);
    }
}