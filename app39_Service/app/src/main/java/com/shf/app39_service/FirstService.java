package com.shf.app39_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.shf.app39_service.actions.interfaces.ICommunication;

public class FirstService extends Service {
    private static final String TAG = FirstService.class.getName();

    public class InnerBinder extends Binder implements ICommunication {
        @Override
        public void callServiceInnerMethod(){
            sayHello();
        }

    }

    public FirstService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return new InnerBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void sayHello(){
        Toast.makeText(this,"hello", Toast.LENGTH_SHORT).show();
    }
}