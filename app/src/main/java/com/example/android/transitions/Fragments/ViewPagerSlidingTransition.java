package com.example.android.transitions.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.transitions.R;
import com.example.android.transitions.Transitions.DepthPageTransformer;
import com.example.android.transitions.Transitions.ZoomOutPageTransformer;

public class ViewPagerSlidingTransition extends Fragment {

    private static final int NUM_PAGES = 5;

    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.view_pager);

        FragmentStatePagerAdapter fragmentStatePagerAdapter = new SlidingPagerAdapter(getActivity().getSupportFragmentManager());

        viewPager.setPageTransformer(true, new DepthPageTransformer());

        viewPager.setAdapter(fragmentStatePagerAdapter);
    }

    private class SlidingPagerAdapter extends FragmentStatePagerAdapter {

        public SlidingPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            return new PagerFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
