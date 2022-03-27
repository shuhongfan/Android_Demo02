package com.shf.app16_handler;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandlerTestActivity extends AppCompatActivity {

    private ProgressBar pb_handler1_loading;
    private EditText et_handler1_result;

    //1.创建Handler成员变量，并重写其他HandleMessage（）
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
           if (msg.what==1){
               //            4.在handleMessage（）中处理消息
               String result = (String) msg.obj;
               et_handler1_result.setText(result);
               pb_handler1_loading.setVisibility(View.INVISIBLE);
           }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

        pb_handler1_loading = findViewById(R.id.pb_handler1_loading);
        et_handler1_result = findViewById(R.id.et_handler1_result);
    }

    /**
     * 测试handler的基本使用
     * 1.创建Handler成员变量，并重写其他HandleMessage（）
     * 2.在分/主线程创建Message对象
     * 3.使用handler对象发送Message
     * 4.在handleMessage（）中处理消息
     * @param v
     */
    public void getSubmit2(View v){
//        1.主线程，显示提示视图（progressDialog / ProgressBar）
        pb_handler1_loading.setVisibility(View.VISIBLE);
//        2 在分/主线程创建Message对象
        new Thread(){
            @Override
            public void run() {
                String path = "http://172.20.10.3:8080/index.jsp?name=svds&age=56";
                try {
                    String result = requestToString(path);
//                    3.使用handler对象发送Message
                    Message message = Message.obtain();
//                    标识
                    message.what = 1;
                    message.obj=result;
//                    3.使用handler对象发送Message
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 1.主线程，显示提示视图（progressDialog / ProgressBar）
     * 2.分线程，联网请求，并得到相应数据
     * 3.主线程，显示将数据,隐藏提示视图
     * @param v
     */
    public void getSubmit1(View v){
//        1.主线程，显示提示视图（progressDialog / ProgressBar）
        pb_handler1_loading.setVisibility(View.VISIBLE);
//        2.分线程，联网请求，并得到相应数据
        new Thread(){
            @Override
            public void run() {
                String path = "http://172.20.10.3:8080/index.jsp?name=svds&age=56";
                try {
                    String result = requestToString(path);
//                    3.主线程，显示将数据
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            et_handler1_result.setText(result);
                            pb_handler1_loading.setVisibility(View.INVISIBLE);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 请求服务器端, 得到返回的结果字符串
     * @param path  : http://192.168.30.165:8080/Web_server/index.jsp?username=tom&age=12
     * @return
     * @throws Exception
     */
    public String requestToString(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();
        InputStream is = connection.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        baos.close();
        is.close();
        String result = baos.toString();
        connection.disconnect();

        return result;
    }
}