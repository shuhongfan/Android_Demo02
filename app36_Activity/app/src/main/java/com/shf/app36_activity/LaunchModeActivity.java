package com.shf.app36_activity;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
    }

    public void openFirstActivity(View view) {
        startActivity(new Intent(this,LaunchModeActivity.class));
    }

    public void openSecondActivity(View view) {
        startActivity(new Intent(this,LaunchModeActivity2.class));
    }
}