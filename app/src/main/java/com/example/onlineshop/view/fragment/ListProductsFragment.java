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

import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.databinding.FragmentListProductsBinding;
import com.example.onlineshop.viewmodel.ListProductsViewModel;

import java.util.List;


public class ListProductsFragment extends Fragment {

    public static final String ARGS_SELECT_LIST_PRODUCTS = "selectListProducts";
    private FragmentListProductsBinding mBinding;
    private ListProductsViewModel mListProductsViewModel;
    private String mSelectListProducts;


    public ListProductsFragment() {
        // Required empty public constructor
    }


    public static ListProductsFragment newInstance(String selectListProducts) {
        ListProductsFragment fragment = new ListProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_SELECT_LIST_PRODUCTS, selectListProducts);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSelectListProducts = getArguments().getString(ARGS_SELECT_LIST_PRODUCTS);
        }
        setRetainInstance(true);

        mListProductsViewModel = new
                ViewModelProvider(requireActivity()).get(ListProductsViewModel.class);



        setLiveDataObservers();

    }

    private void setLiveDataObservers() {
        mListProductsViewModel.getLastProductsLiveData().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                updateUI();
            }
        });


        mListProductsViewModel.getPopularityProductsLiveData().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                updateUI();
            }
        });

        mListProductsViewModel.getRatingProductsLiveData().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                updateUI();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_list_products,
                container,
                false);

        mBinding.recycleViewListProducts.setLayoutManager(new LinearLayoutManager(getActivity()));




        return mBinding.getRoot();
    }

    private void updateUI() {
        mListProductsViewModel.setSelectListProducts(mSelectListProducts);

        if (mListProductsViewModel.getListProductAdapter()==null){

            ListProductsAdapter listProductsAdapter = new ListProductsAdapter
                    (this,mListProductsViewModel.getProductList(),mListProductsViewModel);

            mListProductsViewModel.setListProductAdapter(listProductsAdapter);

            mBinding.recycleViewListProducts.setAdapter
                    (mListProductsViewModel.getListProductAdapter());
        } else mListProductsViewModel.notifyAdapter();

    }
}