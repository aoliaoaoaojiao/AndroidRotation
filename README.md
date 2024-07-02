# 安卓旋转监听器

使用方法：

1.将项目构建

2.将构建产物推送到设备上，例如：

```
adb push app-debug.apk  /data/local/tmp/rotation.jar
```

3.执行，例如：

```
adb shell CLASSPATH=/data/local/tmp/rotation.jar app_process / com.aoliaoaojiao.AndroidRotaion.Run

#output examlpe
[rotation] INFO: Device: [vivo] vivo V2304A (Android 14)
[rotation] INFO: {"width":2400,"height":1080,"rotation":1}
[rotation] INFO: {"width":1080,"height":2400,"rotation":0}
```

4.退出

退出又两种方法：

- ctrl + c
- 命令行中输入 exit