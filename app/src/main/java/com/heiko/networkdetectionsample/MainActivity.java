package com.heiko.networkdetectionsample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                TraceTask pingTask = new TraceTask(MainActivity.this,
                        urlEditText.getText() + "", MainActivity.this);
                pingTask.setAppVersion(DeviceUtils.getVersion(MainActivity.this));
                pingTask.setAppName("NetworkDetection");
                pingTask.setAppCode("01");
                pingTask.setDeviceId(DeviceUtils.getAndroidID(MainActivity.this));
                pingTask.doTask();
            }
        });
    }

    @Override
    public void onUpdated(String log) {
        resultTextView.append(log);
    }

    @Override
    public void onFinish(String log) {
        Toast.makeText(MainActivity.this, "诊断结束 !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(Exception e) {
        resultTextView.append(":诊断失败" + e.getMessage());
    }
}
