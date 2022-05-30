package com.shf.app39_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shf.app39_service.actions.interfaces.IBankBossAction;
import com.shf.app39_service.actions.interfaces.IBankWorkAction;

public class BankBossActivity extends Activity {

    private static final String TAG = "BankBossActivity";
    private BankBossConnection bankBossConnection;
    private boolean isBind;

    private IBankBossAction bankBossActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_boss);

        doBindService();
    }

    /**
     * 绑定服务
     */
    private void doBindService() {
        Intent intent = new Intent();
        intent.setAction("com.shf.ACTION_NORMAL_BOSS");
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage(this.getPackageName());
        bankBossConnection = new BankBossConnection();
        isBind = bindService(intent, bankBossConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind && bankBossConnection!=null){
            Log.d(TAG, "Unbind service");
            unbindService(bankBossConnection);
        }
    }

    private class BankBossConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"onServiceConnected..."+componentName);
            bankBossActivity = (IBankBossAction) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG,"onServiceConnected..."+componentName);
        }
    }

    public void modifyAccountMoneyClick(View view) {
        Log.d(TAG,"modifyAccountMoneyClick");
        bankBossActivity.modifyUserAccountMoney(10000.00);
    }
}