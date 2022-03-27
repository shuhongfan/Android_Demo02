package com.shf.app21_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shf.app21_service.service.MyService;

public class MainActivity extends AppCompatActivity {
    private ServiceConnection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    启动服务
    public void startService(View v){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        Toast.makeText(this," start service", Toast.LENGTH_SHORT).show();
    }

//    停止服务
    public void stopMyService(View v){
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        Toast.makeText(this,"stop service", Toast.LENGTH_SHORT).show();
    }

//    绑定服务
    public void bindMyService(View v){
        Intent intent = new Intent(this, MyService.class);
        if (connection==null){
            connection= new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Log.d("TAG", "onServiceConnected: ");
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    Log.d("TAG", "onServiceDisconnected: ");
                }
            };
//        绑定service
            bindService(intent,connection, Context.BIND_AUTO_CREATE);
            Toast.makeText(this,"bind service",Toast.LENGTH_SHORT ).show();
        } else {
            Toast.makeText(this,"unbind service", Toast.LENGTH_SHORT).show();
        }

    }

//    解绑服务
    public void unbindMyService(View v){
        if (connection!=null){
            unbindService(connection);
            connection = null;
            Toast.makeText(this,"unbind service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"还没有bindservice", Toast.LENGTH_SHORT).show();
        }
    }
}