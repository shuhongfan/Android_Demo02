package com.shf.app39_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shf.app39_service.actions.interfaces.INormalUserAction;

public class NormalUserActivity extends Activity {

    private static final String TAG = "NormalUserActivity";
    private NormalUserConnection normalUserConnection;
    private boolean isBind;

//    private INormalUserAction normalUserAction;
    private NormalUserAction normalUserAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user);

        doBindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind && normalUserConnection != null) {
            Log.d(TAG,"Unbind service");
            unbindService(normalUserConnection);
        }
    }

    /**
     * 绑定服务
     */
    private void doBindService() {
        Intent intent = new Intent();
        intent.setAction("com.shf.ACTION_NORMAL_USER");
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage(this.getPackageName());
        normalUserConnection = new NormalUserConnection();
        isBind = bindService(intent, normalUserConnection, BIND_AUTO_CREATE);
    }

    public void saveMoneyClick(View view) {
        Log.d(TAG,"saveMoneyClick");
        try {
            normalUserAction.saveMoney(10000);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMoneyClick(View view) {
        Log.d(TAG,"getMoneyClick");
        try {
            normalUserAction.getMoney();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void loanMoneyClick(View view) {
        Log.d(TAG,"loanMoneyClick");
        try {
            normalUserAction.loanMoney();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private class NormalUserConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"onServiceConnected..."+componentName);
//            normalUserAction = (INormalUserAction) iBinder;
            NormalUserAction normalUserAction = NormalUserAction.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG,"onServiceConnected..."+componentName);
        }
    }
}