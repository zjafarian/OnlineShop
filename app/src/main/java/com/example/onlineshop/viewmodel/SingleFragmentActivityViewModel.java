package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.onlineshop.data.repository.ShopRepository;


public class SingleFragmentActivityViewModel extends AndroidViewModel {

    public static final String FRAGMENT_TAG = "FragmentActivity";
    private ShopRepository mShopRepository;
    private boolean mDoubleBackPressToExit;
    private static final String TAG = "SingleFragmentActivityV";


    public SingleFragmentActivityViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "SingleFragmentActivityViewModel: " );
        mShopRepository = ShopRepository.getInstance(application);

        mShopRepository.getLastProductsAsync();
        mShopRepository.getPopularityProductsAsync();
        mShopRepository.getRatingProductsAsync();
        mShopRepository.getCategoriesAsync();
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
