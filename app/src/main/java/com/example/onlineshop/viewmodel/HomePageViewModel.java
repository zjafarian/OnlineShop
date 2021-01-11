package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListProductsHomePageAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.List;

public class HomePageViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;
    private Products mProduct;
    private LiveData<List<Products>> mLastProductsLiveData;
    private LiveData<List<Products>> mPopularityProductsLiveData;
    private LiveData<List<Products>> mRatingProductsLiveData;
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

    public LiveData<List<Products>> getLastProductsLiveData() {
        return mLastProductsLiveData;
    }

    public LiveData<List<Products>> getPopularityProductsLiveData() {
        return mPopularityProductsLiveData;
    }

    public LiveData<List<Products>> getRatingProductsLiveData() {
        return mRatingProductsLiveData;
    }


    public String getFirstImageSrc(Products products) {
        mProduct = products;
        if (products.getImages().size() != 0)
            return mProduct.getImages().get(0).getSrc();
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
