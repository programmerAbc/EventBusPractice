package com.practice.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.util.Log;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.postBtn)
    Button postBtn;
    int eventCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void eventHandler1(String event) {
        Log.e(TAG, "eventHandler1: " + event + " on Thread:" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void eventHandler2(String event) {
        Log.e(TAG, "eventHandler2: " + event + " on Thread:" + Thread.currentThread().getName());
    }

    @OnClick(R.id.postBtn)
    public void onClick() {
        eventCount++;
        Log.e(TAG, "onClick: " + "postEvent event"+eventCount);
        EventBus.getDefault().post("event"+eventCount);
    }
}
