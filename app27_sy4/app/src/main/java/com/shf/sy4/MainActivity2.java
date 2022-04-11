package com.shf.sy4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

//                Intent intent = getIntent();
//                double[] d = intent.getDoubleArrayExtra("datas");
//                double sum = 0.0;
//                for (double v : d) {
//                    sum+=v;
//                }
//                Toast.makeText(getApplicationContext(),""+sum,Toast.LENGTH_SHORT);
//
//                Intent intent1 = new Intent();
//                intent1.putExtra("result",sum);
//                setResult(200,intent1);
            }
        });
    }
}