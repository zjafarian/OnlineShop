package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.FragmentProductDetailBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.example.onlineshop.viewmodel.ProductDetailViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailFragment extends Fragment {


    public static final String ARGS_PRODUCT_ID = "productId";
    private int mProductId;
    private ProductDetailViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;
    private SliderAdapter mSliderAdapter;
    private Products mProduct;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(int productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_PRODUCT_ID, productId);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null)
            mProductId = getArguments().getInt(ARGS_PRODUCT_ID);

        mViewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);
        mViewModel.findProduct(mProductId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_product_detail,
                container,
                false);

        listener();

        return mBinding.getRoot();
    }

    private void listener() {
        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackNavigation();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLiveDataObserver();
    }

    private void setLiveDataObserver() {
        mViewModel.getProductLiveData().observe(getViewLifecycleOwner(), new Observer<Products>() {
            @Override
            public void onChanged(Products products) {
                mBinding.setProduct(products);
                initSlider();
            }
        });
    }

    public void initSlider() {
        List<String> imagesUri = mViewModel.getImagesUriProduct();
        mSliderAdapter = new SliderAdapter(imagesUri);

        if (imagesUri.size() != 0 && imagesUri != null) {
            mBinding.productImagesSlider.setSliderAdapter(mSliderAdapter);
            mBinding.productImagesSlider.startAutoCycle();
            mBinding.productImagesSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
            mBinding.productImagesSlider.setSliderTransformAnimation
                    (SliderAnimations.SIMPLETRANSFORMATION);
        }

    }

    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }
}