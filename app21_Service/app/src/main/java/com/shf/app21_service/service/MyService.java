package com.shf.app21_service.service;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

/**
 * 自定义本地服务
 */
public class MyService extends Service {
    public MyService() {
        Log.e("TAG", "MyService()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("TAG", "MyService onBind: ");
        return new Binder();
    }

    @Override
    public void onCreate() {
        Log.d("TAG", "MyService onCreate: ");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d("TAG", "MyService onDestroy: ");
        super.onDestroy();
    }

}
