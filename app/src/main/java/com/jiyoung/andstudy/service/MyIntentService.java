package com.jiyoung.andstudy.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends IntentService {

    private static final String TAG = " MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //용량이 큰 파일 다운로드, 오디오 재생 등의 시간이 오래 걸리는 작업을 수행
        Log.i(TAG, "Intent Service started");
        //3초간 stop
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //onHandleIntent가 실행된 후, 바로 onDestroy가 호출되며 서비스가 종료됨
        Log.i(TAG, "Intent Service finish");
    }
}
