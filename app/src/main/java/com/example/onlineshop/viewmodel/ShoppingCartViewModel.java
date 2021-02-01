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

    public void submitOrder() {
        mProductsShoppingCartLiveData = mShopRepository.getProductsShoppingCartLiveData();
        List<Products> products = mProductsShoppingCartLiveData.getValue();

        int idCustomer = 0;
        if (mCustomerLiveData != null)
            idCustomer = mCustomerLiveData.getValue().getId();

        List<LineItem> lineItems = new ArrayList<>();
        if (products.size() != 0 && products != null) {
            for (Products productBuy : products) {
                LineItem lineItem = new LineItem();
                lineItem.setProductId(productBuy.getId());
                lineItem.setQuantity(1);

                lineItems.add(lineItem);
            }
        }

        Order order = new Order();
        order.setLineItems(lineItems);
        order.setCustomerId(idCustomer);

        mCustomerRepository.createOrder(order);
        afterSubmit();
        fetchGetOrders();
    }

    private void afterSubmit() {
        if (mCustomerRepository.getMessageOrder()!= null && mCustomerRepository.getMessageOrder().equals("successful")) {
            showToast("ثبت سفارش با موفقیت انجام شد");
            mShopRepository.removeAllProductsShoppingCart();
        } else showToast("ثبت سفارش با موفقیت انجام نشد");
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplication(),message,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();
    }

    private void fetchGetOrders(){
        mCustomerLiveData = mCustomerRepository.getCustomerLogin();
        mCustomerRepository.getOrdersCustomer(mCustomerLiveData.getValue().getId());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setProductsInSharedPreferences(List<Products> products){
        SharedPreferencesOnlineShop.setShoppingProducts(getApplication(),products);

    }


}
