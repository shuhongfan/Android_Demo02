package com.shf.app36_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SixthActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_For_PIC = 1;
    private static final int REQUEST_CODE_For_Video = 2;
    private ImageView takePhoto;
    private ImageView photoResultContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        initView();

        initListener();
    }

    private void initListener() {
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                拍照的按钮被点击以后，要跳转到系统的相机界面
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(intent, REQUEST_CODE_For_PIC);
            }
        });
    }

    private void initView() {
        photoResultContainer = findViewById(R.id.photo_result_container);
        takePhoto = findViewById(R.id.take_photo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_For_PIC){
            if (resultCode==RESULT_CANCELED){
//                说明失败了
                Toast.makeText(this, "您取消了拍照！", Toast.LENGTH_SHORT).show();
            } else if (resultCode==RESULT_OK && data!=null){
//                说明成功返回
                Bitmap pic = data.getParcelableExtra("data");
                if (pic!=null){
                    photoResultContainer.setImageBitmap(pic);
                }
            }
        }
    }
}