02_四大应用组件之Activity

> 🖊 人不光是靠他生来就拥有一切，而是靠他从学习中所得到的一切来造就自己。 —— 歌德

## 相关课程资料：

> 鼠标放置在正文左侧，点击“ **+** ”工具栏中的“ **高亮块** ”来插入高亮块。

科目：Android

日期：03/21

主题：02_四大应用组件之Activity

课前阅读： @ 插入相关文档

## Activity的理解

* Activity，直译为活动，它是Android定义的四大应用组件之一，也是最重要的，也是用的最多的
* 作用：Activity用来提供一个能够让用户操作并与之交互的界面
* 组件的特点：

  * 它的类必须实现特定接口或继承特定类
* 需要在配置文件中配置其全类名
* 它的对象不实际通过new来创建的，而是系统自动创建的
* 它的对象具有一定的声明周期，它的类中有对应的声明周期回调方法
* 哪些地方用到了反射？

  * 配置文本中配置全类名
* 布局文件定义标签
* 显示意图：Intent（Context context，Class c）

## Intent的理解

* 意图：信使（Activity，Service，BroadcastReceiver三个组件间通信的信使）
* 分类：

  * 【显示】：操作 【当前】 应用自己的组件
* 【隐式】：操作 【其他】 应用自己的组件

## Intent的使用

* 创建：

  * 显示：Intent（Content context，Class activityClass）
* 隐式：Intent（String action） // 与Activity与`<intent-filter>`的action匹配
* 携带数据

  * 额外：putExtra（String key，Xxx value）内部使用map容器保存
* 有特定前缀：setData（Uri data） // tel:12123,smsto:123123
* 读取数据

  * 额外：getXxxExtra（String key）
* 有特定前缀： Uri getData（）

## Activity的使用

* 定义
  * 定义一个类extends Activity，并重写声明周期方法
  * 在功能清单文件中使用`<activity>`注册
* 启动
  * 一般： startActivity(Intent intent)
  * 带回调启动：
    * startActivityForResult（Intent intent，int requestCode）
    * 重写：onActivityResult（int requestCode，int resultCode,Intent data）
* 结束
  * 一般：finish（）
  * 带结果的：setResult（int resultCode，Intent data）

## Activity界面的四种状态

* 运行状态：可见也可操作
* 暂停状态：可见但不可操作
* 停止状态：不可见，但对象存在
* 死亡状态：对象不存在
* ## Activity生命周期与状态

1. 界面从 死亡 ---》运行
   1. 创建对象 ---> onCreate()  ---> onStart()   --->onResume()
2. 界面从  运行 ---》 死亡
   1. onPause() ---> onStop()  --->  onDestory()
3. 界面从  运行   ---》 停止
   1. onPause() ---> onStop()
4. 界面从 停止  ---》运行
   1. onRestart()  ---> onStart()  --->  onResume()
5. 界面从  运行  ---》暂停
   1. onPause()
6. 界面从  暂停   ---》 运行
   1. onResume()

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=NDJlYTY2Yjg4ZDdkMWVkNTc3ODdlYWUxMzdjMGI5MzNfOGlWWXBncFpQVVpxRE55Z0Z1ZUVuMXU2WXdLN3Nva2NfVG9rZW46Ym94Y252ZnIxM2w4MnFzdmdFQzdPTmR4dklmXzE2NDgwMjE3MzA6MTY0ODAyNTMzMF9WNA)

* onCreate（）: 加载布局和初始化的工作
* onResume（）: 只有经历次方法，才进入运行状态
* onDestroy（）: 在对象死亡之前，做一些收尾或清理的工作

## TaskStack和lauchMode

1. TaskStack **任务堆栈**
   1. 在Android中，系统用Task Stack（Back Stack）结构来存储启动的Activity对象
   2. 一个应用启动，系统就会为其创建一个对应的Task Stack来存储并管理该应用的Activity对象
   3. 只有最上面的任务栈的栈顶的Activity才能显示在窗口中
2. lauchMode：启动模式
   1. standard：标准模式，每次调用startActivity()方法就会产生一个新的实例
   2. singleTop：如果已经有一个实例位于Activity栈的顶部时，就不产生新的实例；如果不位于栈顶，就会产生一个新的实例。
   3. singleTask：只有一个实例，默认在当前Task中
   4. singleInstance：只有一个实例，创建时会新建一个栈，且此栈中不能有其它的对象

### 测试程序

1. 界面布局
2. 实现Activity的功能
   1. 定义所需要操作的视图对象并初始化
   2. 给视图设置监听
   3. 在回调方法中实现逻辑
3. 实现一般启动
   1. 定义好界面二
      1. 定义Activity类
      2. 配置
      3. 重写onCreate(),并加载布局
   2. 启动界面二
      1. 创建Intent对象（显示）
      2. 通过Intent携带额外数据
      3. 显示到EditText
4. 实现一般返回
   1. 在显示Second界面是，Main界面起始在，知识被盖住了
   2. 关闭当前界面

# 应用练习：打电话和发短信

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=NWI1MWNhMjU4ZTk2OGNmNjY3ZGQ3MjE1ZmFkY2ZhYjZfeDBrcVU4aXBXSzhzV1ZWZGVZZDlLa2h0Z2V4emdVTGtfVG9rZW46Ym94Y25BQmpRMHp2WWhoUUliUVcwQzR5YTRjXzE2NDgwMjE3MzA6MTY0ODAyNTMzMF9WNA)

1. 界面布局
   1. 分析：垂直的LinearLayout ---》 水平的LinearLayout
   2. 编码
2. 在Activity中初始化需要操作的视图对象
3. 给button设置点击监听
4. 给button设置长按监听
5. 点击打电话进入拨号界面
   1. 创建一个Intent(隐式)
   2. 携带数据
   3. startActivity(intent)
6. 长按打电话进入打电话的界面
   1. 创建一个Intent（隐式
   2. 携带数据
   3. startActivity（intent）
7. 点击发短信进入短信编辑界面
   1. 创建一个Intent（隐式）
   2. 携带数据（号码 / 内容）
   3. startActivity（intent）
8. 长按发短信直接发短信
   1. 得到SMSManager的对象
   2. 发送文本信息（短信）

## 总结

1. 实现一个简单功能的应用的步骤：
   1. 外观：分析界面的组成，定义布局文件
   2. 行为：编写Activity的实现
      1. 在OnCreate（）中加载布局文件：setContentView（layoutId）R.layout.xxx
      2. 调用findViewById得到需要操作的所有视图对象并保存成为成员变量
      3. 给视图对象设置就监听器（点击/长按），在回调方法实现响应逻辑
2. 使用隐式意图启动系统应用的界面
   1. 如何找到对应的Action字符串？
      1. 添加ActivityManager的LOG日志，利用系统应用源码找到对应的Activity的配置
   2. 权限：
      1. 当调用一些系统比较重要的功能的时需要声明

## 常见的异常

1. NollPointException
   1. 原因：调用对象的方法 / 属性，但对象为null
2. ClassCastException
   1. 原因：类型转换异常
3. ActivityNotFoundException
   1. 原因：没有注册Activity
   2. 注册不正确
4. 基本常见异常的一般分析步骤：
   1. 在logcat中从下往下找，尽量找到causeBy（会显示哪种异常导致的）
   2. 找到出异常的类及行号，点击进入对应的

02_四大应用组件之Activity

> 🖊 人不光是靠他生来就拥有一切，而是靠他从学习中所得到的一切来造就自己。 —— 歌德

## 相关课程资料：

> 鼠标放置在正文左侧，点击“ **+** ”工具栏中的“ **高亮块** ”来插入高亮块。

科目：Android

日期：03/21

主题：02_四大应用组件之Activity

课前阅读： @ 插入相关文档

## Activity的理解

* Activity，直译为活动，它是Android定义的四大应用组件之一，也是最重要的，也是用的最多的
* 作用：Activity用来提供一个能够让用户操作并与之交互的界面
* 组件的特点：

  * 它的类必须实现特定接口或继承特定类
* 需要在配置文件中配置其全类名
* 它的对象不实际通过new来创建的，而是系统自动创建的
* 它的对象具有一定的声明周期，它的类中有对应的声明周期回调方法
* 哪些地方用到了反射？

  * 配置文本中配置全类名
* 布局文件定义标签
* 显示意图：Intent（Context context，Class c）

## Intent的理解

* 意图：信使（Activity，Service，BroadcastReceiver三个组件间通信的信使）
* 分类：

  * 【显示】：操作 【当前】 应用自己的组件
* 【隐式】：操作 【其他】 应用自己的组件

## Intent的使用

* 创建：

  * 显示：Intent（Content context，Class activityClass）
* 隐式：Intent（String action） // 与Activity与`<intent-filter>`的action匹配
* 携带数据

  * 额外：putExtra（String key，Xxx value）内部使用map容器保存
* 有特定前缀：setData（Uri data） // tel:12123,smsto:123123
* 读取数据

  * 额外：getXxxExtra（String key）
* 有特定前缀： Uri getData（）

## Activity的使用

* 定义
  * 定义一个类extends Activity，并重写声明周期方法
  * 在功能清单文件中使用`<activity>`注册
* 启动
  * 一般： startActivity(Intent intent)
  * 带回调启动：
    * startActivityForResult（Intent intent，int requestCode）
    * 重写：onActivityResult（int requestCode，int resultCode,Intent data）
* 结束
  * 一般：finish（）
  * 带结果的：setResult（int resultCode，Intent data）

## Activity界面的四种状态

* 运行状态：可见也可操作
* 暂停状态：可见但不可操作
* 停止状态：不可见，但对象存在
* 死亡状态：对象不存在
* ## Activity生命周期与状态

1. 界面从 死亡 ---》运行
   1. 创建对象 ---> onCreate()  ---> onStart()   --->onResume()
2. 界面从  运行 ---》 死亡
   1. onPause() ---> onStop()  --->  onDestory()
3. 界面从  运行   ---》 停止
   1. onPause() ---> onStop()
4. 界面从 停止  ---》运行
   1. onRestart()  ---> onStart()  --->  onResume()
5. 界面从  运行  ---》暂停
   1. onPause()
6. 界面从  暂停   ---》 运行
   1. onResume()

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=NjAwMzM4ODZiNGY5NDRjYzhjNzYzYTg5MzVjNzllNzlfSFFHM3dkbExxUVpOdjZWR0tWcWRYeThYN0lOWjVLN25fVG9rZW46Ym94Y252ZnIxM2w4MnFzdmdFQzdPTmR4dklmXzE2NDgwMjE2OTc6MTY0ODAyNTI5N19WNA)

* onCreate（）: 加载布局和初始化的工作
* onResume（）: 只有经历次方法，才进入运行状态
* onDestroy（）: 在对象死亡之前，做一些收尾或清理的工作

## TaskStack和lauchMode

1. TaskStack **任务堆栈**
   1. 在Android中，系统用Task Stack（Back Stack）结构来存储启动的Activity对象
   2. 一个应用启动，系统就会为其创建一个对应的Task Stack来存储并管理该应用的Activity对象
   3. 只有最上面的任务栈的栈顶的Activity才能显示在窗口中
2. lauchMode：启动模式
   1. standard：标准模式，每次调用startActivity()方法就会产生一个新的实例
   2. singleTop：如果已经有一个实例位于Activity栈的顶部时，就不产生新的实例；如果不位于栈顶，就会产生一个新的实例。
   3. singleTask：只有一个实例，默认在当前Task中
   4. singleInstance：只有一个实例，创建时会新建一个栈，且此栈中不能有其它的对象

### 测试程序

1. 界面布局
2. 实现Activity的功能
   1. 定义所需要操作的视图对象并初始化
   2. 给视图设置监听
   3. 在回调方法中实现逻辑
3. 实现一般启动
   1. 定义好界面二
      1. 定义Activity类
      2. 配置
      3. 重写onCreate(),并加载布局
   2. 启动界面二
      1. 创建Intent对象（显示）
      2. 通过Intent携带额外数据
      3. 显示到EditText
4. 实现一般返回
   1. 在显示Second界面是，Main界面起始在，知识被盖住了
   2. 关闭当前界面

# 应用练习：打电话和发短信

![](https://rypnbkno8m.feishu.cn/space/api/box/stream/download/asynccode/?code=MWVjMzRmOGU0MDYyNjMyMDMxYWMzN2RiMGMwNDU5YzRfemo4b1ptUU9Xdkl2RVl5dHk5WU9JbWZhaG1IUHFSbkRfVG9rZW46Ym94Y25BQmpRMHp2WWhoUUliUVcwQzR5YTRjXzE2NDgwMjE2OTc6MTY0ODAyNTI5N19WNA)

1. 界面布局
   1. 分析：垂直的LinearLayout ---》 水平的LinearLayout
   2. 编码
2. 在Activity中初始化需要操作的视图对象
3. 给button设置点击监听
4. 给button设置长按监听
5. 点击打电话进入拨号界面
   1. 创建一个Intent(隐式)
   2. 携带数据
   3. startActivity(intent)
6. 长按打电话进入打电话的界面
   1. 创建一个Intent（隐式
   2. 携带数据
   3. startActivity（intent）
7. 点击发短信进入短信编辑界面
   1. 创建一个Intent（隐式）
   2. 携带数据（号码 / 内容）
   3. startActivity（intent）
8. 长按发短信直接发短信
   1. 得到SMSManager的对象
   2. 发送文本信息（短信）

## 总结

1. 实现一个简单功能的应用的步骤：
   1. 外观：分析界面的组成，定义布局文件
   2. 行为：编写Activity的实现
      1. 在OnCreate（）中加载布局文件：setContentView（layoutId）R.layout.xxx
      2. 调用findViewById得到需要操作的所有视图对象并保存成为成员变量
      3. 给视图对象设置就监听器（点击/长按），在回调方法实现响应逻辑
2. 使用隐式意图启动系统应用的界面
   1. 如何找到对应的Action字符串？
      1. 添加ActivityManager的LOG日志，利用系统应用源码找到对应的Activity的配置
   2. 权限：
      1. 当调用一些系统比较重要的功能的时需要声明

## 常见的异常

1. NollPointException
   1. 原因：调用对象的方法 / 属性，但对象为null
2. ClassCastException
   1. 原因：类型转换异常
3. ActivityNotFoundException
   1. 原因：没有注册Activity
   2. 注册不正确
4. 基本常见异常的一般分析步骤：
   1. 在logcat中从下往下找，尽量找到causeBy（会显示哪种异常导致的）
   2. 找到出异常的类及行号，点击进入对应的

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
