package com.example.android.transitions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.transitions.Transitions.ColorTransition;

public class MainActivity extends AppCompatActivity {

    private Scene[] mScenes;

    private int mCurrentScene = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final ViewGroup sceneRoot = findViewById(R.id.scene_root);

//        final Scene startScene = Scene.getSceneForLayout(sceneRoot, R.layout.first_layout, this);

//        final Scene endScene = Scene.getSceneForLayout(sceneRoot, R.layout.second_layout, this);

//        final Transition transition = new ColorTransition().setDuration(3000);

        final TransitionSet transitionSet = new TransitionSet(this, null);

        transitionSet.addTransition(new ColorTransition());

        transitionSet.addTransition(new ChangeBounds());

        transitionSet.setDuration(3000);

        mScenes = new Scene[]{
                Scene.getSceneForLayout(sceneRoot, R.layout.scene_1, this),
                Scene.getSceneForLayout(sceneRoot, R.layout.scene_2, this),
        };

        TransitionManager.go(mScenes[mCurrentScene]);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentScene = (mCurrentScene + 1)%(mScenes.length);

                TransitionManager.go(mScenes[mCurrentScene], transitionSet);
            }
        });
    }
}
