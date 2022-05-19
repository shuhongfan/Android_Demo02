package com.shf.app35_data_storage;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PreferenceActivity extends AppCompatActivity implements  CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "PreferenceActivity";
    private Switch isAllowUnknowAppsSource;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

//        找到控件
        isAllowUnknowAppsSource = findViewById(R.id.is_allow_unknown_apps_sources_switch);
        isAllowUnknowAppsSource.setOnCheckedChangeListener(this);

//        第一步：创建shareReference
        sharedPreferences = this.getSharedPreferences("settings_info", MODE_PRIVATE);

//        第五步： 数据回显
        boolean state = sharedPreferences.getBoolean("state", false);
        isAllowUnknowAppsSource.setChecked(state);
    }


    /**
     * switch改变时调用
     * @param compoundButton
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//        我们在这里需要对数据进行保存
        Log.d(TAG, "current state==="+isChecked);

//        第二步：进入编辑器模式
        SharedPreferences.Editor edit = sharedPreferences.edit();

//        第三步：保存数据
        edit.putBoolean("state", isChecked);

//        第四步：提交编辑器
        edit.commit();
    }
}