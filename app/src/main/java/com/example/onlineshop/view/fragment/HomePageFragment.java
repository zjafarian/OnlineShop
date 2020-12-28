package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.LastProductsAdapter;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.databinding.FragmentHomePageBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private HomePageViewModel mHomePageViewModel;
    private FragmentHomePageBinding mBinding;
    private LastProductsAdapter mLastProductsAdapter;


    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


        mHomePageViewModel = new ViewModelProvider(requireActivity()).get(HomePageViewModel.class);
        mHomePageViewModel.fetchProductsAsync();
        mHomePageViewModel.getProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                updateUI();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home_page,
                container,
                false);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mBinding.recycleViewLastProducts.setLayoutManager(linearLayoutManager);


        return mBinding.getRoot();

    }

    public void updateUI() {
        if (mLastProductsAdapter == null) {
            mLastProductsAdapter = new LastProductsAdapter(this,mHomePageViewModel);
            mBinding.recycleViewLastProducts.setAdapter(mLastProductsAdapter);

        } else {
            mLastProductsAdapter.notifyDataSetChanged();
        }


    }


}