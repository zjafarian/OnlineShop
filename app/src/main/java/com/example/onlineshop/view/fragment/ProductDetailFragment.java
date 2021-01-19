package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.FragmentProductDetailBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.example.onlineshop.viewmodel.ProductDetailViewModel;


public class ProductDetailFragment extends Fragment {


    public static final String ARG_PRODUCT_ID = "ArgProductId";
    private int mProductId;
    private ProductDetailViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(int productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID,productId);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null)
            mProductId = getArguments().getInt(ARG_PRODUCT_ID);

        mViewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_product_detail,
                container,
                false);

        return mBinding.getRoot();
    }
}