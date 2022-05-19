package com.shf.app36_activity;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LifeCycleActivity3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle3);
    }
}