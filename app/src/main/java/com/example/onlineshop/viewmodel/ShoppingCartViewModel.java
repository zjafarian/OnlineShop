package com.example.onlineshop.viewmodel;


import android.app.Application;
import android.os.Build;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.LineItem;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartViewModel extends AndroidViewModel {

    private CustomerRepository mCustomerRepository;
    private LiveData<Boolean> mIsLoginLiveData;
    private ShopRepository mShopRepository;
    private LiveData<List<Products>> mProductsShoppingCartLiveData;
    private LiveData<Customer> mCustomerLiveData;

    public ShoppingCartViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance();
        mShopRepository = ShopRepository.getInstance();
        mProductsShoppingCartLiveData = mShopRepository.getProductsShoppingCartLiveData();
        mCustomerLiveData = mCustomerRepository.getCustomerLogin();
        mIsLoginLiveData = mCustomerRepository.getIsLogin();
    }


    public LiveData<Boolean> getIsLoginLiveData() {
        return mIsLoginLiveData;
    }

    public LiveData<List<Products>> getProductsShoppingCartLiveData() {
        return mProductsShoppingCartLiveData;
    }

    public void removeProductFromShoppingCart(Products products) {
        mShopRepository.removeProductAndLoadNewListProductsShoppingCart(products);
        mProductsShoppingCartLiveData = mShopRepository.getProductsShoppingCartLiveData();
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setProductsInSharedPreferences(List<Products> products){
        SharedPreferencesOnlineShop.setShoppingProducts(getApplication(),products);

    }


}
