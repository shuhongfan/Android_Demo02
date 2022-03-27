package com.shf.app22_serviceclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection connection;
    private EditText et_aidl_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_aidl_id = findViewById(R.id.et_aidl_id);
    }

    public void bindRemoteService(View v){
        Intent intent = new Intent("com.shf.app21_service.romote.MyRemoteService");
        if (connection==null){
            connection=new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {

                }
            };
           bindService(intent, connection,BIND_AUTO_CREATE);
           Toast.makeText(this,"绑定Service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"已经绑定Service", Toast.LENGTH_SHORT).show();
        }
    }

    public void invokeRemote(View v){

    }

    public void unbindRemoteService(View v){
        if (connection!=null){
            unbindService(connection);
            connection=null;
            Toast.makeText(this, "解绑service", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "还未绑定service", Toast.LENGTH_SHORT).show();
        }
    }
}