package com.shf.app31_sy7;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        Intent intent = new Intent();
        intent.setAction("hello");
        sendBroadcast(intent);

    }

    public void stop(View view) {
        Intent intent2 = new Intent();
        intent2.setAction("hello2");
        sendBroadcast(intent2);
    }
}