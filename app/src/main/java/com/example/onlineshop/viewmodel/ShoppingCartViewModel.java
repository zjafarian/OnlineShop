package com.example.onlineshop.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.repository.CustomerRepository;

public class ShoppingCartViewModel extends ViewModel {

    private CustomerRepository mCustomerRepository;
    private LiveData<Boolean> mIsLogin;


    public ShoppingCartViewModel() {
        mCustomerRepository = CustomerRepository.getInstance();
        mIsLogin = mCustomerRepository.getIsLogin();

    }

    public LiveData<Boolean> getIsLogin() {
        return mIsLogin;
    }
}
