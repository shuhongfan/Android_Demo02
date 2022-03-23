package com.shf.app8_component;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 简单的Component
 */
public class SimpleComponentActivity extends AppCompatActivity {

    private TextView tv_simple_message;
    private EditText et_simple_number;
    private Button btn_simple_submit;
    private ImageView btn_simple_play;

    private CheckBox cb_simple_basket;
    private CheckBox cb_simple_foot;
    private CheckBox cb_simple_pingpang;

    private RadioGroup rg_simple_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_component);

//        1.TextView
        tv_simple_message = findViewById(R.id.tv_simple_message);
        tv_simple_message.setText("我是TextView");

//        2.EditText
        et_simple_number = findViewById(R.id.et_simple_number);

//        3.Button
        btn_simple_submit = findViewById(R.id.btn_simple_submit);
        btn_simple_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               1. 得到内容
                String number = et_simple_number.getText().toString();
//                2.提示
                Toast.makeText(SimpleComponentActivity.this, number.toString(), Toast.LENGTH_SHORT).show();
            }
        });

//        4.ImageView
        btn_simple_play = findViewById(R.id.btn_simple_play);
        btn_simple_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                设置背景图片
                btn_simple_play.setBackgroundResource(android.R.drawable.alert_light_frame);
//                设置前景图片
                btn_simple_play.setImageResource(android.R.drawable.ic_media_pause);
            }
        });

//        5.CheckBox
        cb_simple_basket = findViewById(R.id.cb_simple_basket);
        cb_simple_foot = findViewById(R.id.cb_simple_foot);
        cb_simple_pingpang = findViewById(R.id.cb_simple_pingpang);
//        给cb_simple_foot设置选中状态改变的监听
        cb_simple_foot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(SimpleComponentActivity.this, "选中了足球",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SimpleComponentActivity.this, "未选中足球", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        6.RadioGroup
        rg_simple_sex = findViewById(R.id.rg_simple_sex);
        rg_simple_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) { // checkId 选中 radioButton的id
//                找到未选中的radioButton
                RadioButton radioButton = findViewById(checkId);
//                得到文本
                String sex = radioButton.getText().toString();
//                提示
                Toast.makeText(SimpleComponentActivity.this, sex,Toast.LENGTH_SHORT);
            }
        });
    }

    public void confirm(View v){
        StringBuffer sb = new StringBuffer();

        if (cb_simple_basket.isChecked()){
            sb.append(cb_simple_basket.getText().toString()).append("  ");
        }

        if (cb_simple_pingpang.isChecked()){
            sb.append(cb_simple_pingpang.getText().toString()).append("   ");
        }

        if (cb_simple_foot.isChecked()){
            sb.append(cb_simple_foot.getText().toString()).append("  ");
        }

//        提示
        Toast.makeText(this, sb,Toast.LENGTH_SHORT).show();
    }
}