package com.jiyoung.andstudy.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.jiyoung.andstudy.R;

public class SceneTransitionActivity extends AppCompatActivity {

    ViewGroup rootContainer;
    Scene scene1;
    Scene scene2;
    Transition transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_transition);

        rootContainer = (ViewGroup) findViewById(R.id.root_container);

        if (Build.VERSION.SDK_INT >= 19) {

            transition = TransitionInflater.from(this).inflateTransition(R.transition.transition);

            scene1 = Scene.getSceneForLayout(rootContainer, R.layout.scene1_layout, this);
            scene2 = Scene.getSceneForLayout(rootContainer, R.layout.scene2_layout, this);

            scene1.enter();
        }
    }

    public void goToScene1(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            TransitionManager.go(scene1, transition);
        }
    }

    public void goToScene2(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            TransitionManager.go(scene2, transition);
        }
    }
}
