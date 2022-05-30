package com.shf.app39_service;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BankActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

    }

    /**
     * 普通用户被点击了
     * @param view
     */
    public void normalUserClick(View view) {
        startActivity(new Intent(this,NormalUserActivity.class));
    }

    /**
     * 银行工作人员被点击了
     * @param view
     */
    public void bankWorkerClick(View view) {
        startActivity(new Intent(this,BankWorkerActivity.class));
    }

    /**
     * 老板被点击了
     * @param view
     */
    public void bankBossClick(View view) {
        startActivity(new Intent(this,BankBossActivity.class));
    }
}