package com.example.onlineshop.view.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListProductsShoppingCartAdapter;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.FragmentShoppingCartBinding;
import com.example.onlineshop.viewmodel.ShoppingCartViewModel;

import java.util.List;


public class ShoppingCartFragment extends Fragment {
    public static final String ARGS_WHICH_PAGE = "whichPage";
    public static final String ARGS_WHICH_PAGE_SHOPPING = "whichPageShopping";
    public static final String ARGS_PRODUCT_ID = "productId";
    private ShoppingCartViewModel mViewModel;
    private FragmentShoppingCartBinding mBinding;
    private String mPage;
    private ListProductsShoppingCartAdapter mAdapter;


    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    public static ShoppingCartFragment newInstance(String whichPage) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_WHICH_PAGE_SHOPPING, whichPage);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null)
            mPage = getArguments().getString(ARGS_WHICH_PAGE_SHOPPING);

        mViewModel = new ViewModelProvider(requireActivity()).get(ShoppingCartViewModel.class);
        setObservers();


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_shopping_cart,
                container,
                false);
        initView();
        listener();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onItemClick();
    }

    private void setObservers() {


            mViewModel.getIsLoginLiveData().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        mBinding.layoutNotLogin.setVisibility(View.GONE);
                        mBinding.layoutShoppingCart.setVisibility(View.VISIBLE);
                    } else {
                        mBinding.layoutNotLogin.setVisibility(View.VISIBLE);
                        mBinding.layoutShoppingCart.setVisibility(View.GONE);
                    }
                }
            });

        mViewModel.getProductsShoppingCartLiveData().observe(this, new Observer<List<Products>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChanged(List<Products> products) {
                if (products.size() != 0 && products != null) {
                    mBinding.btnPayProducts.setVisibility(View.VISIBLE);
                    mBinding.sumShoppingLabel.setVisibility(View.VISIBLE);
                    mBinding.sumShopping.setVisibility(View.VISIBLE);
                    mBinding.currencyShopping.setVisibility(View.VISIBLE);
                    long sum = 0;
                    for (int i = 0; i <products.size() ; i++) {
                        sum += Integer.valueOf(products.get(i).getPrice());
                    }
                    mBinding.sumShopping.setText(String.valueOf(sum));
                }
                else {
                    mBinding.btnPayProducts.setVisibility(View.GONE);
                    mBinding.sumShoppingLabel.setVisibility(View.GONE);
                    mBinding.sumShopping.setVisibility(View.GONE);
                    mBinding.currencyShopping.setVisibility(View.GONE);
                }
                mViewModel.setProductsInSharedPreferences(products);
                updateUI(products);
            }
        });
    }

    private void initView() {
        if (mPage == null)
            mBinding.cardViewBackPage.setVisibility(View.GONE);
        else if (mPage.equals("productPage"))
            mBinding.cardViewBackPage.setVisibility(View.VISIBLE);


        initRecycleView();
    }

    private void initRecycleView() {
        mBinding.recycleViewOnShoppingCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ListProductsShoppingCartAdapter();
        mBinding.recycleViewOnShoppingCart.setAdapter(mAdapter);
    }

    private void listener() {
        mBinding.loginCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginPage();
            }
        });

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackNavigation();
            }
        });

        mBinding.btnPayProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.submitOrder();
            }
        });
    }

    private void startLoginPage() {
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_WHICH_PAGE, "shopping");

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.login_sign_up_fragment_des, bundle);
    }

    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }

    private void updateUI(List<Products> products) {
        mAdapter.setData(products);
    }

    private void onItemClick() {

        mAdapter.onItemClicked(new ListProductsShoppingCartAdapter.OnItemClickProduct() {
            @Override
            public void onItemClicked(Products products, int position) {
                setNavigationToProductPage(products);
            }
        });


        mAdapter.onDeleteSelect(new ListProductsShoppingCartAdapter.onItemClickDeleteProductFromList() {
            @Override
            public void onDeleteSelect(Products products, int position) {
                mViewModel.removeProductFromShoppingCart(products);
            }
        });


    }

    private void setNavigationToProductPage(Products products) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_PRODUCT_ID, products.getId());

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.product_detail_fragment_des, bundle);
    }

}