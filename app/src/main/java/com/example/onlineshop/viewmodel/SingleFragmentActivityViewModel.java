package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.repository.ShopRepository;


public class SingleFragmentActivityViewModel extends ViewModel {

    public static final String FRAGMENT_TAG = "FragmentActivity";
    private ShopRepository mShopRepository;
    private boolean mDoubleBackPressToExit;
    private static final String TAG = "SingleFragmentActivityV";


    public SingleFragmentActivityViewModel() {

        Log.d(TAG, "SingleFragmentActivityViewModel: " );
        mShopRepository = ShopRepository.getInstance();

        mShopRepository.getLastProductsAsync();
        mShopRepository.getPopularityProductsAsync();
        mShopRepository.getRatingProductsAsync();
        mShopRepository.getCategoriesAsync();
        mShopRepository.getAllProductsAsync();
    }


    public boolean isDoubleBackPressToExit() {
        return mDoubleBackPressToExit;
    }

    public void setDoubleBackPressToExit(boolean doubleBackPressToExit) {
        mDoubleBackPressToExit = doubleBackPressToExit;
    }


    public void handlerDoubleBackPress() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mDoubleBackPressToExit = false;
            }
        }, 2000);
    }





}
