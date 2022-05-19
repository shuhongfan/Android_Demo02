package com.shf.app34_layout;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TextView cancle;
    private TextView divider;
    private TextView mod;
    private TextView plus_or_minus;
    private TextView plus;
    private TextView minus;
    private TextView one;
    private TextView two;
    private TextView three;
    private TextView four;
    private TextView five;
    private TextView six;
    private TextView seven;
    private TextView eight;
    private TextView nine;
    private TextView zero;
    private TextView point;
    private TextView equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

//        找控件
        initView();

//        设置点击事件
        initClickEvent();
    }

    /**
     * 设置点击事件
     */
    private void initClickEvent() {
//        第一种设置方式
        cancle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (view instanceof TextView){
                    String text = ((TextView) view).getText().toString();
                    Log.d(TAG, "oneOnClick===="+text);
                }
            }
        });

//        第二种设置方式
        plus_or_minus.setOnClickListener(this);
    }

    /**
     * 统一处理点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
//        如果有多个控件设置点击事件，呢么我们这里统一处理的话，需要判断是哪个控件
        if (view==plus_or_minus){

        } else if (view==plus){

        }

        int id = view.getId();
        switch (id){
            case R.id.tv_number_one:
                break;
            case R.id.tv_equal:
                break;
        }
        if (view instanceof TextView){
            String text = ((TextView) view).getText().toString();
            Log.d(TAG, "oneOnClick===="+text);
        }
    }

    /**
     * 找控件
     */
    private void initView() {
        cancle = this.findViewById(R.id.tv_cancel);
        divider = this.findViewById(R.id.tv_divider);
        mod = this.findViewById(R.id.tv_mod);
        plus_or_minus = this.findViewById(R.id.tv_plus_or_minus);
        plus = this.findViewById(R.id.tv_plus);
        minus = this.findViewById(R.id.tv_minus);
        one = this.findViewById(R.id.tv_number_one);
        two = this.findViewById(R.id.tv_number_two);
        three = this.findViewById(R.id.tv_number_three);
        four = this.findViewById(R.id.tv_number_four);
        five = this.findViewById(R.id.tv_number_five);
        six = this.findViewById(R.id.tv_number_six);
        seven = this.findViewById(R.id.tv_number_seven);
        eight = this.findViewById(R.id.tv_number_eight);
        nine = this.findViewById(R.id.tv_number_nine);
        zero = this.findViewById(R.id.tv_number_zero);
        point = this.findViewById(R.id.tv_number_point);
        equal = this.findViewById(R.id.tv_equal);
    }

    public void oneOnClick(View view) {
        Log.d(TAG,"one be click...");
        if (view instanceof TextView){
            String text = ((TextView) view).getText().toString();
            Log.d(TAG, "oneOnClick===="+text);
        }
    }

    public void oneOrangeClick(View view) {
        Log.d(TAG,"oneOrangeClick be click...");
        if (view instanceof TextView){
            String text = ((TextView) view).getText().toString();
            Log.d(TAG, "oneOrangeClick===="+text);
        }
    }

}