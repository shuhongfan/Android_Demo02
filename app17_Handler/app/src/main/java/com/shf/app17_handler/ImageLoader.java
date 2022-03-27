package com.shf.app17_handler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**'
 * 加载图片并显示的类
 *
 * * 如何使用三级缓存？--------如何根据图片的url动态显示图片？
 *  * "imagepath":"http://192.168.56.1:8080//images/f10.jpg"
 *  * 1. 根据url从一级缓存中取对应的bitmap对象
 *  *      如果有，显示
 *  *      如果没有，进入2
 *  * 2. 从二级缓存中查询：得到文件名并在sd卡的缓存文件目录下找到对应的图片得到bitMap对象
 *  *     如果有： 显示，缓存到一级缓存中
 *  *     如果没有，进入3
 *  * 3. 显示代表提示正在加载的图片，启动分线程联网请求得到Bitmap对象
 *  *      如果没有：显示提示错误的图片
 *  *      如果有：
 *  *          显示
 *  *          缓存到一级缓存
 *  *          缓存到二级缓存
 */
public class ImageLoader {

    private Context context;
    private int loadingImageRes;
    private int errorImageRes;

    public ImageLoader(Context context, int loadingImageRes, int errorImageRes) {
        this.context = context;
        this.loadingImageRes = loadingImageRes;
        this.errorImageRes = errorImageRes;
    }

    //    用于缓存bitmao的容器对象
    private Map<String, Bitmap> cacheMap = new HashMap<String,Bitmap>();

    public void loadImage(String imagePath, ImageView imageView) {
//        将图片URL保存到视图上
        imageView.setTag(imagePath);

//    * 1. 根据url从一级缓存中取对应的bitmap对象
//          如果有，显示
//          如果没有，进入2
        Bitmap bitmap = getFromFirstCache(imagePath);
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        /**
         *  *  * 2. 从二级缓存中查询：得到文件名并在sd卡的缓存文件目录下找到对应的图片得到bitMap对象
         *  *  *     如果有： 显示，缓存到一级缓存中
         *  *  *     如果没有，进入3
         */
        bitmap = getFromSecondCache(imagePath);

        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
            cacheMap.put(imagePath,bitmap);
            return;
        }

        /**
         *  *  * 3. 显示代表提示正在加载的图片，启动分线程联网请求得到Bitmap对象
         *  *  *      如果没有：显示提示错误的图片
         *  *  *      如果有：
         *  *  *          显示
         *  *  *          缓存到一级缓存
         *  *  *          缓存到二级缓存
         */
        loadBitmapFromThirdCache(imagePath,imageView);
    }

    /**
     * 根据图片url从三级缓存中取出对应的bitmap对象并显示
     * @param imagePath
     * @param imageView
     */
    private void loadBitmapFromThirdCache(String imagePath, ImageView imageView) {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected void onPreExecute() {
                imageView.setImageResource(loadingImageRes);
            }

//            联网请求得到bitmap对象
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Bitmap doInBackground(Void... voids) {
                Bitmap bitmap = null;
                try {
//                    在准备请求服务器图片之前，判断是否需要加载
                    String newImagePath = (String) imageView.getTag();
//                    判断是否需要加载
                    if (newImagePath!=imagePath){
                        // 视图已经被复用,不再重新请求
                        return null;
                    }
//                得到连接
                    URL url = new URL(imagePath);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                设置
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
//                连接
                    connection.connect();
//                发请求读取返回进度数据并封装为bitmap
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream is = connection.getInputStream(); // 图片文件流
//                        将is封装为bitmap
                        bitmap = BitmapFactory.decodeStream(is);
                        is.close();

                        if (bitmap!=null){
//                            缓存到一级缓存（分线程）
                            cacheMap.put(imagePath,bitmap);
//                            缓存到二级缓存（分线程）
                            String files = context.getExternalFilesDir(null).getAbsolutePath();
                            String fileName = imagePath.substring(imagePath.lastIndexOf("/")+1);
                            String filePath = files+"/"+fileName;
                            bitmap.compress(
                                    Bitmap.CompressFormat.JPEG,
                                    100,
                                    new FileOutputStream(filePath));
                        }
                    }
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) { // 从联网请求图片到得到图片对象需要一定时间，视图可能被复用了，不需要显示
//                    在准备请求服务器图片之前，判断是否需要加载
                String newImagePath = (String) imageView.getTag();
//                    判断是否需要加载
                if (newImagePath!=imagePath){
                    // 视图已经被复用,不再重新请求
                    return;
                }
//                如果没有，显示提示错误的图片
                if (bitmap==null){
                    imageView.setImageResource(errorImageRes);
                } else {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }.execute();
    }

    /**
     * 根据图片URL从二级缓存中取对应的bitmap对象
     * @param imagePath
     * @return
     */
    private Bitmap getFromSecondCache(String imagePath) {
        String files = context.getExternalFilesDir(null).getAbsolutePath();
        String fileName = imagePath.substring(imagePath.lastIndexOf("/")+1);
        String filePath = files+"/"+fileName;
        return BitmapFactory.decodeFile(fileName);
    }

    /**
     * 根据图片url从一级缓存中取出对应的bitmap对象
     * @param imagePath
     * @return
     */
    private Bitmap getFromFirstCache(String imagePath) {
        return cacheMap.get(imagePath);
    }
}
