package com.shf.app35_data_storage;

import android.content.Context;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.Format;

public class SDCardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button writeDataBtn;
    private String TAG ="SDCardActivity";
    private Button checkSDCard;
    private Button get_sd_card_free_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);

        initView();
        initListener();
    }

    /**
     * 监听方法
     */
    private void initListener() {
        writeDataBtn.setOnClickListener(this);
        checkSDCard.setOnClickListener(this);
        get_sd_card_free_size.setOnClickListener(this);
    }

    /**
     * 监听视图
     */
    private void initView() {
        writeDataBtn = this.findViewById(R.id.write_data_2_sd_card_btn);
        checkSDCard = this.findViewById(R.id.checkSDCard);
        get_sd_card_free_size = this.findViewById(R.id.get_sd_card_free_size);
    }

    @Override
    public void onClick(View view) {
//        不同手机厂商 SD卡名称不同
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Log.d(TAG, "SD卡目录名称："+externalStorageDirectory.toString());

        if (view==writeDataBtn){
//            写数据到sd卡
            try {
                File filesDir = getExternalFilesDir(null);
                File file = new File(filesDir, "info.text");
                Log.d(TAG,file.toString());
                if(!file.exists())
                {
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                fos.write("hello".getBytes());
                fos.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (view==checkSDCard){
//            点击的是检查是否有SD卡
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)){
                Log.d(TAG, "SD卡已经挂载，可用！@");
            } else if (state.equals(Environment.MEDIA_REMOVED)){
                Log.d(TAG,"SD卡已经移除了");
            }
        } else if (view==get_sd_card_free_size){
            File Ext_file = Environment.getExternalStorageDirectory();
            Log.d(TAG, "Ext-FilePath"+Ext_file);
            long freeSpace = Ext_file.getFreeSpace();
            String fileSize = Formatter.formatFileSize(this, freeSpace);
            Log.d(TAG, "free size=="+fileSize);
        }
    }
}