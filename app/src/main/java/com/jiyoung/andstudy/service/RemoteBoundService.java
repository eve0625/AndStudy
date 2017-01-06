package com.jiyoung.andstudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class RemoteBoundService extends Service {

    final Messenger myMessenger = new Messenger(new IncomingHandler());

    public RemoteBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myMessenger.getBinder();
    }

    class IncomingHandler extends Handler {
        /**
         * 클라이언트로부터 메세지가 수진될때 호출됨
         * @param msg 클라이언트에서 전달된 데이터가 포함되어 있음
         */
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            String dataString = data.getString("MyString", "Empty!!");

            Toast.makeText(getApplicationContext(), dataString, Toast.LENGTH_SHORT).show();
        }
    }
}
