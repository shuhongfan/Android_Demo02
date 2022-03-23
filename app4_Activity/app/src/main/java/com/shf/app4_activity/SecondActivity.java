package com.shf.app4_activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    private EditText et_second_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et_second_message = findViewById(R.id.et_second_message);

//        4.得到Intent对象
        Intent intent = getIntent();
//        5.通过Intent读取额外数据
        String message = intent.getStringExtra("MESSAGE");
//        6.显示到EDITTest
        et_second_message.setText(message);
    }

    public void back1(View v){
//        关闭当前界面
        finish();
    }

    public void back2(View v){
//        保存一个结果
        int resultCode = 3;
//        准备一个额外带数据的Intent对象
        Intent data = new Intent();
        String value =  et_second_message.getText().toString();
        data.putExtra("RESULT", value);
//        设置结果
        setResult(resultCode,data);

//        关闭当前界面
        finish();
    }
}