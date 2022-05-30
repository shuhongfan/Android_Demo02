package com.shf.app39_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shf.app39_service.actions.interfaces.ICommunication;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private boolean mIsServiceBinded;

    private ICommunication iCommunication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    /**
     * 开启服务
     * @param view
     */
    public void startServiceClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this,FirstService.class);
        startService(intent);
    }

    /**
     * 停止服务
     * @param view
     */
    public void stopServiceClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this,FirstService.class);
        stopService(intent);
    }

    public void callServiceMethod(){
        FirstService firstService = new FirstService();
        firstService.sayHello();
    }

    public void callServiceMethod(View view) {
        Log.d(TAG, "call service inner method.");
        FirstService firstService = new FirstService();
//        firstService.sayHello();
        iCommunication.callServiceInnerMethod();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected");
            iCommunication = (ICommunication) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected");
            iCommunication = null;
        }
    };

    /**
     * 绑定服务
     * @param view
     */
    public void bindServiceClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this,FirstService.class);
        mIsServiceBinded = bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     * @param view
     */
    public void unBindServiceClick(View view) {
        if (mConnection!=null && mIsServiceBinded){
            unbindService(mConnection);
        }
    }
}