package com.example.android.transitions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.view.View;
import android.widget.LinearLayout;

import com.example.android.transitions.Fragments.AddViewTransition;
import com.example.android.transitions.Fragments.FlipTransition;
import com.example.android.transitions.Fragments.ViewPagerSlidingTransition;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Scene[] mScenes;

    private int mCurrentScene = 0;

    private Fragment fragment;

    private LinearLayout fragmentContainer, contentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.button_view_pager).setOnClickListener(this);

        findViewById(R.id.button_flip).setOnClickListener(this);

        findViewById(R.id.button_layout_transition).setOnClickListener(this);

        contentContainer = findViewById(R.id.content_layout);

        fragmentContainer = findViewById(R.id.frame_container);

        /*final ViewGroup sceneRoot = findViewById(R.id.frame_container);

        final Scene startScene = Scene.getSceneForLayout(sceneRoot, R.layout.first_layout, this);

        final Scene endScene = Scene.getSceneForLayout(sceneRoot, R.layout.second_layout, this);

        final Transition transition = new ColorTransition().setDuration(3000);

        final TransitionSet transitionSet = new TransitionSet(this, null);

        transitionSet.addTransition(new ColorTransition().setDuration(1000));

        transitionSet.addTransition(new ChangeBounds().setStartDelay(1000));

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
        });*/
    }

    @Override
    public void onBackPressed() {

        if(getSupportFragmentManager().getBackStackEntryCount() != 0){
            getSupportFragmentManager().popBackStack();
            contentContainer.setVisibility(View.VISIBLE);
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.button_view_pager){

            fragment = new ViewPagerSlidingTransition();

        }else if(id == R.id.button_flip){

            fragment = new FlipTransition();

        }else if(id == R.id.button_layout_transition){

            fragment = new AddViewTransition();
        }

        contentContainer.setVisibility(View.GONE);

        fragmentContainer.setVisibility(View.VISIBLE);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.frame_container, fragment);

        fragmentTransaction.addToBackStack(fragment.getTag());

        fragmentTransaction.commit();
    }
}
