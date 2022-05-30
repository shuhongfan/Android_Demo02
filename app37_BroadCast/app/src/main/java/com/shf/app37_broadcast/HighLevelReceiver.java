package com.shf.app37_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class HighLevelReceiver extends BroadcastReceiver {

    private static final String TAG = "HighLevelReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG,"high action is --->"+action);

//        终止往下传达
//        abortBroadcast();

//        修改广播内容
        Bundle resultExtras = getResultExtras(true);
        String content = resultExtras.getCharSequence("content").toString();
        Log.d(TAG, "content--->"+content);

        resultExtras.putCharSequence("content", "我是被修改过的内容...");
        setResultExtras(resultExtras);
    }
}