01-Android快速入门

鼠标放置在正文左侧，点击“ **+** ”工具栏中的“ **高亮块** ”来插入高亮块。

科目：Android

日期：03/21

主题：01-Android快速入门

课前阅读： @ 插入相关文档

* ## 四个文件目录结构

1. ### 项目的组成结构

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTIyN2JmYmVmY2EwOGFhNjZlMTA5MDljZTY5MTRjZGVfWFc4WVgxUjJNbTlPbFhEM0tPRWkzaFUzdEN0bzRUYXJfVG9rZW46Ym94Y25SaVp6QlJLRWVTem15YkRkdEhrQm9kXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

* src（源码文件夹）

  * MainActivity.java：主界面类
* gen（自动生成的源码文件夹）

  * R.java: 对应res文件夹
  * drawable：图片
* layout：布局
* string：字符串
* res（资源文件夹）

  * drawable-xxx：图片文件夹，为了适配不同分辨率的手机
* layout：界面的布局文件，功能类似于HTML
* values：常量文件夹

  * strings.xml:包含固定的字符串，在布局中引用：@string/name引用
* AndroidManifest.xml（功能清单文件）

2. ### APK安装文件的组成结构

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=YzQ2YzllOGNlZjY3Y2ZhNWVkOTQ0ZDUxNThiZGY3YzlfVG5yaEZFT0x5Q1I2cXIxcEJuWHFnTFYxVU53ZU1HRHlfVG9rZW46Ym94Y25NQ3pMQXc0ZEdINHJPTnc5VFc4bW5kXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

3. ### APK安装文件的组成结构

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2E0NzRiZTcxMzVlMDQ1ZDkyMTA0MjExZjZiNjQzZmJfYjFySHRtUWJGcDhwRWxyNlVJMTU0aVFqV2lMZ0s4dDRfVG9rZW46Ym94Y25WNDVVdVR6RnBtdmRXbFZwaVJ0bk5oXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

4. ### Android系统文件目录结构

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDIxOTAyMDNhZTg3ODk1ZjRhMTZlMWI0YzAwOGE0NmJfUUszTkV6eUtDV3h6VWJxSklOR0VpOTJEUHdBdXNabGxfVG9rZW46Ym94Y25hREtaWXpsZVRxMUR5RnRNd29tYXVnXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

5. SDK的文件目录结构

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=MjYwZjc0ZmQzZTE4NzdlNzIzZTdlYzM5ODFhMDVkZmNfNUFCZEk5Ykt0TXh1NWY2d0ZHcHBtb2xEUmdYTklNYUhfVG9rZW46Ym94Y25jU3VucklHVlREeG1wMUxRQ3NpTHpnXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

* ## 日志工具类 LOG

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=OTY2NWI4YTEzMDA0ZDQ1YzVmMDkxODU1ZjBlMzIzNThfWnVoRURSQnNCb0Nlcms1SVk5Mk9SN1RVN0JiZnRyZnNfVG9rZW46Ym94Y25CNXRDbm1QZFJJQ0VjNVBMT3UzWFZkXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=N2U3N2RlNmNmN2M4MjNkODcyNjg0ZjM1NGU3MGRkMjBfdFUwRzZXRm5JWTNVRXEzQjFPUzNiZmNRNDdIUUVuUW5fVG9rZW46Ym94Y242NE9DQlpTNFNWVGNjQTNtTmxaUGFkXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

* ## 尺寸单位的比较

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=OGVmOTA4YWJmODQ3ZjMwOTc1OWI2OGUyNTk3ZjU3MjdfMDZWc3VWVVJMM0RScjE4RkdBV0M0R3BiN3gxdWFaQjBfVG9rZW46Ym94Y25DN091V2I5MEFjcUczOE1VUXAyMmhjXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

*px:  pixels(像素) 1px长度等于对应屏幕的一个像素点的大小*

** dp/dip：  device-independent pixels（设备无关像素）*

** sp： scaled pixels（可伸缩像素），与dp类似，但是可以根据用户的字体大小首选项进行缩放*

---

**  以dp像素为单位：差手机和好手机上显示的大小是不变的*

**  以px为单位：在差手机上显示变大，但在好手机上显示变小*

**  在布局文件中，除了字体大小，其他的都是以 【dp】 做单位，字体大小的单位用 【sp】*

---

**  在 布局文件视图的宽高尽量使用match_parent / wrap_content*

**  如果必须指定特定的值，使用dp/dip做单位*

**  文本大小使用sp做单位*

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=MzQwOTIzMDU1NjViYTRiNGM1N2RlMTczYjZiZGU5Y2JfWk9kQ0lGN1BkYmpIOXh3ZUxzNHV1QTd1cEg0YkpQWE9fVG9rZW46Ym94Y25oeDBWMUg2TDRodkFRdDZEMzNZQ0doXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

* ## 应用练习：

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=NTI5YWIwZGMyYTdjZGUzMThmMmM2NGQ5ZTdmYWZkYzJfYUhSaTNIUlNudUhFRVJjdWZPZFJWTWlPQUFrckNGTzBfVG9rZW46Ym94Y25wZW91eEttVkJGajR4THQ5WGNqZzhnXzE2NDgwMjE0OTE6MTY0ODAyNTA5MV9WNA)

1. 定义界面布局
2. 实现Activity
   1. 在onCreate（）中加载布局
   2. 根据id查询所有需要操作的视图对象，并保存为成员变量
   3. 给视图对象设置监听对象，并保存为成员变量
   4. 在监听器的回调方法中实现响应逻辑

#### strings.xml

```HTML
<resources>
    <string name="app_name">app3_quickStart</string>
    <string name="download">下载</string>
</resources>
```

#### activity_main.xml

```XML

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

    <Button
            android:text="@string/download"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/btn_main_download"
            tools:layout_editor_absoluteY="341dp"
            tools:layout_editor_absoluteX="158dp"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

#### MainActivity.java

```Java
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
```

```Java
package com.shf.app3_quickstart;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button btn_main_download;

//    自动调用的方法，在其中加载布局显示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        加载布局，并生成对应的视图图像
        setContentView(R.layout.activity_main);

//        1.得到Button对象
        btn_main_download = findViewById(R.id.btn_main_download);

//        2.给Button设置点击的监听
        btn_main_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// 在回调方法中：当点击设置监听的button时调用
//            1）.提示开始下载的文本小提示
//                如何得到外部类的对象： 外部类名.this
                Toast
                        .makeText(MainActivity.this,"开始下载...",Toast.LENGTH_LONG )
                        .show();
//            2）.更新button显示的文本
                btn_main_download.setText("正在下载中....");
            }
        });

    }
}

/**
 * px:  pixels(像素) 1px长度等于对应屏幕的一个像素点的大小
 * dp/dip：  device-independent pixels（设备无关像素）
 * sp： scaled pixels（可伸缩像素），与dp类似，但是可以根据用户的字体大小首选项进行缩放
 *
 *  以dp像素为单位：差手机和好手机上显示的大小是不变的
 *  以px为单位：在差手机上显示变大，但在好手机上显示变小
 *  在布局文件中，除了字体大小，其他的都是以 【dp】 做单位，字体大小的单位用 【sp】
 *
 *  在 布局文件视图的宽高尽量使用match_parent / wrap_content
 *  如果必须指定特定的值，使用dp/dip做单位
 *  文本大小使用sp做单位
 *
 *
 */
```
