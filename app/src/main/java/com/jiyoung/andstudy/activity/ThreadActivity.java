package com.jiyoung.andstudy.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiyoung.andstudy.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick();
            }
        });
    }

    private void buttonClick() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = dateFormat.format(new Date());

                Bundle bundle = new Bundle();
                bundle.putString("myKey", dateString);

                Message msg = handler.obtainMessage();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String string = bundle.getString("myKey");

            TextView myText = (TextView) findViewById(R.id.textview);
            myText.setText(string);
        }
    };
}
