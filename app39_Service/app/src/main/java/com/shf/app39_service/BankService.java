package com.shf.app39_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.shf.app39_service.actions.impl.BankBossActionImpl;
import com.shf.app39_service.actions.impl.BankWorkActionImpl;
import com.shf.app39_service.actions.impl.NormalUserAIDLActionImpl;
import com.shf.app39_service.actions.impl.NormalUserActionImpl;

public class BankService extends Service {
    public BankService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)){
            if ("com.shf.ACTION_NORMAL_USER".equals(action)){
//                return new NormalUserActionImpl();
                return new NormalUserAIDLActionImpl();
            } else if ("com.shf.ACTION_NORMAL_WORKER".equals(action)){
                return new BankWorkActionImpl();
            } else if ("com.shf.ACTION_NORMAL_BOSS".equals(action)){
                return new BankBossActionImpl();
            }
        }
        return null;
    }
}