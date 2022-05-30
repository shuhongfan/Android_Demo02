package com.shf.app39_service.actions.impl;

import android.os.Binder;
import android.util.Log;
import com.shf.app39_service.actions.interfaces.INormalUserAction;

public class NormalUserActionImpl extends Binder implements INormalUserAction {
    private static final String TAG = "NormalUserImpl";

    @Override
    public void saveMoney(float money) {
        Log.d(TAG,"saveMoney--->"+money);
    }

    @Override
    public Double getMoney() {
        Log.d(TAG,"saveMoney---> 100.00");
        return 100.00;
    }

    @Override
    public Double loanMoney() {
        Log.d(TAG,"loanMoney---> 100.00");
        return 100.00;
    }
}
