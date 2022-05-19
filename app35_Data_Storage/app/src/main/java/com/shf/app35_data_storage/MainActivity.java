package com.shf.app35_data_storage;

import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText eaccount;
    private EditText epassword;
    private Button blogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        第一步，找到控件
        initView();

//        第二步：给我们的登录按钮，设置点击事件
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        File filesDir = this.getFilesDir();
//        File saveFile = new File(filesDir, "info.text");
        try {
            FileInputStream fileInputStream = this.openFileInput("info.text");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String info = bufferedReader.readLine();
//            切割数据
            String[] infoList = info.split("\\*\\*\\*");
            String account = infoList[0];
            String password = infoList[1];
//            回显数据
            eaccount.setText(account);
            epassword.setText(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置监听
     */
    private void initListener() {
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "点击了登录按钮");
                handlerLoginEvent(view);
            }
        });
    }

    /**
     * 处理登录事件
     * @param view
     */
    private void handlerLoginEvent(View view) {
//        第三步：拿到页面上的内容，账户和密码
        String accountText = eaccount.getText().toString();
        String passwordText = epassword.getText().toString();

//        对数据进行判空
//        if (accountText.length()==0){
        if (TextUtils.isEmpty(accountText)){
//            账户长度为空
            Toast.makeText(this,"账户不可以为空", Toast.LENGTH_SHORT).show();
            return;
        }

//        if (passwordText.length()==0) {
        if (TextUtils.isEmpty(passwordText)) {
            Toast.makeText(this,"密码不可以为空", Toast.LENGTH_SHORT).show();
            return;
        }

//        把账号和密码保存起来
        saveUserInfo(accountText,passwordText);
    }

    /**
     * 存储数据到文件
     * @param accountText
     * @param passwordText
     */
    private void saveUserInfo(String accountText, String passwordText) {
        Log.d(TAG,"保存用户信息....");
        File filesDir = this.getFilesDir();
        File saveFile = new File(filesDir, "info.text");
        Log.d(TAG,"filesDir...."+filesDir);
        try {
//            File saveFile = new File("/data/data/com.shf.app35_data_storage/info.text");
            if (!saveFile.exists()){
                saveFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(saveFile);
//            以特定的方式来存储
            fos.write((accountText+"***"+passwordText).getBytes(StandardCharsets.UTF_8));
            fos.close();
            Toast.makeText(this, "数据保存成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 找到控件
     */
    private void initView() {
        eaccount = this.findViewById(R.id.et_account);
        epassword = this.findViewById(R.id.et_password);
        blogin = this.findViewById(R.id.et_login);
    }
}