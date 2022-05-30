package com.shf.app37_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * 监听应用的安装与卸载
 */
public class AppStateChangeReceiver extends BroadcastReceiver {

    private static final String TAG = "AppStateChangeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)){
//            应用安装
            Log.d(TAG,"应用安装了，它的相关信息是："+intent.getData());
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)){
//            应用卸载
            Log.d(TAG,"应用卸载了，它的相关信息是："+intent.getData());
        }
    }

}