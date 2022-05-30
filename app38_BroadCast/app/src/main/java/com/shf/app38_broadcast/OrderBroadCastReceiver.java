package com.shf.app38_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class OrderBroadCastReceiver extends BroadcastReceiver {

    private static final String TAG = "OrderBroadCastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action==="+action);

    }



}