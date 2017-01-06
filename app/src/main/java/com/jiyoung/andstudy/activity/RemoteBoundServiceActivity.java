package com.jiyoung.andstudy.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.service.RemoteBoundService;

public class RemoteBoundServiceActivity extends AppCompatActivity {

    Messenger myService;
    boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_bound_service);

        Intent intent = new Intent(getApplicationContext(), RemoteBoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

        ((Button) findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBound) {
                    Message msg = Message.obtain();

                    //서비스에 보낼 데이터
                    Bundle bundle = new Bundle();
                    bundle.putString("MyString", "Message Received");

                    msg.setData(bundle);

                    try {
                        myService.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myService = new Messenger(iBinder);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myService = null;
            isBound = false;
        }
    };
}
