package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListCategoriesHomePageAdapter;
import com.example.onlineshop.adapter.ListProductsHomePageAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class HomePageViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;

    private LiveData<List<Products>> mLastProductsLiveData;
    private LiveData<List<Products>> mPopularityProductsLiveData;
    private LiveData<List<Products>> mRatingProductsLiveData;
    private LiveData<List<Categories>> mListCategoriesLiveData;



    public HomePageViewModel(@NonNull Application application) {
        super(application);

        mShopRepository = ShopRepository.getInstance(application);
        mLastProductsLiveData = mShopRepository.getLastProductsLiveData();
        mPopularityProductsLiveData = mShopRepository.getPopularityProductsLiveData();
        mRatingProductsLiveData = mShopRepository.getRatingProductsLiveData();
        mListCategoriesLiveData = mShopRepository.getCategoriesListLiveData();

    }


    public LiveData<List<Products>> getLastProductsLiveData() {
        return mLastProductsLiveData;
    }

    public LiveData<List<Products>> getPopularityProductsLiveData() {
        return mPopularityProductsLiveData;
    }

    public LiveData<List<Products>> getRatingProductsLiveData() {
        return mRatingProductsLiveData;
    }

    public LiveData<List<Categories>> getListCategoriesLiveData() {
        return mListCategoriesLiveData;
    }



    public String getUriImage(int idImage) {

        return Uri.parse("android.resource://" +
                R.class.getPackage().getName() +
                "/" +
                idImage).toString();
    }










}
