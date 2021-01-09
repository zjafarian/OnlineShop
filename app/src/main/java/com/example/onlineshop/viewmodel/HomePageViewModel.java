package com.example.onlineshop.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListProductsHomePageAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.view.activity.ListProductsActivity;

import java.util.ArrayList;
import java.util.List;

public class HomePageViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;
    private Product mProduct;
    private LiveData<List<Product>> mLastProductsLiveData;
    private LiveData<List<Product>> mPopularityProductsLiveData;
    private LiveData<List<Product>> mRatingProductsLiveData;
    private ListProductsHomePageAdapter mLastProductsAdapter;
    private ListProductsHomePageAdapter mPopularityProductsAdapter;
    private ListProductsHomePageAdapter mRatingProductsAdapter;
    private SliderAdapter mSliderAdapter;



    public HomePageViewModel(@NonNull Application application) {
        super(application);

        mShopRepository = ShopRepository.getInstance(application);
        mLastProductsLiveData = mShopRepository.getLastProductsLiveData();
        mPopularityProductsLiveData = mShopRepository.getPopularityProductsLiveData();
        mRatingProductsLiveData = mShopRepository.getRatingProductsLiveData();

    }

    public void fetchProductsAsync() {
        mShopRepository.getLastProductsAsync();
        mShopRepository.getPopularityProductsAsync();
        mShopRepository.getRatingProductsAsync();
    }

    public LiveData<List<Product>> getLastProductsLiveData() {
        return mLastProductsLiveData;
    }

    public LiveData<List<Product>> getPopularityProductsLiveData() {
        return mPopularityProductsLiveData;
    }

    public LiveData<List<Product>> getRatingProductsLiveData() {
        return mRatingProductsLiveData;
    }


    public String getFirstImageSrc(Product product) {
        mProduct = product;
        if (product.getProductPhotos().length != 0)
            return product.getProductPhotos()[0].getPhotoSrc();
        return null;
    }

    public String getUriImage(int idImage) {

        return Uri.parse("android.resource://" +
                R.class.getPackage().getName() +
                "/" +
                idImage).toString();
    }


    public void updateAdapters(String adapterName) {

        switch (adapterName) {
            case NetworkParams.LAST:
                mLastProductsAdapter.notifyDataSetChanged();
                break;

            case NetworkParams.POPULARITY:
                mPopularityProductsAdapter.notifyDataSetChanged();
                break;

            case NetworkParams.RATING:
                mRatingProductsAdapter.notifyDataSetChanged();
                break;
        }
    }


    public void setLastProductsAdapter(ListProductsHomePageAdapter lastProductsAdapter) {
        mLastProductsAdapter = lastProductsAdapter;
    }

    public void setPopularityProductsAdapter(ListProductsHomePageAdapter popularityProductsAdapter) {
        mPopularityProductsAdapter = popularityProductsAdapter;
    }

    public void setRatingProductsAdapter(ListProductsHomePageAdapter ratingProductsAdapter) {
        mRatingProductsAdapter = ratingProductsAdapter;
    }

    public void setSliderAdapter(SliderAdapter sliderAdapter) {
        mSliderAdapter = sliderAdapter;
    }

    public ListProductsHomePageAdapter getLastProductsAdapter() {
        return mLastProductsAdapter;
    }

    public ListProductsHomePageAdapter getPopularityProductsAdapter() {
        return mPopularityProductsAdapter;
    }

    public ListProductsHomePageAdapter getRatingProductsAdapter() {
        return mRatingProductsAdapter;
    }

    public SliderAdapter getSliderAdapter() {
        return mSliderAdapter;
    }




}
