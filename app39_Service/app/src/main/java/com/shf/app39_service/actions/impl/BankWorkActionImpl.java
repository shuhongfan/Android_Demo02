package com.shf.app39_service.actions.impl;

import android.os.Binder;
import android.util.Log;
import com.shf.app39_service.actions.interfaces.IBankWorkAction;

public class BankWorkActionImpl extends Binder implements IBankWorkAction {
    private static final String TAG = "BankWorkImpl";

    @Override
    public void checkUserCredit() {
        Log.d(TAG,"checkUserCredit--->790");
    }

    @Override
    public void freezeUserAccount() {
        Log.d(TAG,"freezeUserAccount--->0");
    }

    @Override
    public void saveMoney(float money) {
        Log.d(TAG,"saveMoney--->"+money);
    }

    @Override
    public Double getMoney() {
        Log.d(TAG,"getMoney---> 100.00");
        return 100.00;
    }

    @Override
    public Double loanMoney() {
        Log.d(TAG,"loanMoney---> 100.00");
        return 100.00;
    }
}
