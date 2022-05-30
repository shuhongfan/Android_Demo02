package com.shf.app37_broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static com.shf.app37_broadcast.Constants.ACTION_ORDER_BROADCAST_TEST;

public class SendOrderBroadCastActivity extends AppCompatActivity {


    private HighLevelReceiver highLevelReceiver;
    private DefaultLevelReceiver defaultLevelReceiver;
    private LowLevelReceiver lowLevelReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order_broad_cast);

        registerHigh();
        registerDefault();
        registerLow();
    }

    /**
     * 发送广播
     * @param view
     */
    public void sendOrdBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction(ACTION_ORDER_BROADCAST_TEST);

        Bundle bundle = new Bundle();
        bundle.putCharSequence("content", "我是被发送的广播内容...");
        sendOrderedBroadcast(intent,"com.shf.app37_broadcast.ORDER_PERMISSION", null, null, RESULT_OK, null, bundle);
    }

    /**
     * 动态注册广播接收者
     */
    private void registerHigh(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_ORDER_BROADCAST_TEST);
//        Priority表示等级，值是-1000~1000，默认是0
        intentFilter.setPriority(1000);

        highLevelReceiver = new HighLevelReceiver();
        registerReceiver(highLevelReceiver,intentFilter);
    }

    private void registerDefault(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_ORDER_BROADCAST_TEST);
//        Priority表示等级，值是-1000~1000，默认是0
        intentFilter.setPriority(0);

        defaultLevelReceiver = new DefaultLevelReceiver();
        registerReceiver(defaultLevelReceiver,intentFilter);
    }

    private void registerLow(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_ORDER_BROADCAST_TEST);
//        Priority表示等级，值是-1000~1000，默认是0
        intentFilter.setPriority(-1000);

        lowLevelReceiver = new LowLevelReceiver();
        registerReceiver(lowLevelReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(highLevelReceiver);
        unregisterReceiver(defaultLevelReceiver);
        unregisterReceiver(lowLevelReceiver);
    }
}