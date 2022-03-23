package com.shf.app5_life;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public MainActivity(){
        Log.e("TAG", "MainActivity()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("TAG", "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.e("TAG", "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e("TAG", "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("TAG", "onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("TAG", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("TAG", "onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.e("TAG", "onRestart()");
        super.onRestart();
    }

    public void startSecond(View v){
        startActivity(new Intent(this,SecondActivity.class));
    }

    public void startFirst(View v){
        startActivity(new Intent(this,MainActivity.class));
    }
}