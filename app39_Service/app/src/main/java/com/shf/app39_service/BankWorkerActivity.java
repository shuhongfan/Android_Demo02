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
import com.shf.app39_service.actions.interfaces.IBankWorkAction;

public class BankWorkerActivity extends Activity {
    private static final String TAG = "BankWorkerActivity";
    private IBankWorkAction bankWorkerActivity;
    private BankWorkerConnection bankWorkerConnection;
    private boolean isBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_worker);

        doBindService();
    }

    public void checkUserCreditClick(View view) {
        Log.d(TAG,"checkUserCreditClick");
        bankWorkerActivity.checkUserCredit();
    }

    public void freezeAccountClick(View view) {
        Log.d(TAG,"freezeAccountClick");
        bankWorkerActivity.freezeUserAccount();
    }

    /**
     * 绑定服务
     */
    private void doBindService(){
        Intent intent = new Intent();
        intent.setAction("com.shf.ACTION_NORMAL_WORKER");
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage(this.getPackageName());
        bankWorkerConnection = new BankWorkerConnection();
        isBind = bindService(intent, bankWorkerConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind && bankWorkerConnection != null) {
            Log.d(TAG, "Unbind service");
            unbindService(bankWorkerConnection);
        }
    }

    private class BankWorkerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"onServiceConnected..."+componentName);
            bankWorkerActivity= (IBankWorkAction) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG,"onServiceConnected..."+componentName);
        }
    }
}