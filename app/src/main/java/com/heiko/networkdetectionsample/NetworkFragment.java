package com.heiko.networkdetectionsample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.heiko.network.detection.task.TraceTask;

public class NetworkFragment extends Fragment {
    Button traceButton;
    TextView resultTextView;
    EditText urlEditText;

    static NetworkFragment networkFragment;

    public static NetworkFragment getInstance() {
        if (networkFragment == null) {
            networkFragment = new NetworkFragment();
        }
        return networkFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_network, container, false);
        traceButton = view.findViewById(R.id.bt_trace);
        resultTextView = view.findViewById(R.id.tv_result);
        urlEditText = view.findViewById(R.id.et_url);

        traceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TraceTask pingTask = new TraceTask(getActivity(), urlEditText.getText() + "", resultTextView);
                pingTask.setAppVersion(DeviceUtils.getVersion(getActivity()));
                pingTask.setAppName("NetworkDetection");
                pingTask.setAppCode("01");
                pingTask.setDeviceId(DeviceUtils.getAndroidID(getActivity()));
                pingTask.doTask();
            }
        });

        return view;
    }
}
