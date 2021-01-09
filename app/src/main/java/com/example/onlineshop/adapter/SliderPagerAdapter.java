package com.example.onlineshop.adapter;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.onlineshop.view.fragment.SliderImageFragment;

import java.util.ArrayList;
import java.util.List;

public class SliderPagerAdapter extends FragmentStatePagerAdapter {


    private List<SliderImageFragment> mImagesSliderFragments = new ArrayList<>();


    public SliderPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mImagesSliderFragments.get(position);
    }

    @Override
    public int getCount() {
        return mImagesSliderFragments.size();
    }

    public void setImages(List<String> urlImages) {
        mImagesSliderFragments = new ArrayList<>();
        for (String url : urlImages)
            mImagesSliderFragments.add(SliderImageFragment.newInstance(url));

        notifyDataSetChanged();
    }
}
