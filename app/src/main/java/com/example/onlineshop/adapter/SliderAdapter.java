package com.example.onlineshop.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentSliderImageBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderHolder> {

    private List<String> mImageScrList;
    private Activity mActivity;


    public SliderAdapter(List<String> imageScrList) {
        mImageScrList = imageScrList;
    }

    @Override
    public SliderHolder onCreateViewHolder(ViewGroup parent) {

        mActivity = (Activity) parent.getContext();
        FragmentSliderImageBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mActivity),
                R.layout.fragment_slider_image,
                null,
                false);


        return new SliderHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(SliderHolder viewHolder, int position) {
        viewHolder.bindImage(mImageScrList.get(position));


    }

    @Override
    public int getCount() {
        return mImageScrList.size();
    }

    class SliderHolder extends SliderViewAdapter.ViewHolder {

        private FragmentSliderImageBinding mBinding;


        public SliderHolder(@NonNull FragmentSliderImageBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindImage(String src) {
            Picasso.get().load(src).into(mBinding.sliderImage);
        }





    }
}
