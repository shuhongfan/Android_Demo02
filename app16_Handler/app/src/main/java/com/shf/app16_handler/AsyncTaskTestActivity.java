package com.shf.app16_handler;

import android.os.AsyncTask;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AsyncTaskTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_test);
    }

    public void downloadApk(View v){
//        启动异步任务处理
        new AsyncTask<Void,Void,Void>(){
//            1.主线程显示提示视图
            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }

//            2.分线程，联网请求

        }.execute();
    }
}