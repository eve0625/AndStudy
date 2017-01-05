package com.jiyoung.andstudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "MyService";
    boolean isRunning;
    int mCount;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "my service called : onCreate", Toast.LENGTH_SHORT).show();

        isRunning = true;
        mCount = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning) {
                    Log.i(TAG, "my service called : " + mCount);
                    mCount++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        //Start 이후에 호출됨
        Toast.makeText(this, "my service called : onBind", Toast.LENGTH_SHORT).show();
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //서비스 시작시 호출됨
        Toast.makeText(this, "my service called : onStartCommand", Toast.LENGTH_SHORT).show();

        //Service 호출시 보낸 파라미터 꺼내기
        if (intent != null) {
            int count = intent.getIntExtra("count", 0);
            this.mCount = count;
            Log.i(TAG, String.format("my service called : count=%d", count));
        }
        //서비스가 메모리 부족등의 이유로 강제로 죽은 경우, 다시 띄우지 않음. (다시 띄우려면 START_STICKY)
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "my service called : onDestroy", Toast.LENGTH_SHORT).show();
        isRunning = false;
    }
}
