package com.shf.app16_handler;

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

    public void testHandler(View v) {
        startActivity(new Intent(this, HandlerTestActivity.class));
    }

    public void handlerDemo(View v) {
        startActivity(new Intent(this, HandlerDemoActivity.class));
    }

    public void testAsyncTask(View v) {
        startActivity(new Intent(this, AsyncTaskTestActivity.class));
    }
}