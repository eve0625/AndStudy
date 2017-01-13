package com.jiyoung.andstudy.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;

import com.jiyoung.andstudy.R;

import static android.os.Build.VERSION.SDK;
import static android.os.Build.VERSION.SDK_INT;

public class TransitionActivity extends AppCompatActivity {

    ViewGroup myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        myLayout = (ViewGroup) findViewById(R.id.activity_transition);

        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                handleTouch();
                return true;
            }
        });
    }

    public void handleTouch() {

        View view = findViewById(R.id.button);

        if (SDK_INT >= 19) {
            Transition changeBounds = new ChangeBounds();
            changeBounds.setDuration(3000);
            changeBounds.setInterpolator(new BounceInterpolator());

            TransitionManager.beginDelayedTransition(myLayout, changeBounds);
        }

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        view.setLayoutParams(params);

        ViewGroup.LayoutParams lparams = view.getLayoutParams();
        lparams.width = 500;
        lparams.height = 350;
        view.setLayoutParams(lparams);
    }
}
