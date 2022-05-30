package com.shf.app38_broadcast;

import android.content.IntentFilter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private OrderBroadCastReceiver orderBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register();
    }

    private void register(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.shf.app37_broadcast.ORDER_BROADCAST_TEST");

        orderBroadCastReceiver = new OrderBroadCastReceiver();
        registerReceiver(orderBroadCastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(orderBroadCastReceiver);
    }
}