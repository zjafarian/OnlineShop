package com.example.onlineshop.view.fragment;

import android.os.Bundle;

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
import com.example.onlineshop.databinding.FragmentShoppingCartBinding;
import com.example.onlineshop.viewmodel.ShoppingCartViewModel;


public class ShoppingCartFragment extends Fragment {
    public static final String ARGS_WHICH_PAGE = "whichPage";
    public static final String ARGS_WHICH_PAGE_SHOPPING = "whichPageShopping";
    private ShoppingCartViewModel mViewModel;
    private FragmentShoppingCartBinding mBinding;
    private String mPage;



    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    public static ShoppingCartFragment newInstance(String whichPage) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_WHICH_PAGE_SHOPPING,whichPage);

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
        mViewModel.getIsLogin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    mBinding.layoutNotLogin.setVisibility(View.GONE);
                    mBinding.layoutShoppingCart.setVisibility(View.VISIBLE);
                } else {
                    mBinding.layoutNotLogin.setVisibility(View.VISIBLE);
                    mBinding.layoutShoppingCart.setVisibility(View.GONE);
                }
            }
        });

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

    private void initView() {
        if (mPage.equals("productPage"))
            mBinding.cardViewBackPage.setVisibility(View.VISIBLE);
        else mBinding.cardViewBackPage.setVisibility(View.GONE);
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

}