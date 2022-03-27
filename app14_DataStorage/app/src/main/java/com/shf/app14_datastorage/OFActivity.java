package com.shf.app14_datastorage;

import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class OFActivity extends AppCompatActivity {

    private EditText et_of_name;
    private EditText et_of_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofactivity);

        et_of_name = findViewById(R.id.et_of_name);
        et_of_content = findViewById(R.id.et_of_content);
    }

    public void save(View v) throws IOException{
//        1.判断sd卡的状态，如果是挂在的状态才继续，否则提示
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//        2.读取输入的文件名 / 内容
            String fileName = et_of_name.getText().toString();
            String content = et_of_content.getText().toString();
//        3.得到指定文件的OutputStream
//            3.1 得到sd卡下的files路径
            String filesPath = getExternalFilesDir(null).getAbsolutePath();
//            3.2 组成完整路径
            String filePath = filesPath+"/"+fileName;
//            3.3 创建FileOutputStream
            FileOutputStream fos = new FileOutputStream(filePath);
//        4.写数据  /storage/sdcard/Android/data/packageName
            fos.write(content.getBytes(StandardCharsets.UTF_8));
            fos.close();
//        5.提示
            Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }

    public void read(View v)  throws IOException {
//        1.判断sd卡的状态，如果是挂在的状态才继续，否则提示
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//        2.读取输入的文件名 / 内容
            String fileName = et_of_name.getText().toString();
//        3.得到指定文件的OutputStream
//            3.1 得到sd卡下的files路径
            String filesPath = getExternalFilesDir(null).getAbsolutePath();
//            3.2 组成完整路径
            String filePath = filesPath+"/"+fileName;
//            3.3 创建FileInputStream
            FileInputStream fis = new FileInputStream(filePath);
//        4.读数据  /storage/sdcard/Android/data/packageName
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = - 1;
            while ((len=fis.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            String content = baos.toString();
            fis.close();
//        5.提示
            et_of_content.setText(content);
        } else {
            Toast.makeText(this, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }

//    /storage/sdcard/shf/xxx.txt
    public void save2(View v) throws IOException {
//        1.判断sd卡状态,如果时挂在的状态才继续,否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            2.得到指定文件的OutputStream
            String fileName = et_of_name.getText().toString();
            String content = et_of_content.getText().toString();
//            3.得到指定文件的OutPutStream
//            3.1 /storage/sdcard
            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//            3.2 /storage/sdcard/shf(创建文件夹)
            File file = new File(sdPath + "/shf");
            if (!file.exists()){
                file.mkdir(); // 创建文件夹
            }
//            3.3 /storage/sdcard/shf/xxx.txt
            String filePath = sdPath+"/shf/"+fileName;
//            4.写数据
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(content.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } else {
            Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
        }
    }

    public void read2(View v) throws IOException {
//        1.判断sd卡状态,如果时挂在的状态才继续,否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            2.读取输入的文件名
            String fileName = et_of_name.getText().toString();
//            3.得到指定文件的InputSream
            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String filePath = sdPath + "/shf/" + fileName;
            FileInputStream fis = new FileInputStream(filePath);
//            4.读取数据,成String
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len=fis.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            String content = baos.toString();
            fis.close();
            et_of_content.setText(content);
        } else {
            Toast.makeText(this, "保存完成", Toast.LENGTH_SHORT).show();
        }
    }
}