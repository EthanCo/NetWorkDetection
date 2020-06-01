### NetWorkDetection

Android 网络诊断、网络检测库  
基于[AndroidHttpCapture](https://github.com/JZ-Darkal/AndroidHttpCapture)改造，剔除了不需要的功能，更精简。  
效果如图所示:  

![](./network_detaction.jpg)

### 添加依赖
#### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### Step 2. Add the dependency

```java
dependencies {
    implementation 'com.github.EthanCo:NetWorkDetection:1.0.2'
}
```

### 进行使用
```java
traceTask = new TraceTask(MainActivity.this, "www.baidu.com", new TaskCallBack() {
    @Override
    public void onUpdated(String log) {
        //当诊断状态更新
    }

    @Override
    public void onFinish(String log) {
        //当诊断结束
    }

    @Override
    public void onFailed(Exception e) {
        //当诊断失败
    }
});
traceTask.doTask(); //进行诊断
```

#### 可选方法
```java
traceTask.setAppName("NetworkDetection"); //设置应用名称
traceTask.setAppCode("01"); //设置AppCode
traceTask.setDeviceId(deviceId); //设置设备ID
traceTask.setAlwaysPing(false); //是否永远进行Ping，如果是false，则根据当前网络环境判断是否要Ping
```

### 添加混淆

```java
-keep class com.netease.LDNetDiagnoService.**{*;}
```

感谢 [AndroidHttpCapture](https://github.com/JZ-Darkal/AndroidHttpCapture) | [LDNetDiagnoService](https://github.com/Lede-Inc/LDNetDiagnoService_Android)