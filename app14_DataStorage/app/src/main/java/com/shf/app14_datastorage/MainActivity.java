package com.shf.app14_datastorage;

import android.content.Intent;
import android.net.Network;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    测试SP存储
    public void onClickSP(View v){
        startActivity(new Intent(this,SpActivity.class));
    }

    //    内部文件存储
    public void onClickIF(View v){
        startActivity(new Intent(this,IFActivity.class));
    }

    //    外部文件存储
    public void onClickOF(View v){
        startActivity(new Intent(this,OFActivity.class));
    }

    //    数据库存储
    public void onClickDB(View v){
        startActivity(new Intent(this,DBActivity.class));
    }

    //    网络存储
    public void onClickNW(View v){
        startActivity(new Intent(this, NetworkActivity.class));
    }
}