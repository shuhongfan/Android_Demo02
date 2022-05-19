package com.shf.app33;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    TextView tv_weather, tv_location, tv_temperature_min, tv_temperature_max;
    Button btn_WH,btn_SH,btn_BJ;
    String urlStr = "http://www.weather.com.cn/data/cityinfo/";
    String cityId = "101200401";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_weather=findViewById(R.id.tv_weather);
        tv_location=findViewById(R.id.tv_location);
        tv_temperature_min=findViewById(R.id.tv_temperature_min);
        tv_temperature_max=findViewById(R.id.tv_temperature_max);
        btn_WH=findViewById(R.id.WH);
        btn_SH=findViewById(R.id.SH);
        btn_BJ=findViewById(R.id.BJ);

       btn_WH.performClick();
    }

    private void getWeather(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                URL url = null;
                try {
                    url=new URL(urlStr+cityId+".html");
                    URLConnection urlConnection = url.openConnection();
                    InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = "";
                    StringBuilder contentBuf = new StringBuilder();
                    while ((line=bufferedReader.readLine())!=null){
                        contentBuf.append(line);
                    }
                    JSONObject jsonObject = new JSONObject(contentBuf.toString());
//                    System.out.println(jsonObject);
                    JSONObject weatherinfo = jsonObject.getJSONObject("weatherinfo");
//                    System.out.println(weatherinfo);
                    tv_weather.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                tv_weather.setText("天气状况："+weatherinfo.getString("weather"));
                                tv_location.setText("城市："+weatherinfo.getString("city"));
                                tv_temperature_min.setText("最低气温："+weatherinfo.getString("temp1"));
                                tv_temperature_max.setText("最高气温："+weatherinfo.getString("temp2"));
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void show(View view) {
        int id = view.getId();
        if (id==btn_WH.getId()){
            cityId = "101200101";
        } else if (id==btn_BJ.getId()){
            cityId = "101010100";
        } else if (id==btn_SH.getId()){
            cityId = "101020100";
        }
        getWeather();
    }
}