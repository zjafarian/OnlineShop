package com.example.onlineshop.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.List;

public class ShoppingListProductsViewModel extends AndroidViewModel {
    private CustomerRepository mCustomerRepository;
    private MutableLiveData<Boolean> mIsLoginLiveData = new MutableLiveData<>();
    private LiveData<List<Order>> mOrdersCustomerLiveData;
    private ShopRepository mShopRepository;
    private LiveData<Customer> mCustomerLiveData;

    public ShoppingListProductsViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance();
        mShopRepository = ShopRepository.getInstance();
        mCustomerLiveData = mCustomerRepository.getCustomerLogin();

        int idCustomer = SharedPreferencesOnlineShop.getCustomerId(getApplication());

        if (idCustomer != 0) {
            mCustomerRepository.getOrdersCustomer(idCustomer);
        }
        mOrdersCustomerLiveData = mCustomerRepository.getOrdersCustomerLiveData();
        boolean isOn = SharedPreferencesOnlineShop.getStatusLogin(getApplication());
        mIsLoginLiveData.setValue(isOn);

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
