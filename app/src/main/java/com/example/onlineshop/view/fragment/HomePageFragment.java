package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListProductsAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.remote.NetworkParams;
import com.example.onlineshop.databinding.FragmentHomePageBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;

import java.util.List;

public class HomePageFragment extends Fragment {
    private HomePageViewModel mHomePageViewModel;
    private FragmentHomePageBinding mBinding;
    private ListProductsAdapter mLastProductsAdapter;
    private ListProductsAdapter mPopularityProductsAdapter;
    private ListProductsAdapter mRatingProductsAdapter;
    private SliderAdapter mSliderAdapter;


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
        setLiveDataObservers();

    }

    private void setLiveDataObservers() {
        mHomePageViewModel.getLastProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                updateUI(products, NetworkParams.LAST);
            }
        });

        mHomePageViewModel.getPopularityProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                updateUI(products, NetworkParams.POPULARITY);

            }
        });

        mHomePageViewModel.getRatingProductsLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                updateUI(products, NetworkParams.RATING);
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




        return mBinding.getRoot();

    }

    public void updateUI(List<Product> products, String selectAdapter) {

        switch (selectAdapter) {
            case NetworkParams.LAST:
                if (mLastProductsAdapter == null) {

                    mLastProductsAdapter = new ListProductsAdapter
                            (this, products, mHomePageViewModel);
                    mBinding.recycleViewLastProducts.setAdapter(mLastProductsAdapter);
                } else {
                    mLastProductsAdapter.notifyDataSetChanged();
                }

                break;
            case NetworkParams.POPULARITY:
                if (mPopularityProductsAdapter == null) {

                    mPopularityProductsAdapter = new ListProductsAdapter
                            (this, products, mHomePageViewModel);
                    mBinding.recycleViewPopularityProducts.setAdapter(mPopularityProductsAdapter);

                } else {
                   mPopularityProductsAdapter.notifyDataSetChanged();
                }

                break;
            case NetworkParams.RATING:
                if (mRatingProductsAdapter == null) {

                    mRatingProductsAdapter = new ListProductsAdapter
                            (this, products, mHomePageViewModel);
                    mBinding.recycleViewRatingProducts.setAdapter(mRatingProductsAdapter);

                } else {
                    mRatingProductsAdapter.notifyDataSetChanged();
                }
                break;
        }







    }


}