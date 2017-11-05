package com.example.android.transitions.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.transitions.R;

public class FlipTransition extends Fragment {

    private boolean backShown;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flip_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button flipButton = view.findViewById(R.id.flip_button);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, new CardFrontFragment());

        fragmentTransaction.commit();

        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(backShown){

                    backShown = false;

                    getActivity().getSupportFragmentManager().popBackStack();
                    return;
                }

                backShown = true;

                FragmentTransaction backFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                backFragmentTransaction.setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out
                );

                backFragmentTransaction.replace(R.id.fragment_container, new CardBackFragment());

                backFragmentTransaction.addToBackStack(null);

                backFragmentTransaction.commit();
            }
        });
    }

    public static class CardFrontFragment extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_front, container, false);
        }
    }

    public static class CardBackFragment extends Fragment{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_back, container, false);
        }
    }
}
