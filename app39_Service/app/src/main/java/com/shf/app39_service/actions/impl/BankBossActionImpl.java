package com.shf.app39_service.actions.impl;

import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.shf.app39_service.actions.interfaces.IBankBossAction;

public class BankBossActionImpl extends Binder implements IBankBossAction {
    private static final String TAG = "BankBossActionImpl";

    @Override
    public void modifyUserAccountMoney(Double money) {
        Log.d(TAG,"modifyUserAccountMoney--->999999.99");
    }

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
