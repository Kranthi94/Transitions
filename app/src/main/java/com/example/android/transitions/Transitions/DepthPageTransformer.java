package com.example.android.transitions.Transitions;

import android.support.v4.view.ViewPager;
import android.view.View;

public class DepthPageTransformer implements ViewPager.PageTransformer {


    @Override
    public void transformPage(View view, float position) {

        float width = view.getWidth();

        if(position < -1 || position > 1){

            view.setAlpha(0);

        }else if(position > 0){

            float scaleFactor = 1 - position;

            view.setTranslationX(width*-position);

            view.setAlpha(scaleFactor);

            view.setScaleX(scaleFactor);

            view.setScaleY(scaleFactor);
        }else{

            view.setAlpha(1);
        }

    }
}
