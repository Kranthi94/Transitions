package com.example.android.transitions.Transitions;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

public class ColorTransition extends Transition {

    private static final String PACKAGE_NAME = "transition";

    private static final String BACKGROUND_COLOR = PACKAGE_NAME + ":change_color" + ":background";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValue(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValue(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, final TransitionValues endValues) {

        if(startValues == null || endValues == null){
            return null;
        }

        final View view = endValues.view;

        Object startDrawable = startValues.values.get(BACKGROUND_COLOR);

        Object endDrawable = endValues.values.get(BACKGROUND_COLOR);

        if(startDrawable instanceof ColorDrawable && endDrawable instanceof ColorDrawable){

            ColorDrawable start = (ColorDrawable) startDrawable;

            ColorDrawable end = (ColorDrawable) endDrawable;

            if(start.getColor() != end.getColor()) {

                ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), start.getColor(), end.getColor());

                colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        Object endValue = animation.getAnimatedValue();

                        if (endValue != null) {
                            view.setBackgroundColor((Integer) endValue);
                        }
                    }
                });

                return colorAnimator;
            }
        }

        return null;
    }

    private void captureValue(TransitionValues transitionValues){
        transitionValues.values.put(BACKGROUND_COLOR, transitionValues.view.getBackground());
    }
}
