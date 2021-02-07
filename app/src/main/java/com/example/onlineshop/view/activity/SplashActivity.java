package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.onlineshop.R;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.databinding.ActivitySplashBinding;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding mBinding;


    private boolean mIsConnect;

    private ShopRepository mShopRepository;
    private CustomerRepository mCustomerRepository;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);


        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getStatusNetwork();

        if (mIsConnect) {
            mBinding.layoutNoInternet.setVisibility(View.GONE);
            mBinding.splashOnlineShop.playAnimation();
            requestToServer();
            setDataFromSharedPreferences();
            startOnlineShoppingMainActivity();
        } else {
            mBinding.layoutNoInternet.setVisibility(View.VISIBLE);
        }

        listener();
    }

    private void getStatusNetwork() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        ConnectivityManager cm = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        mIsConnect = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    private void startOnlineShoppingMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.layoutNoInternet.setVisibility(View.GONE);
                startActivity(OnlineShoppingMainActivity.newIntent(getApplicationContext()));
                finish();
            }
        }, 8000);
    }

    private void listener() {
        mBinding.btnRetryConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataFromSharedPreferences();
                if (mIsConnect) {
                    startOnlineShoppingMainActivity();
                }
            }
        });
    }

    private void setDataFromSharedPreferences() {
        int id = SharedPreferencesOnlineShop.getCustomerId(getApplicationContext());
        boolean isLogin = SharedPreferencesOnlineShop.getStatusLogin(getApplicationContext());
        Set<String> productsShopping = SharedPreferencesOnlineShop.getShoppingProducts(getApplicationContext());
        if (productsShopping != null) {
            //mShopRepository.clearShoppingList();
            if (mShopRepository.getProductsListShopping().size() == 0) {
                getDataSavedByRequestServer(productsShopping);

            } else  mShopRepository.setShoppingProductsLiveData();
        }

        mCustomerRepository.setIsLogin(isLogin);
        if (isLogin) {
            mCustomerRepository.getOrdersCustomer(id);
            mCustomerRepository.findCustomerById(id);
        }
    }

    private void requestToServer() {
        mShopRepository = ShopRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance();
        mShopRepository.getLastProductsAsync();
        mShopRepository.getPopularityProductsAsync();
        mShopRepository.getRatingProductsAsync();
        mShopRepository.getCategoriesAsync();
        mShopRepository.getAllProductsAsync();
        mShopRepository.fetchCreateCoupon();
    }

    public void getDataSavedByRequestServer(Set<String> productsId) {
        List<Integer> intIds = new ArrayList<>();

        for (Iterator<String> ids = productsId.iterator(); ids.hasNext(); ) {
            String id = ids.next();
            intIds.add(Integer.valueOf(id));
        }
        for (int i = 0; i < intIds.size(); i++) {
            mShopRepository.getProductById((int) intIds.get(i),"splash");
        }

        mShopRepository.setShoppingProductsLiveData();
    }


}