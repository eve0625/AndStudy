package com.jiyoung.andstudy.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.service.BoundService;

public class BoundServiceActivity extends AppCompatActivity {

    BoundService myService;
    boolean isBound = false;

    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);

        //서비스 시작
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

        tvTime = (TextView) findViewById(R.id.tv_time);

        ((Button) findViewById(R.id.btn_show_time)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //서비스에서 현재 시간을 구해옴!!
                String currentTime = myService.getCurrentTime();
                tvTime.setText(currentTime);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("tv_time", tvTime.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tvTime.setText(savedInstanceState.getCharSequence("tv_time"));
    }

    private ServiceConnection myConnection = new ServiceConnection() {

        /**
         * 클라이언트가 서비스에 성공적으로 바인딩되면 호출됨
         * @param componentName
         * @param iBinder Service의 onBind() 메서드에서 반환하는 IBinder 제공됨
         */
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyLocalBinder binder = (BoundService.MyLocalBinder) iBinder;
            myService = binder.getService(); //서비스의 인스턴스 참조를 가져옴
            isBound = true;
        }

        /**
         * 서비스와 연결이 끝나면 호출
         * @param componentName
         */
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}
