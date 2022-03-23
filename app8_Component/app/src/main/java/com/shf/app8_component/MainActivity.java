package com.shf.app8_component;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main_test1).setOnClickListener(this);
        findViewById(R.id.btn_main_test2).setOnClickListener(this);
        findViewById(R.id.btn_main_test3).setOnClickListener(this);
        findViewById(R.id.btn_main_test4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_main_test1:  //常用简单的Component
                startActivity(new Intent(this,SimpleComponentActivity.class));
                break;
            case R.id.btn_main_test2:
                startActivity(new Intent(this,MenuActivity.class));
                break;
            case R.id.btn_main_test3:
                startActivity(new Intent(this,ProgressActivity.class));
                break;
            case R.id.btn_main_test4:

                break;
        }
    }
}