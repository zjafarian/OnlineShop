package com.example.onlineshop.adapter;


import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.onlineshop.R;

import com.squareup.picasso.Picasso;



public class BindingAdapters {

    @BindingAdapter("app:src")
    public static void loadImage(ImageView imageView, String url){
        if(!url.equals("")){
           Picasso.get()
                   .load(url)
                   .placeholder(R.drawable.place_holder_online_shop)
                   .into(imageView);

        }

    }






}
