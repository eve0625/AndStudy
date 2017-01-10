package com.jiyoung.andstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyoung.andstudy.R;

public class MotionEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);

        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                handleTouch(motionEvent);
                return true;
            }
        });
    }

    private void handleTouch(MotionEvent m) {

        TextView textView1 = (TextView) findViewById(R.id.textview1);
        TextView textView2 = (TextView) findViewById(R.id.textview2);

        //포인터 갯수 조회. 항상 1 이상의 값을 가짐
        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            int id = m.getPointerId(i);
            int action = m.getActionMasked();
            int actionIndex = m.getActionIndex();
            String actionString;

            switch (action) {
                case MotionEvent.ACTION_DOWN :
                    actionString = "DOWN";
                    break;
                case MotionEvent.ACTION_UP :
                    actionString = "UP";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN :
                    actionString = "PNTR DOWN";
                    break;
                case MotionEvent.ACTION_POINTER_UP :
                    actionString = "PNTR UP";
                    break;
                case MotionEvent.ACTION_MOVE :
                    actionString = "MOVE";
                    break;
                default:
                    actionString = "";
            }

            String touchStatus = "Action: " + actionString + " Index: " + actionIndex +
                    " ID: " + id + " X: " + x + " Y: " + y;

            if (id == 0)
                textView1.setText(touchStatus);
            else
                textView2.setText(touchStatus);
         }

    }
}
