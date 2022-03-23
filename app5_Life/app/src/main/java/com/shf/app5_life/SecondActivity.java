package com.shf.app5_life;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    public SecondActivity(){
        Log.e("TAG","SecondActivity()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void startFirst(View v){
        startActivity(new Intent(this,MainActivity.class));
    }

    public void startThird(View v){
        startActivity(new Intent(this,ThirdActivity.class));
    }
}