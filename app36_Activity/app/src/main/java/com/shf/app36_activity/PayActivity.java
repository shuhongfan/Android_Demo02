package com.shf.app36_activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PayActivity extends AppCompatActivity {

    private EditText mPayInputBox;
    private Button mStartPayBtn;
    private Button mCancelPayBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        initView();

        initListener();
    }

    private void initListener() {
        mStartPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClick();
            }
        });

        mCancelPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCancel();
            }
        });
    }

    /**
     * 取消充值
     */
    private void handleCancel() {
        Intent intent = new Intent();
        intent.putExtra("resultContent", "充值失败！");
        setResult(3,intent);

        finish();
    }

    private void handleClick() {
        String payNumber = mPayInputBox.getText().toString();
        if (TextUtils.isEmpty(payNumber)){
            Toast.makeText(this,"请输入充值金额!", Toast.LENGTH_SHORT).show();
            return;
        }

//        进行网络访问，进行充值
        Intent intent = new Intent();
        intent.putExtra("resultContent", "充值成功！");
        setResult(2,intent);

        finish();
    }

    private void initView() {
        mPayInputBox = findViewById(R.id.pay_input_box);
        mStartPayBtn = findViewById(R.id.start_pay_btn);
        mCancelPayBtn = findViewById(R.id.cancel_pay_btn);
    }
}