package com.shf.app7_sy_2;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private ImageView img;
    private EditText radius;
    private RadioButton perimeter;
    private RadioButton area;
    private RadioButton volume;
    private Button btn;
    private RadioGroup rg;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        radius = findViewById(R.id.radius);
        perimeter = findViewById(R.id.perimeter);
        area = findViewById(R.id.area);
        volume = findViewById(R.id.volume);
        btn = findViewById(R.id.btn);
        rg = findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(this);
        btn.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//        Toast.makeText(getApplicationContext(), i+"", Toast.LENGTH_SHORT).show();
        switch (i){
            case R.id.perimeter:
                index=0;
                img.setImageResource(R.drawable.circle1);
                break; 
            case R.id.area:
                index=1;
                img.setImageResource(R.drawable.circle2);
                break;
            case R.id.volume:
                index=2;
                img.setImageResource(R.drawable.circle3);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        String result = "";
        double r = Double.parseDouble(radius.getText().toString());

        if (index==0){
//            求周长
            result="半径为："+r+"的周长为："+2*Math.PI*r;
        } else if (index==1){
//            求面积
            result="半径为："+r+"的面积为："+Math.PI*Math.pow(r,2);
        } else if (index==2){
//            求体积
            result="半径为："+r+"的体积为："+(4.0*Math.PI*Math.pow(r,3))/3;
        }

        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }
}