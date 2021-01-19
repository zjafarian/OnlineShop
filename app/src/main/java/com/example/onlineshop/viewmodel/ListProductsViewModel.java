package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.adapter.ListProductsAdapter;

import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class ListProductsViewModel extends AndroidViewModel {

    private String mSelectListProducts;
    private List<Products> mProductList=new ArrayList<>();
    private ShopRepository mShopRepository;
    private LiveData<List<Products>> mLastProductsLiveData;
    private LiveData<List<Products>> mPopularityProductsLiveData;
    private LiveData<List<Products>> mRatingProductsLiveData;


    public ListProductsViewModel(@NonNull Application application) {
        super(application);
        mShopRepository = ShopRepository.getInstance(application);
        mLastProductsLiveData = mShopRepository.getLastProductsLiveData();
        mPopularityProductsLiveData = mShopRepository.getPopularityProductsLiveData();
        mRatingProductsLiveData = mShopRepository.getRatingProductsLiveData();
    }



    public void setSelectListProducts(String selectListProducts) {
        mSelectListProducts = selectListProducts;
        setProducts();

    }

    private void setProducts() {
        switch (mSelectListProducts){
            case NetworkParams.LAST:
                mProductList = mLastProductsLiveData.getValue();
                break;
            case NetworkParams.POPULARITY:
                mProductList =mPopularityProductsLiveData.getValue();
                break;
            case NetworkParams.RATING:
                mProductList = mRatingProductsLiveData.getValue();
                break;
        }

    }

    public List<Products> getProductList() {
        return mProductList;
    }

}
