package com.shf.app4_activity;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_main_message;
    private Button btn_main_start1;
    private Button btn_main_start2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        初始化视图对象
        et_main_message = findViewById(R.id.et_main_message);
        btn_main_start1 = findViewById(R.id.btn_main_start1);
        btn_main_start2 = findViewById(R.id.btn_main_start2);

//        设置点击监听
        btn_main_start1.setOnClickListener(this);
        btn_main_start2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){ // v 就是发生事件的视图对象（操作的）
        if (v==btn_main_start1){
//            Toast.makeText(this, "一般启动", Toast.LENGTH_SHORT).show();
//            1.创建Intent对象（显示）
            Intent intent = new Intent(this,SecondActivity.class);
//            2.通过Intent携带额外数据
            String message = String.valueOf(et_main_message.getText());
            intent.putExtra("MESSAGE", message);
//            3.启动Activity
            startActivity(intent);
        } else if (v==btn_main_start2){
//            Toast.makeText(this,"带回调启动", Toast.LENGTH_SHORT).show();
//            1.创建Intyent对象（显示）
            Intent intent = new Intent(this, SecondActivity.class);
//            2.通过intent携带额外数据
            String message = et_main_message.getText().toString();
            intent.putExtra("MESSAGE", message);
//            3.回调启动Activity
            int requestCode = 2;
            startActivityForResult(intent,requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        判断code
        if (requestCode == 2 && resultCode == 3) {
//        从data中取出数据
            String result = data.getStringExtra("RESULT");
//            显示
            et_main_message.setText(result);
        }

//        显示
    }
}

