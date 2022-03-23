package com.shf.app2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 主界面Activity类
 * 主界面：点击应用程序图标启动的界面
 * extends Activity
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 重写的方法
     * onCreate：在当前类（activity）对象创建的时候自动调用
     * 回调方法： 不是我们调用的，是系统在一定条件下自动调用的，基本都以on开头，onXXX
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        调用父类处理做一些默认初始化工作
        super.onCreate(savedInstanceState);

//        设置内容视图要显示的内容视图（界面/布局）
//        指定布局文件在R所对应的变量，加载布局文件最终显示到窗口中
        setContentView(R.layout.activity_main);
        /**
         * HelloAndroid
         *      --- src（源码文件夹）
         *          MainActivity.java：主界面类
         *
         *      --- gen（自动生成的源码文件夹）
         *          R.java: 对应res文件夹
         *              drawable：图片
         *              layout：布局
         *              string：字符串
         *      --- res（资源文件夹）
         *          drawable-xxx：图片文件夹
         *              为了适配不同分辨率的手机
         *          layout：界面的布局文件
         *              功能类似于HTML
         *          values：常量文件夹
         *              strings.xml:包含固定的字符串，在不居中引用：@string/name引用
         *
         */
    }
}