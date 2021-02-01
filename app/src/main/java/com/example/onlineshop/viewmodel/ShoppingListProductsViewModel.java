package com.example.onlineshop.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.List;

public class ShoppingListProductsViewModel extends ViewModel {
    private CustomerRepository mCustomerRepository;
    private LiveData<Boolean> mIsLoginLiveData;
    private LiveData<List<Order>> mOrdersCustomerLiveData;
    private ShopRepository mShopRepository;
    private LiveData<Customer> mCustomerLiveData;

    public ShoppingListProductsViewModel() {
        mCustomerRepository = CustomerRepository.getInstance();
        mShopRepository = ShopRepository.getInstance();
        mCustomerLiveData = mCustomerRepository.getCustomerLogin();
        mIsLoginLiveData = mCustomerRepository.getIsLogin();
        if (mCustomerLiveData.getValue() != null) {
            mCustomerRepository.getOrdersCustomer(mCustomerLiveData.getValue().getId());
        }
        mOrdersCustomerLiveData = mCustomerRepository.getOrdersCustomerLiveData();


    }

    public LiveData<Boolean> getIsLoginLiveData() {
        return mIsLoginLiveData;
    }

    public LiveData<List<Order>> getOrdersCustomerLiveData() {
        return mOrdersCustomerLiveData;
    }

    public List<Products> getProducts() {
        return mShopRepository.getAllProductsLiveData().getValue();
    }
}
