package com.shf.app37_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.shf.app37_broadcast.Constants.KEY_CONTENT;

public class MessageReceiver extends BroadcastReceiver {

    private static final String TAG = "MessageReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "actions is --->"+action);
        String content = intent.getStringExtra(KEY_CONTENT);
        Log.d(TAG,"content is --->"+content);
    }
}