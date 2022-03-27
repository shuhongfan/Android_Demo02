package com.shf.app14_datastorage;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUESTCODE = 1;
    private EditText et_network_url;
    private EditText et_network_result;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        permissionNETWORK();

        et_network_url = findViewById(R.id.et_network_url);
        et_network_result = findViewById(R.id.et_network_result);

        queue = Volley.newRequestQueue(this);
    }

    /**
     * 使用HttpConnection
     * @param v
     */
    public void testConnectionGet(View v){
//        1.显示ProgressDialog
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");

//        2.启动分离线程
        new Thread(){
//            3.在分线程,发送请求,得到响应数据
            @Override
            public void run(){
                try {
//                1. 得到path,并带上参数name=Tom&age=11
                    String path = et_network_url.getText().toString() + "?name=tom1&age=36";
//                2. 创建URL对象
                    URL url = new URL(path);
//                3. 设置请求方式，得到HttpURLConnection对象
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                4.设置请求方式，连接超时，读取数据超时
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(6000);
//                5.连接服务器
                    connection.connect();
//                6. 发请求，得到相应数据
                    int responseCode = connection.getResponseCode();
//                7. 得到响应码，必须是200才读取
                    if (responseCode==200){
    //                8.得到InputStream，并读取成String
                        InputStream is = connection.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while (((len=is.read(buffer))!=-1)){
                            baos.write(buffer,0,len);
                        }
                        final String result = baos.toString();

                        baos.close();
                        is.close();

//                        4.在主线程，显示得到的结果，一处dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_result.setText(result);
                                dialog.dismiss();
                            }
                        });
                    }
//                    5.断开连接
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
//                    如果出了异常要移除dialog
                    dialog.dismiss();
                }
            }
        }.start();
    }

    /**
     * 使用httpUrlConnection提交post请求
     * @param v
     */
    public void testConnectionPost(View v){
//        1. 显示ProgressDialog
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");
//        2.启动分线程
        new Thread(new Runnable(){
//            3. 在分线程，发送请求，得到响应数据
            @Override
            public void run() {
                try {
//                1. 得到path
                    String path = et_network_url.getText().toString();
//                2.创建URL对象
                    URL url = new URL(path);
//                    3.打开链接，得到HttpURLConnection对象
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    4. 设置请求方式，连接超时，读取数据超时
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
//                    5. 连接服务器
                    connection.connect();
//                    6. 发送请求，得到响应数据
                    OutputStream os = connection.getOutputStream();
//                          得到输出流，写请求体：name=Tom&age=11
                    String data = "name=Tom1&age=13";
                    os.write(data.getBytes(StandardCharsets.UTF_8));
//                          得到响应码，必须是200才读取
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
//                          得到InputStream，并读取成String
                        InputStream is = connection.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while (((len=is.read(buffer))!=-1)){
                            baos.write(buffer,0,len);
                        }
                        final String result = baos.toString();

                        baos.close();
                        is.close();

//                        7.在主线程，显示得到的结果，移除dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_result.setText(result);
                                dialog.dismiss();
                            }
                        });
                    }
                    os.close();
//                    8.断开连接
                } catch (IOException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }
        }).start();
    }

    /**
     * 使用httpClient提交get请求
     * @param v
     */
    public void testClientGet(View v){
//        1.显示ProgressDialog
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");

//        2.启动分离线程
        new Thread(){
            //            3.在分线程,发送请求,得到响应数据
            @Override
            public void run(){
                try {
//                1. 得到path,并带上参数name=Tom&age=11
                    String path = et_network_url.getText().toString() + "?name=tom1&age=36";
//                    2.创建Client对象
                    HttpClient httpClient = HttpClientBuilder.create().build();//获取DefaultHttpClient请求
//                    3.设置超时
                    HttpParams params = httpClient.getParams();
                    HttpConnectionParams.setConnectionTimeout(params,5000);
                    HttpConnectionParams.setSoTimeout(params,5000);
//                    4.创建请求对象
                    HttpGet request = new HttpGet(path);
//                    5.执行请求对象，得到相应对象
                    HttpResponse response = httpClient.execute(request);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode==200){
//                    6.得到相应体文本
                        HttpEntity entity = response.getEntity();
                        final String result = EntityUtils.toString(entity);
//                    7.到主线程,显示数据，移除dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_result.setText(result);
                                dialog.dismiss();
                            }
                        });
                    }
//                    6.断开连接
                    httpClient.getConnectionManager().shutdown();
                } catch (IOException e) {
                    e.printStackTrace();
//                    如果出了异常要移除dialog
                    dialog.dismiss();
                }
            }
        }.start();
    }

    /*
     * 使用httpClient提交post请求
     */
    public void testClientPost(View v) {
        //1. 显示ProgressDialog
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");
        //2. 启动分线程
        new Thread(){
            //3. 在分线程, 发送请求, 得到响应数据
            @Override
            public void run() {
                try {
                    //1). 得到path
                    String path = et_network_url.getText().toString();

                    //2). 创建HttpClient对象
                    HttpClient httpClient = new DefaultHttpClient();
                    //3). 设置超时
                    HttpParams params = httpClient.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 5000);
                    HttpConnectionParams.setSoTimeout(params, 5000);
                    //4). 创建请求对象
                    HttpPost request = new HttpPost(path);
                    //设置请求体
                    List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
                    parameters.add(new BasicNameValuePair("name", "Tom4"));
                    parameters.add(new BasicNameValuePair("age", "14"));
                    HttpEntity entity = new UrlEncodedFormEntity(parameters);
                    request.setEntity(entity);

                    //5). 执行请求对象, 得到响应对象
                    HttpResponse response = httpClient.execute(request);

                    int statusCode = response.getStatusLine().getStatusCode();
                    if(statusCode==200) {
                        //6). 得到响应体文本
                        entity = response.getEntity();
                        final String result = EntityUtils.toString(entity);
                        //4. 要主线程, 显示数据, 移除dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_result.setText(result);
                                dialog.dismiss();
                            }
                        });
                    }
                    //7). 断开连接
                    httpClient.getConnectionManager().shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                    //如果出了异常要移除dialog
                    dialog.dismiss();
                }
            }
        }.start();
    }

    public void testVolleyGet(View v){
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");

        //创建请求对象StringRequest
        String path = et_network_url.getText().toString()+"?name=Tom5&age=15";
        StringRequest request = new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//在主线程执行
                et_network_result.setText(response);
                dialog.dismiss();
            }
        }, null);
        //将请求添加到队列中
        queue.add(request);
    }

    public void testVolleyPost(View v){
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在请求中...");

        //创建请求对象StringRequest
        String path = et_network_url.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                et_network_result.setText(response);
                dialog.dismiss();
            }
        }, null){
            //重写此方法返回参数的map作为请求体
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", "Tom6");
                map.put("age", "16");
                return map;
            }
        };
        //将请求添加到队列中
        queue.add(request);
    }

    //    申请网络权限
    private void permissionNETWORK(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            //没有授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUESTCODE);
        }else{
            //已经授权

        }
    }
}