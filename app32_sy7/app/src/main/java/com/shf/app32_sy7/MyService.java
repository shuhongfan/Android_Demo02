package com.shf.app32_sy7;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    boolean running = true;
    int i = 0;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (i < 200 && running) {
                    i++;
                    Log.i("i的值为：", String.valueOf(i));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        running = false;

        Log.i("程序停止", "程序停止onDestroy");

        super.onDestroy();
    }
}