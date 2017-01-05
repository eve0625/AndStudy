package com.jiyoung.andstudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.service.MyService;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Button btn = (Button) findViewById(R.id.btn_start_service);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                intent.putExtra("count", 100); //Service에 파라미터 전달
                startService(intent); //Service 클래스의 onCreate(최초한번)와 onStartCommand가 호출됨
            }
        });

        btn = (Button) findViewById(R.id.btn_stop_service);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                stopService(intent); //구동중인 Service 클래스의 onDestroy가 호출됨
            }
        });
    }
}
