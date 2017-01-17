package com.jiyoung.andstudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jiyoung.andstudy.R;

public class SendBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                broadcastIntent(view);
            }
        });
    }

    private void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.jiyoung.andstudy.activity");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES); //안드로이드 3.0 이상에서는 반드시 추가해야 하는 플래그
        sendBroadcast(intent);
    }
}
