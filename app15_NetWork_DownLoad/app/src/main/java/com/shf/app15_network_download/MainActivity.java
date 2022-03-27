package com.shf.app15_network_download;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 1.界面布局
 * 2.点击回调处理
 *  2.1.主线程，显示提示视图：ProgressDialog
 *  2.2.启动分线程，请求下载APK文件，下载过程中显示下载进度
 *  2.3.主线程，移除dialog，启动安装
 */
public class MainActivity extends AppCompatActivity {
    private File apkFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void downloadAPK(View v){
//         2.1.主线程，显示提示视图：ProgressDialog
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
//        准备用于保存APK文件的FIle对象 /storage/sdcard/Android/package_name/files/xxx.apk
        apkFile = new File(getExternalFilesDir(null),"update.apk");
//         2.2.启动分线程，请求下载APK文件，下载过程中显示下载进度
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                1.得到连接对象
                    URL url = new URL("http://172.20.10.3:8080/app.apk");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                2.设置
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(10000);
//                3.连接
                    connection.connect();
//                4.请求并得到响应码
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
//                        设置dialog的最大进度
                        dialog.setMax(connection.getContentLength());
//                5.得到包含APK文件数据的InputStream
                        InputStream is = connection.getInputStream();
//                6.创建一个指向apkFile的FileOutputStream
                        FileOutputStream fos = new FileOutputStream(apkFile);
//                7.边读边写
                        byte[] buffer = new byte[1024];
                        int len  = -1;
                        while ((len=is.read(buffer))!=-1){
                            fos.write(buffer,0,len);
//                8.显示下载进度
                            dialog.incrementProgressBy(len);
//                            休息一会（模拟网速满）
                            SystemClock.sleep(50);
                        }
//                9.下载完成，关闭
                        fos.close();
                        is.close();
                    }
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//         2.3.主线程，移除dialog，启动安装
        runOnUiThread(new Thread(){
            @Override
            public void run() {
                dialog.dismiss();
                installAPK();
            }
        });
    }

    /**
     * 启动安装APK
     */
    private void installAPK() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}