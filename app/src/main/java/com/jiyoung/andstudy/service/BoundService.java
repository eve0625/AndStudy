package com.jiyoung.andstudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoundService extends Service {

    private final IBinder myBinder = new MyLocalBinder();

    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * 바운드 서비스의 인스턴스 참조를 반환하는 Binder의 서브클래스를 생성
     * 클라이언트에서 서비스의 참조를 얻을때 사용함
     */
    public class MyLocalBinder extends Binder {
        public BoundService getService() {
            return BoundService.this;
        }
    }
}
