## NetWorkDetection

Android 网络诊断、网络检测库  
基于[AndroidHttpCapture](https://github.com/JZ-Darkal/AndroidHttpCapture)改造，剔除了不需要的功能，更精简。  
效果如下所示:  
```java
开始诊断...
诊断时间:2020-06-11 14:20:09
应用code: 01
应用名称: NetworkDetection
应用版本: 1.0
机器类型: OnePlus:OnePlus:GM1910
系统版本: 10
机器ID: 5a121bc3a9547810
运营商: 中国电信
ISOCountryCode: cn
MobileCountryCode: 460
MobileNetworkCode: 11

诊断域名 www.baidu.com...
当前是否联网: 已联网
当前联网类型: WIFI
本地IP: 10.1.150.157
本地网关: 10.1.150.1
本地DNS: 10.96.0.10,114.114.114.114
远端域名: www.baidu.com
DNS解析结果: 112.80.248.75,112.80.248.76 (25ms)

开始TCP连接测试...
Connect to host: 112.80.248.75...
1's time=17ms,  2's time=22ms,  3's time=15ms,  4's time=14ms,  average=17ms
Connect to host: 112.80.248.76...
1's time=16ms,  2's time=17ms,  3's time=17ms,  4's time=16ms,  average=16ms

开始ping...
ping...127.0.0.1
64bytes from 127.0.0.1: icmp_seq=#1 ttl=64 time=0.236ms
64bytes from 127.0.0.1: icmp_seq=#2 ttl=64 time=0.467ms
64bytes from 127.0.0.1: icmp_seq=#3 ttl=64 time=0.622ms
64bytes from 127.0.0.1: icmp_seq=#4 ttl=64 time=0.586ms
ping本机IP...10.1.150.157
64bytes from 10.1.150.157: icmp_seq=#1 ttl=64 time=0.397ms
64bytes from 10.1.150.157: icmp_seq=#2 ttl=64 time=0.430ms
64bytes from 10.1.150.157: icmp_seq=#3 ttl=64 time=0.543ms
64bytes from 10.1.150.157: icmp_seq=#4 ttl=64 time=0.626ms
ping本地网关...10.1.150.1
64bytes from 10.1.150.1: icmp_seq=#1 ttl=254 time=13.9ms
64bytes from 10.1.150.1: icmp_seq=#2 ttl=254 time=17.0ms
64bytes from 10.1.150.1: icmp_seq=#3 ttl=254 time=15.1ms
64bytes from 10.1.150.1: icmp_seq=#4 ttl=254 time=13.0ms
ping本地DNS1...10.96.0.10
ping: cannot resolve 10.96.0.10: Timeout
ping本地DNS2...114.114.114.114
64bytes from 114.114.114.114: icmp_seq=#1 ttl=90 time=23.1ms
64bytes from 114.114.114.114: icmp_seq=#2 ttl=87 time=34.6ms
64bytes from 114.114.114.114: icmp_seq=#3 ttl=75 time=33.9ms
64bytes from 114.114.114.114: icmp_seq=#4 ttl=63 time=34.1ms

开始traceroute...
 1?: [LOCALHOST]             pmtu 1500
 1:  10.1.150.1                         4.795ms 
 1:  10.1.150.1                        19.414ms 
 2:  **********
 3:  60.12.222.1                        21.194ms 
 4:  **********
 5:  **********
 6:  221.12.35.101                        25.570ms 
 7:  **********
 8:  221.6.1.254                        24.447ms 
 9:  58.240.96.30                        14.385ms 
10:  182.61.216.0                        15.139ms 
11:  **********
12:  **********
13:  **********

网络诊断结束
```

## Usage
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