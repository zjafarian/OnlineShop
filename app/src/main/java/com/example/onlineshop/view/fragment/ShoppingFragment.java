package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ShoppingAdapter;
import com.example.onlineshop.databinding.FragmentShoppingBinding;
import com.example.onlineshop.viewmodel.ShoppingViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShoppingFragment extends Fragment {

    private FragmentShoppingBinding mBinding;
    private ShoppingViewModel mShoppingViewModel;



    public ShoppingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ShoppingFragment newInstance() {
        ShoppingFragment fragment = new ShoppingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShoppingViewModel = new ViewModelProvider(this).get(ShoppingViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_shopping,
                container,false);

        updateUI();

        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    public void updateUI() {

        List<String> shoppingTabs = new ArrayList<>();
        shoppingTabs.add("سبد خرید");
        shoppingTabs.add("لیست خریدها");



        if (mShoppingViewModel.getShoppingAdapter() == null) {
            mShoppingViewModel.setShoppingAdapter(new ShoppingAdapter(getActivity()));

            mBinding.viewPagerShoppingCart.setAdapter(mShoppingViewModel.getShoppingAdapter());



            new TabLayoutMediator(mBinding.tabShoppingCart, mBinding.viewPagerShoppingCart,
                    new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                            String nameTab = shoppingTabs.get(position);
                            tab.setText(nameTab);
                            mBinding.viewPagerShoppingCart.setCurrentItem(position);
                        }
                    }).attach();


        } else {
            mShoppingViewModel.updateUI();
        }
    }
}