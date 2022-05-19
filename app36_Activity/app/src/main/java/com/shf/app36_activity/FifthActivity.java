package com.shf.app36_activity;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class FifthActivity extends AppCompatActivity {

    private static final String TAG = "FifthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        Intent intent = getIntent();
        if(intent!=null){
            int intKey = intent.getIntExtra("intKey", -1);
            boolean booleanKey = intent.getBooleanExtra("booleanKey", false);
            Log.d(TAG, "int intKey==="+intKey);
            Log.d(TAG, "int booleanKey==="+booleanKey);

            User user = intent.getParcelableExtra("userKey");
            if (user!=null){
                Log.d(TAG, "int user==="+user);
            }
        }
    }
}