package com.shf.app14_datastorage;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IFActivity extends AppCompatActivity {

    private ImageView iv_if;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ifactivity);

        iv_if = findViewById(R.id.iv_if);
    }

    public void save(View v) throws IOException {
//        1.得到InputStream 读取assets下的logo.png ---> InputStream
//                得到AssetManager
        AssetManager manager = getAssets();
//            读取文件
        InputStream is = manager.open("logo.png");
//        2.得到OutPutStream /data/data/packageName/files/logo.png
        FileOutputStream fos = openFileOutput("logo.png", Context.MODE_PRIVATE);
//        3.边读边写
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len=is.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }
        fos.close();
        is.close();
//        4.提示
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    public void read(View v){
//        1.得到图片文件路径
        String filesPath = getFilesDir().getAbsolutePath();
        String imagePath = filesPath + "/logo.png";
//        2.读取加载图片文件得到Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//        3.将其设置到imageView中显示
        iv_if.setImageBitmap(bitmap);
    }
}