package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentShoppingListProductsBinding;
import com.example.onlineshop.viewmodel.ShoppingListProductsViewModel;


public class ShoppingListProductsFragment extends Fragment {
    private FragmentShoppingListProductsBinding mBinding;
    private ShoppingListProductsViewModel mViewModel;
    public static final String ARGS_WHICH_PAGE = "whichPage";


    public ShoppingListProductsFragment() {
        // Required empty public constructor
    }


    public static ShoppingListProductsFragment newInstance() {
        ShoppingListProductsFragment fragment = new ShoppingListProductsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mViewModel = new ViewModelProvider(requireActivity()).get(ShoppingListProductsViewModel.class);
        mViewModel.getIsLoginLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    mBinding.layoutNotLogin.setVisibility(View.GONE);
                    mBinding.layoutShoppingListProducts.setVisibility(View.VISIBLE);
                } else {
                    mBinding.layoutNotLogin.setVisibility(View.VISIBLE);
                    mBinding.layoutShoppingListProducts.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_shopping_list_products,
                container,
                false);
        listener();


        return mBinding.getRoot();
    }

    private void listener() {
        mBinding.loginCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginPage();
            }
        });
    }

    private void startLoginPage() {
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_WHICH_PAGE, "shopping");

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.login_sign_up_fragment_des, bundle);
    }
}