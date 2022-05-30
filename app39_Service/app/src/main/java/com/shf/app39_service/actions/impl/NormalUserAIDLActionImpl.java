package com.shf.app39_service.actions.impl;

import android.os.IBinder;
import android.util.Log;
import com.shf.app39_service.NormalUserAction;

public class NormalUserAIDLActionImpl extends NormalUserAction.Stub {
    private static final String TAG = "NormalUserAIDLActionImpl";

    @Override
    public void saveMoney(float money) {
        Log.d(TAG,"normal user save money--->"+money);
    }

    @Override
    public double getMoney() {
        return 1000.00;
    }

    @Override
    public double loanMoney() {
        Log.d(TAG,"loanMoney---> 100.00");
        return 100.00;
    }
}
