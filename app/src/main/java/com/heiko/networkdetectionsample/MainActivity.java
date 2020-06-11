package com.heiko.networkdetectionsample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.heiko.network.detection.task.TaskCallBack;
import com.heiko.network.detection.task.TraceTask;

public class MainActivity extends AppCompatActivity implements TaskCallBack {
    Button traceButton;
    TextView resultTextView;
    EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        traceButton = findViewById(R.id.bt_trace);
        resultTextView = findViewById(R.id.tv_result);
        urlEditText = findViewById(R.id.et_url);

        traceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText("");
                TraceTask traceTask = new TraceTask(getApplication(),
                        urlEditText.getText() + "", MainActivity.this);
                traceTask.setAppVersion(DeviceUtils.getVersion(MainActivity.this));
                traceTask.setAppName("NetworkDetection");
                traceTask.setAppCode("01");
                traceTask.setDeviceId(DeviceUtils.getAndroidID(MainActivity.this));
                traceTask.setAlwaysPing(true); //是否永远进行Ping，如果是false，则根据当前网络环境判断是否要Ping
                traceTask.doTask();
            }
        });
    }

    @Override
    public void onUpdated(String log) {
        resultTextView.append(log);
    }

    @Override
    public void onFinish(String log) {
        new AlertDialog.Builder(this)
                .setTitle("诊断结束")
                .setMessage("请将诊断信息复制或长截屏给客服，谢谢")
                .setCancelable(false)
                .setNegativeButton("复制诊断信息到剪切板", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String info = resultTextView.getText().toString();
                        ClipboardUtils.copyToClipboard(MainActivity.this, info);
                        Toast.makeText(getApplicationContext(), "复制诊断信息成功!", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    @Override
    public void onFailed(Exception e) {
        resultTextView.append("诊断失败:" + e.getMessage());
        Toast.makeText(this, "诊断失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
