package com.shf.app17_handler;

import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 图片的三级缓存
 * 一级缓存：内存缓存，缓存是Bitmap对象，用 Map<String,Bitmap>结构保存，key是url
 * 二级缓存：本地（SD卡）缓存，缓存的是图片文件， /storage/sdcard/Android/data/packageName/files/xxx.jpg
 * 三级缓存：远程服务器缓存，缓存是图片文件，远程服务器上的应用中
 *
 * 如何使用三级缓存？--------如何根据图片的url动态显示图片？
 * "imagepath":"http://192.168.56.1:8080//images/f10.jpg"
 * 1. 根据url从一级缓存中取对应的bitmap对象
 *      如果有，显示
 *      如果没有，进入2
 * 2. 从二级缓存中查询：得到文件名并在sd卡的缓存文件目录下找到对应的图片得到bitMap对象
 *     如果有： 显示，缓存到一级缓存中
 *     如果没有，进入3
 * 3. 显示代表提示正在加载的图片，启动分线程联网请求得到Bitmap对象
 *      如果没有：显示提示错误的图片
 *      如果有：
 *          显示
 *          缓存到一级缓存
 *          缓存到二级缓存
 *
 * 在ListView使用图片三级缓存会存在图片闪动的bug
 * 1. 原因
 *      converView被复用了
 * 2.解决
 *      每个getView（）都将图片进度URL保存到ImageView上：imageView.setTag（imagePath）
 *      在分线程准备请求服务器加载图片之前，比较准备加载图片的URL与ImageView中保存的最新图片的URL是同一个
 *          如果不是同一个，当前加载图片的任务不应该再执行
 *          如果相同，继续执行加载远程图片
 *      在主线程准备显示图片之前，比较加载图片的url与imageView中保存的最新图片的url是同一个
 *          如果不是同一个，不需要显示此图片
 */
public class MainActivity extends AppCompatActivity {
    private static final int WHAT_SUCCESS = 1;
    private static final int WHAT_REQUEST_ERROR = 2;
    private ListView lv_main;
    private LinearLayout lv_main_loading;
    private List<ShopInfo> data;
    private ShopInfoAdapter adapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case WHAT_SUCCESS:
                    lv_main_loading.setVisibility(View.GONE);
//                    显示列表
                    lv_main.setAdapter(adapter);
                    break;
                case WHAT_REQUEST_ERROR:
                    lv_main_loading.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "加载数据失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = findViewById(R.id.lv_main);
        lv_main_loading = findViewById(R.id.lv_main_loading);
        adapter = new ShopInfoAdapter();

//        1.主线程，显示提示视图
        lv_main_loading.setVisibility(View.VISIBLE);
//        2.分线程，联网请求

//        启动分线程请求服务器动态展示数据并显示
        new Thread(){
            @Override
            public void run() {
                try {
//                互联网请求得到jsonString
                    String jsonString = requestJson();
//                    解析成List<ShopInfo>
                    data = new Gson().fromJson(jsonString,
                            new TypeToken<List<ShopInfo>>() {
                            }.getType());
                    //        3.主线程，更新界面
                    handler.sendEmptyMessage(WHAT_SUCCESS); // 发送成功的消息
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(WHAT_REQUEST_ERROR);  // 发送失败的消息
                }
            }
        }.start();
    }

    /**
     * 联网请求得到JsonString
     * @return
     */
    private String requestJson() throws Exception {
        String result = null;
        String path = "http://192.168.56.1:8080/ShopListServlet";
        //1. 得到连接对象
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //2. 设置
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        //连接
        connection.connect();
        //发请求并读取服务器返回的数据
        int responseCode = connection.getResponseCode();
        if(responseCode==200) {
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            connection.disconnect();

            result = baos.toString();
        } else {
            //也可以抛出运行时异常
        }
        return result;
    }

    class ShopInfoAdapter extends BaseAdapter{
        private ImageLoader imageLoader;

        public ShopInfoAdapter() {
            this.imageLoader = new ImageLoader(
                    MainActivity.this,
                    R.drawable.loading,
                    R.drawable.error);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view = View.inflate(MainActivity.this,R.layout.item_main, null);
            }
//            得到当前行的数据对象
            ShopInfo shopInfo = data.get(i);
//            得到当前行的子View
            TextView tv_item_name = view.findViewById(R.id.tv_item_name);
            TextView tv_item_price = view.findViewById(R.id.tv_item_price);
            ImageView iv_item_icon = view.findViewById(R.id.iv_item_icon);
//            设置数据
            tv_item_name.setText(shopInfo.getName());
            tv_item_price.setText(shopInfo.getPrice()+"元");
            String imagePath = shopInfo.getImagePath();
//            根据图片路径动态请求服务器加载图片并显示
            imageLoader.loadImage(imagePath,iv_item_icon);
            return view;
        }
    }
}