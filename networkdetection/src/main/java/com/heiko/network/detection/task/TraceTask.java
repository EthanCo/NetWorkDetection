package com.heiko.network.detection.task;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.netease.LDNetDiagnoService.LDNetDiagnoListener;
import com.netease.LDNetDiagnoService.LDNetDiagnoService;


/**
 * Created by xuzhou on 2016/8/1.
 */
public class TraceTask extends BaseTask implements LDNetDiagnoListener {
    String url;
    Application context;
    private String appCode;
    private String appName;
    private String deviceId;
    private String appVersion;
    private boolean alwaysPing;

    private Handler handler = new Handler(Looper.getMainLooper());

    public void setAlwaysPing(boolean alwaysPing) {
        this.alwaysPing = alwaysPing;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public TraceTask(Application context, String url, TaskCallBack callBack) {
        super(url, callBack);
        this.context = context;
        this.url = url;
    }

    @Override
    public Runnable getExecRunnable() {
        return execRunnable;
    }

    public Runnable execRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                LDNetDiagnoService _netDiagnoService = new LDNetDiagnoService(context,
                        appCode, appName, appVersion, "",
                        deviceId, url, "", "",
                        "", "", alwaysPing, TraceTask.this);
                // 设置是否使用JNIC 完成traceroute
                _netDiagnoService.setIfUseJNICTrace(true);
                _netDiagnoService.execute();
            } catch (final Exception e) {
                if (callBack != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(e);
                        }
                    });
                }
            }
        }
    };

    @Override
    public void OnNetDiagnoFinished(final String log) {
        if (callBack != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onFinish(log);
                }
            });
        }
    }

    @Override
    public void OnNetDiagnoUpdated(final String log) {
        if (callBack != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onUpdated(log);
                }
            });
        }
    }
}
