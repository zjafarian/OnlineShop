package com.example.onlineshop.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListProductsHomePageAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.remote.NetworkParams;
import com.example.onlineshop.databinding.FragmentHomePageBinding;
import com.example.onlineshop.view.activity.ListProductsActivity;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private HomePageViewModel mHomePageViewModel;
    private FragmentHomePageBinding mBinding;
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

        mSliderAdapter = new SliderAdapter(new ArrayList<String>() {{
            add(mHomePageViewModel.getUriImage(R.drawable.slider_one));
            add(mHomePageViewModel.getUriImage(R.drawable.slider_two));
            add(mHomePageViewModel.getUriImage(R.drawable.slider_three));
            add(mHomePageViewModel.getUriImage(R.drawable.slider_four));
        }});


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

        mBinding.setHomePageViewModel(mHomePageViewModel);

        mBinding.specialProductsSlider.setSliderAdapter(mSliderAdapter);
        mBinding.specialProductsSlider.startAutoCycle();
        mBinding.specialProductsSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mBinding.specialProductsSlider.setSliderTransformAnimation
                (SliderAnimations.SIMPLETRANSFORMATION);

        listener();


        return mBinding.getRoot();

    }

    private void listener() {
        mBinding.textViewAllLastProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.LAST);
            }
        });

        mBinding.imgViewAllLastProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.LAST);
            }
        });

        mBinding.textViewAllPopularityProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.POPULARITY);

            }
        });

        mBinding.imgViewAllPopularityProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.POPULARITY);
            }
        });

        mBinding.textViewAllRatingProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.RATING);
            }
        });

        mBinding.imgViewAllRatingProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.RATING);
            }
        });
    }


    private void startListProducts(String selectFilter) {
        Intent listProductsActivity = ListProductsActivity.newIntent(getActivity(), selectFilter);
        getActivity().startActivity(listProductsActivity);
    }

    public void updateUI(List<Product> products, String selectAdapter) {
        switch (selectAdapter) {

            case NetworkParams.LAST:

                if (mHomePageViewModel.getLastProductsAdapter() == null) {

                    ListProductsHomePageAdapter listProductsHomePageAdapter =
                            new ListProductsHomePageAdapter(this, products, mHomePageViewModel);

                    mHomePageViewModel.setLastProductsAdapter(listProductsHomePageAdapter);

                    mBinding.recycleViewLastProducts.setAdapter
                            (mHomePageViewModel.getLastProductsAdapter());

                } else mHomePageViewModel.updateAdapters(selectAdapter);

                break;

            case NetworkParams.POPULARITY:

                if (mHomePageViewModel.getPopularityProductsAdapter() == null) {

                    ListProductsHomePageAdapter listProductsHomePageAdapter =
                            new ListProductsHomePageAdapter(this, products, mHomePageViewModel);

                    mHomePageViewModel.setPopularityProductsAdapter(listProductsHomePageAdapter);

                    mBinding.recycleViewPopularityProducts.setAdapter
                            (mHomePageViewModel.getPopularityProductsAdapter());

                } else mHomePageViewModel.updateAdapters(selectAdapter);

                break;

            case NetworkParams.RATING:

                if (mHomePageViewModel.getRatingProductsAdapter() == null) {

                    ListProductsHomePageAdapter listProductsHomePageAdapter =
                            new ListProductsHomePageAdapter(this, products, mHomePageViewModel);

                    mHomePageViewModel.setRatingProductsAdapter(listProductsHomePageAdapter);

                    mBinding.recycleViewRatingProducts.setAdapter
                            (mHomePageViewModel.getRatingProductsAdapter());

                } else mHomePageViewModel.updateAdapters(selectAdapter);

                break;
        }
    }


}