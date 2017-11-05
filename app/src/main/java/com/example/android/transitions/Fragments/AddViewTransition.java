package com.example.android.transitions.Fragments;

import android.animation.LayoutTransition;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.transitions.R;

public class AddViewTransition extends Fragment {

    private int count = 0;

    private int[] colorArray = new int[]{Color.BLACK, Color.CYAN, Color.BLUE};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_view_layout, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ViewGroup root = view.findViewById(R.id.root_container);

        Button addViewButton = view.findViewById(R.id.button_add);

        addViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;

                View view = new View(getActivity());

                view.setLayoutParams(new LinearLayout.LayoutParams(root.getWidth(), root.getHeight()/10));

                view.setBackgroundColor(colorArray[count%colorArray.length]);

//                root.addView(view);

                LayoutTransition layoutTransition = new LayoutTransition();

                layoutTransition.addChild(root, view);
            }
        });
    }
}
