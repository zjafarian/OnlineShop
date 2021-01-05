package com.example.onlineshop.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderHolder> {


    public SliderAdapter() {
    }

    @Override
    public SliderHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }
    @Override
    public void onBindViewHolder(SliderHolder viewHolder, int position) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    class SliderHolder extends SliderViewAdapter.ViewHolder{



    public SliderHolder(View itemView) {
        super(itemView);
    }
}
}
