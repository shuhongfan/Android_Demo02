package com.shf.sy4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                显式调用
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

//                隐式调用
//                Intent intent1 = new Intent();
//                intent1.setAction("com.shf.second");
//                intent1.putExtra("datas",new Double[]{1.2,5.3,693.630});
////                startActivity(intent1);
//                startActivityForResult(intent,100);
            }
        });

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("activity3");
                intent.putExtra("datas",new double[]{1.2,5.3,6.9});
                startActivity(intent);
//                startActivityForResult(intent,100);
            }
        });


        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("activity4");
                intent.putExtra("datas",new double[]{1.2,5.3,6.9});
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        double result = data.getDoubleExtra("result",0.0);
        Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_SHORT).show();
    }
}
