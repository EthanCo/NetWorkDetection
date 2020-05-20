package com.heiko.network.detection.task;

/**
 * Task 回调
 *
 * @author Heiko
 * @date 2020/5/20 0020
 */
public interface TaskCallBack {
    void onUpdated(String log);

    void onFinish(String log);

    void onFailed(Exception e);
}
