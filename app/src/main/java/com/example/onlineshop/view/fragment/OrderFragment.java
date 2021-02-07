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

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlineshop.R;
import com.example.onlineshop.data.database.Address;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.databinding.FragmentOrderBinding;
import com.example.onlineshop.view.activity.OnlineShoppingMainActivity;
import com.example.onlineshop.viewmodel.OrderViewModel;

import java.util.List;


public class OrderFragment extends Fragment {
    private FragmentOrderBinding mBinding;
    private OrderViewModel mViewModel;


    public OrderFragment() {
        // Required empty public constructor
    }


    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        mViewModel = new ViewModelProvider(this).get(OrderViewModel.class);


        mViewModel.getShoppingCartCustomer().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                String sumPrices = mViewModel.calculatorPrices(products);
                mBinding.sumShopping.setText(sumPrices);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_order,
                container,
                false);

        listener();

        return mBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getAddressLiveData().observe(getViewLifecycleOwner(), new Observer<Address>() {
            @Override
            public void onChanged(Address address) {
                mBinding.setAddress(address);
               mViewModel.setFullAddress(address.getFullAddress());
            }
        });
    }

    private void listener() {
        mBinding.btnSelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToListAddressPage();
            }
        });

        mBinding.btnSubmitCodeCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coupon = mBinding.textViewCodeCoupon.getText().toString();
                mViewModel.checkCouponCode(coupon);
                if(mViewModel.isCorrectCode())
                    mBinding.sumShopping.setText(mViewModel.calculatorPricesAfterDiscount());

            }
        });

        mBinding.btnPayProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewModel.submitOrder();
                        setBackNavigation();
                    }
                }, 3000);


            }
        });

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackNavigation();
            }
        });


    }

    private void goToListAddressPage(){
        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.list_address_fragment_des);
    }

    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();

        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
/*
        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.shopping_list_fragment_des);*/
    }


}