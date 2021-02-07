package com.example.onlineshop.viewmodel;


import android.app.Application;
import android.os.Build;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.LineItem;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ShoppingCartViewModel extends AndroidViewModel {

    private CustomerRepository mCustomerRepository;
    private MutableLiveData<Boolean> mIsLoginLiveData = new MutableLiveData<>();
    private ShopRepository mShopRepository;
    private LiveData<List<Products>> mProductsShoppingCartLiveData;
    private LiveData<Customer> mCustomerLiveData;

    public ShoppingCartViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance();
        mShopRepository = ShopRepository.getInstance();
        Set<String> productsId = SharedPreferencesOnlineShop.getShoppingProducts(getApplication());
        if (productsId != null) {
            //mShopRepository.clearShoppingList();
            if (mShopRepository.getProductsListShopping().size() == 0)
                getDataSavedByRequestServer(productsId);
            else mShopRepository.setShoppingProductsLiveData();
        }
        boolean isOn = SharedPreferencesOnlineShop.getStatusLogin(getApplication());
        mIsLoginLiveData.setValue(isOn);
        mProductsShoppingCartLiveData = mShopRepository.getProductsShoppingCartLiveData();
        mCustomerLiveData = mCustomerRepository.getCustomerLogin();

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
    public void setProductsInSharedPreferences(List<Products> products) {
        SharedPreferencesOnlineShop.setShoppingProducts(getApplication(), products);

    }

    public void getDataSavedByRequestServer(Set<String> productsId) {
        List<Integer> intIds = new ArrayList<>();

        for (Iterator<String> ids = productsId.iterator(); ids.hasNext(); ) {
            String id = ids.next();
            intIds.add(Integer.valueOf(id));
        }
        for (int i = 0; i < intIds.size(); i++) {
            mShopRepository.getProductById((int) intIds.get(i), "splash");
        }
        mShopRepository.setShoppingProductsLiveData();
    }


}
