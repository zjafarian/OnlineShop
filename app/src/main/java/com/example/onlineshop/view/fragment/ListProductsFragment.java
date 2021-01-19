package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListCategoriesHomePageAdapter;
import com.example.onlineshop.adapter.ListProductsAdapter;

import com.example.onlineshop.adapter.ListProductsHomePageAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.databinding.FragmentListProductsBinding;
import com.example.onlineshop.viewmodel.ListProductsViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;


public class ListProductsFragment extends Fragment {
    public static final String ARGS_SELECT_LIST_PRODUCTS = "selectListProducts";
    public static final String ARGS_CATEGORY_ID = "categoryId";
    private FragmentListProductsBinding mBinding;
    private ListProductsViewModel mListProductsViewModel;
    private String mSelectListProducts;
    private ListProductsAdapter mListProductAdapter;
    private int mCategoryId;


    public ListProductsFragment() {
        // Required empty public constructor
    }

    public static ListProductsFragment newInstance(String selectListProducts,int categoryId) {
        ListProductsFragment fragment = new ListProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_SELECT_LIST_PRODUCTS, selectListProducts);
        args.putInt(ARGS_CATEGORY_ID,categoryId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSelectListProducts = getArguments().getString(ARGS_SELECT_LIST_PRODUCTS);
            mCategoryId = getArguments().getInt(ARGS_CATEGORY_ID);
        }
        setRetainInstance(true);

        mListProductsViewModel = new
                ViewModelProvider(requireActivity()).get(ListProductsViewModel.class);
        if (mCategoryId !=0)
            mListProductsViewModel.setCategoryId(mCategoryId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_list_products,
                container,
                false);

        mBinding.recycleViewListProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        initRecycler();




        return mBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateUI();

    }

    private void initRecycler() {
        mListProductAdapter = new ListProductsAdapter();
        mBinding.recycleViewListProducts.setAdapter(mListProductAdapter);
    }

    private void updateUI() {
        mListProductsViewModel.setSelectListProducts(mSelectListProducts);
        if (mCategoryId != 0)

        mListProductAdapter.setData(mListProductsViewModel.getProductList());
    }
}