package com.example.android.transitions.Transitions;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.3f;

    private static final float MIN_ALPHA = 0.50f;

    @Override
    public void transformPage(View view, float position) {

        int pageWidth = view.getWidth();

        int pageHeight = view.getHeight();

        if(position < -1 || position > 1){

            view.setAlpha(0);

        } else{

            float scaleFactor = Math.max(MIN_SCALE, 1-Math.abs(position));

            float verticalMargin = (pageHeight*(1 - scaleFactor))/2;

            float horizontalMargin = (pageWidth*(1 - scaleFactor))/2;

//            if (position < 0) {
//                view.setTranslationX(horizontalMargin - verticalMargin / 2);
//            } else {
//                view.setTranslationX(-horizontalMargin + verticalMargin / 2);
//            }

            view.setScaleX(scaleFactor);

            view.setScaleY(scaleFactor);

            view.setAlpha((1-Math.abs(position)));

            /*view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) /
                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));*/
        }
    }
}
