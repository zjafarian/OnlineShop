package com.example.onlineshop.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;


public class BindingAdapters {

    @BindingAdapter("app:src")
    public static void loadImage(ImageView imageView, String url) {
        if (!url.equals("") && url != null) {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.place_holder_online_shop)
                    .into(imageView);

        }

    }


}









