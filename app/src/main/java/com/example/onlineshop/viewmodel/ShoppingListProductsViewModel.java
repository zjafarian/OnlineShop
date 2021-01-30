package com.example.onlineshop.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.repository.CustomerRepository;

public class ShoppingListProductsViewModel extends ViewModel {
    private CustomerRepository mCustomerRepository;
    private LiveData<Boolean> mIsLoginLiveData;
    public ShoppingListProductsViewModel() {
        mCustomerRepository = CustomerRepository.getInstance();
        mIsLoginLiveData = mCustomerRepository.getIsLogin();

    }

    public LiveData<Boolean> getIsLoginLiveData() {
        return mIsLoginLiveData;
    }
}
