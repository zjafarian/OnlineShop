package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListProductsFragment extends Fragment {



    public ListProductsFragment() {
        // Required empty public constructor
    }


    public static ListProductsFragment newInstance() {
        ListProductsFragment fragment = new ListProductsFragment();
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
        return inflater.inflate(R.layout.fragment_list_products, container, false);
    }
}