package com.shf.app20_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class MytextView extends androidx.appcompat.widget.AppCompatTextView {
//    new创建对象
    public MytextView(Context context) {
        super(context);
        Log.e("TAG", " MytextView(context)");
    }

//    布局文件创建对象
    public MytextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e("TAG", " MytextView(Context context, @Nullable AttributeSet attrs)");
    }

    /**
     * 只有布局的方式才会调用
     */
    @Override
    protected void onFinishInflate() {
        Log.e("TAG", " onFinishInflate()");
        super.onFinishInflate();
    }

    /**
     * 无论new还是布局都会调用此方法
     */
    @Override
    protected void onAttachedToWindow() {
        Log.e("TAG", " onAttachedToWindow()");
        super.onAttachedToWindow();
    }
}
