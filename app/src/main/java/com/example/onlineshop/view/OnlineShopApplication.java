package com.example.onlineshop.view;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.List;
import java.util.Set;

public class OnlineShopApplication extends Application {
    private ShopRepository mShopRepository;
    private CustomerRepository mCustomerRepository;


    @Override
    public void onCreate() {
        super.onCreate();
        mShopRepository = ShopRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance();
        mShopRepository.getLastProductsAsync();
        mShopRepository.getPopularityProductsAsync();
        mShopRepository.getRatingProductsAsync();
        mShopRepository.getCategoriesAsync();
        mShopRepository.getAllProductsAsync();



        int id = SharedPreferencesOnlineShop.getCustomerId(getApplicationContext());
        boolean isLogin = SharedPreferencesOnlineShop.getStatusLogin(getApplicationContext());
        Set<String> productsShopping = SharedPreferencesOnlineShop.getShoppingProducts(getApplicationContext());
        if(productsShopping != null){
            mShopRepository.setProductsShoppingFromDataBase(productsShopping);
        }

        mCustomerRepository.setIsLogin(isLogin);
        if (isLogin) {
            mCustomerRepository.getOrdersCustomer(id);
            mCustomerRepository.findCustomerById(id);
        }


    }


}
