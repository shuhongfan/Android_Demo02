package com.shf.app18_event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
/**
 * 自定义ImageView
 */
public class MyImageView extends androidx.appcompat.widget.AppCompatImageView {

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.e("TAG", "MyImageView()");
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.e("TAG", "MyImageView dispatchTouchEvent() "+event.getAction());
		return super.dispatchTouchEvent(event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.e("TAG", "MyImageView onTouchEvent() "+event.getAction());
		return false;
		//return true;
	}
}
