package com.shf.app6_activity_call_sms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private EditText et_main_number;
    private EditText et_main_sms;
    private Button btn_main_call;
    private Button btn_main_send;
    private static final int PERMISSION_REQUESTCODE = 1;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view==btn_main_call){ // 点击打电话
//                Toast.makeText(MainActivity.this, "点击打电话", Toast.LENGTH_SHORT).show();

//            1. 创建一个Intent(隐式)
                String action = Intent.ACTION_DIAL;
                Intent intent = new Intent(action);
//            2. 携带数据
                String number = et_main_number.getText().toString();
                intent.setData(Uri.parse("tel:"+number));
//            3. startActivity(intent)
                startActivity(intent);
            } else if (view==btn_main_send){ //点击发短信
//                Toast.makeText(MainActivity.this, "发短信", Toast.LENGTH_SHORT).show();

//                1. 创建一个Intent（隐式）
                Intent intent = new Intent(Intent.ACTION_SENDTO);
//                2. 携带数据（号码 / 内容）
                String number = et_main_number.getText().toString();
                String sms = et_main_sms.getText().toString();
                intent.setData(Uri.parse("smsto:"+number));
//                携带额外数据
                intent.putExtra("sms_body",sms);
//                3. startActivity（intent）
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        初始化视图对象
        et_main_number = findViewById(R.id.et_main_number);
        et_main_sms = findViewById(R.id.et_main_sms);
        btn_main_call = findViewById(R.id.btn_main_call);
        btn_main_send = findViewById(R.id.btn_main_send);

//        给视图对象设置点击监听
        btn_main_call.setOnClickListener(onClickListener);
        btn_main_send.setOnClickListener(onClickListener);

//        给图像设置长按监听
        btn_main_call.setOnLongClickListener(this);
        btn_main_send.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        if (view==btn_main_call){ // 长按打电话
//            Toast.makeText(MainActivity.this, "长按打电话", Toast.LENGTH_SHORT).show();
//            申请权限并拨打电话
            permissionCall();
        } else if (view==btn_main_send){ //长按发短信
//            Toast.makeText(MainActivity.this, "长按发短信", Toast.LENGTH_SHORT).show();
            permissionSMS();
        }
        return true; // 不会再触发了点击事件（表示此事件已经被消费了）
    }

//    申请权限打电话
    private void permissionCall(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //没有授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUESTCODE);
        }else{
            //已经授权
            call();
        }
    }

    //    申请权限发短信
    private void permissionSMS(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            //没有授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUESTCODE);
        }else{
            //已经授权
            sms();
        }
    }

//    打电话
    public void call(){
//            1.创建一个Intent（隐式）
        Intent intent = new Intent(Intent.ACTION_CALL); //android.intent.action.CALL
//            2.携带数据
        String number = et_main_number.getText().toString();
        intent.setData(Uri.parse("tel:"+number));
//            3. startActivity(intent)
        startActivity(intent);
    }

//    发送短信
    public void sms(){
//            1. 得到SMSManager的对象
        SmsManager smsManager = SmsManager.getDefault();
//            2. 发送文本信息（短信）
        String number = et_main_number.getText().toString();
        String sms = et_main_sms.getText().toString();
        smsManager.sendTextMessage(number, null, sms,null,null);
    }

//    判断用户是否授权回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUESTCODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //用户点击了同意授权
                    call();
                }else{
                    //用户拒绝了授权
                    Toast.makeText(this,"权限被拒绝",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}