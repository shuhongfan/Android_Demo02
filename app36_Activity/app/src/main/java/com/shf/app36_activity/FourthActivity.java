package com.shf.app36_activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class FourthActivity extends AppCompatActivity {

    private static final int PAY_REQUEST_CODE = 1;
    private Button recharge;
    private TextView mPayResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        initView();

        initListener();
    }

    private void initListener() {
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                处理点击
                handleClick();
            }
        });
    }

    /**
     * 设置一个点击事件，跳转到第二个界面，进行充值
     */
    private void handleClick() {
        Intent intent = new Intent(this, PayActivity.class);
//        startActivity(intent);

//        第一步：使用startActivityForResult替换原来的startActivity
        startActivityForResult(intent,PAY_REQUEST_CODE);
    }

    /**
     * 意图返回的结果的回调方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PAY_REQUEST_CODE){
            String resultContent = "";
            if (resultCode==2){
//                充值成功
                resultContent = data.getStringExtra("resultContent");
            } else if (resultCode==3){
//                充值失败
                resultContent = data.getStringExtra("resultContent");
            }
            mPayResultText.setText(resultContent);
        }
    }

    private void initView() {
        recharge = findViewById(R.id.recharge_bt);
        mPayResultText = findViewById(R.id.pay_result);
    }

    /**
     * 这个方法用于跳转到第二个界面
     * @param view
     */
    public void skip2Second(View view) {
        Intent intent = new Intent(this, FifthActivity.class);
        intent.putExtra("intKey", 100);
        intent.putExtra("booleanKey", false);
        startActivity(intent);
    }

    /**
     * 传递对象到另外一个界面
     *
     * 第一步：创建的对象要实现 Parcelable接口
     * 第二步：使用putExtra的方式扔进去，并且需要一个key
     * 第三步：
     * @param view
     */
    public void deliverObject(View view) {
        Intent intent = new Intent(this, FifthActivity.class);

        User user = new User();
        user.setName("BillGates");
        user.setTall(196.68);
        user.setAge(26);

        intent.putExtra("userKey", user);
        startActivity(intent);
    }

    /**
     * 打电话给10086
     * @param view
     */
    public void skip2Call(View view) {
 /*
            判断用户是否已经授权，调用ContextCompat.checkSelfPermission()
            比较方法的返回值和 PackageManager.PERMISSION_GRANTED，
            不等时表示用户没有授权，否则，即已经授权
         */
        if (ContextCompat.checkSelfPermission(this, Manifest.
                permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 调用ActivityCompat.requestPermissions() 方法，向用户申请授权
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CALL_PHONE}, 1);
        } else {
            // 已经授权，直接执行 call() 方法
            call();
        }
    }

    /*
        调用完requestPermission() 方法之后，无论是哪一种结果，最终都回调到 onRequestPermissionsResult() 方法中
        然后对授权结果 grantResults 进行判断
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "你禁止了拨打电话的权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void call() {
        // 为了防止程序崩溃我们将所有操作放在了异常捕获代码块当中
        try {
            // 构建一个隐式 intent ，intent 的 action 为 ACTION_CALL
            // ACTION_CALL 是系统内置的一个打电话的动作
            Intent intent = new Intent(Intent.ACTION_CALL);

            // 指定协议为 tel ，号码为 10086
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void skip2SendMSG(View view) {
        Intent intent = new Intent();
        intent.setAction("com.shf.app36_activity.SendMsgActivity");
        intent.addCategory(Intent.CATEGORY_DEFAULT);

        intent.putExtra("targetNumKey", "1008611");
        intent.setData(Uri.parse("msg:帮我查询一下电话费!"));

        startActivity(intent);
    }
}