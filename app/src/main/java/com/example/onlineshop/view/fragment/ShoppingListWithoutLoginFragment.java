package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;


public class ShoppingListWithoutLoginFragment extends Fragment {



    public ShoppingListWithoutLoginFragment() {
        // Required empty public constructor
    }


    public static ShoppingListWithoutLoginFragment newInstance() {
        ShoppingListWithoutLoginFragment fragment = new ShoppingListWithoutLoginFragment();
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
        return inflater.inflate(R.layout.fragment_shopping_list_without_login, container, false);
    }
}