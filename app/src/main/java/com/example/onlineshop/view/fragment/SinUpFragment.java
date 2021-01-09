package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class SinUpFragment extends BottomSheetDialogFragment {



    public SinUpFragment() {
        // Required empty public constructor
    }


    public static SinUpFragment newInstance() {
        SinUpFragment fragment = new SinUpFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sin_up, container, false);
    }
}