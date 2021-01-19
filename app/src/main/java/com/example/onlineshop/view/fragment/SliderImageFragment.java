package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;

public class SliderImageFragment extends Fragment {



    public SliderImageFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SliderImageFragment newInstance(String imageUri) {
        SliderImageFragment fragment = new SliderImageFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider_image, container, false);
    }
}