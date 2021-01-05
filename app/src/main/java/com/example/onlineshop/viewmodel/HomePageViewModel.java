package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class HomePageViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;
    private Product mProduct;
    private LiveData<List<Product>> mLastProductsLiveData;
    private LiveData<List<Product>> mPopularityProductsLiveData;
    private LiveData<List<Product>> mRatingProductsLiveData;




    public HomePageViewModel(@NonNull Application application) {
        super(application);
        List<Product> products;
        mShopRepository = new ShopRepository();
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

    public List<Product> getProductsList() {
        if (mLastProductsLiveData.getValue() != null)
            return mLastProductsLiveData.getValue();
        else return new ArrayList<>();
    }

    public String getFirstImageSrc(Product product) {
        mProduct = product;
        if (product.getProductPhotos().length != 0)
                return product.getProductPhotos()[0].getPhotoSrc();
        return null;
    }
}
