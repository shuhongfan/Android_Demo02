package com.shf.app36_activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Skip2BrowserActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Skip2BrowserActivity";
    private Button skipToBrowser;
    private Button skipToMSG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip2_browser);

        skipToBrowser = findViewById(R.id.skipToBrowser);
        skipToMSG = findViewById(R.id.skipToMSG);
        skipToBrowser.setOnClickListener(this);
        skipToMSG.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.d(TAG, "Skip2BrowserActivity");

        if (view==skipToBrowser){
            /**
             * 隐式跳转
             */
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("http://www.baidu.com"));
            startActivity(intent);
        } else if (view==skipToMSG){
            /**
             * 隐式跳转
             */
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SENDTO");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("sms:"));
            intent.putExtra("sms_body", "推荐您使用一款软件，我最近都在使用它，挺不错的");
            startActivity(intent);
        }

    }
}