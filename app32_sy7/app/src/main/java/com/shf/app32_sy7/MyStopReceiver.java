package com.shf.app32_sy7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyStopReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        
        context.stopService(MainActivity.intent1);
    }
}