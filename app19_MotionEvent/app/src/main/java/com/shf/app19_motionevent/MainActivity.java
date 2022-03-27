package com.shf.app19_motionevent;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView iv_main;
    private RelativeLayout parentView;

    private int lastX;
    private int lastY;
    private int maxRight;
    private int maxBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_main = findViewById(R.id.iv_main);

        parentView = (RelativeLayout) iv_main.getParent();

        int right = parentView.getRight();
        int bottom = parentView.getBottom();
        Log.e("TAG", right+"-------"+bottom);

//        设置touch监听
        iv_main.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
//        所有的motionEvent都交给imageView处理
//            得到事件的坐标
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
//                得到父视图的right / bottom
                if (maxRight==0){
//                    保证只赋值一次
                    maxRight = parentView.getRight();
                    maxBottom = parentView.getBottom();
                }

//                第一记录lastX / lastY
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
//                计算事件的偏移
                int dx=rawX-lastX;
                int dy =rawY-lastY;
//                根据事件的偏移来移动imageView
                int left = iv_main.getLeft()+dx;
                int top = iv_main.getTop()+dy;
                int right = iv_main.getRight()+dx;
                int bottom = iv_main.getBottom()+dy;

//                限制left
                if (left<0){
                    right += -left;
                    left = 0;
                }
//                限制top
                if (top<0){
                    bottom += -top;
                    top=0;
                }
//                限制right
                if (right>maxRight){
                    left -= right-maxRight;
                    right=maxRight;
                }
//                限制bottom
                if (bottom>maxBottom){
                    top-=bottom-maxBottom;
                    bottom=maxBottom;
                }
                iv_main.layout(left, top,right, bottom);
//                再次记录lastX/lastY
                lastX=rawX;
                lastY=rawY;
                break;
            default:
                break;
        }
//        所有的motionEvent都交给imageView处理
        return true;
    }
}