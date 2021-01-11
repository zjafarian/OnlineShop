package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.adapter.ListProductsAdapter;

import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.List;

public class ListProductsViewModel extends AndroidViewModel {
    private ListProductsAdapter mListProductAdapter;
    private String mSelectListProducts;
    private List<Products> mProductList;
    private ShopRepository mShopRepository;
    private Products mProduct;
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

    public ListProductsAdapter getListProductAdapter() {
        return mListProductAdapter;
    }

    public void setListProductAdapter(ListProductsAdapter listProductAdapter) {
        mListProductAdapter = listProductAdapter;
    }

    public String getSelectListProducts() {
        return mSelectListProducts;
    }

    public void setSelectListProducts(String selectListProducts) {
        mSelectListProducts = selectListProducts;
        setProducts();

    }

    private void setProducts() {
        switch (mSelectListProducts){
            case NetworkParams.LAST:
                mProductList = mShopRepository.getLastProductsLiveData().getValue();
                break;
            case NetworkParams.POPULARITY:
                mProductList = mShopRepository.getPopularityProductsLiveData().getValue();
                break;
            case NetworkParams.RATING:
                mProductList = mShopRepository.getRatingProductsLiveData().getValue();
                break;
        }

    }


    public List<Products> getProductList() {
        return mProductList;
    }

    public void notifyAdapter(){

        mListProductAdapter.notifyDataSetChanged();
    }

    public String getFirstImageSrc(Products product) {
        mProduct = product;
        if (product.getImages().size() != 0)
            return mProduct.getImages().get(0).getSrc();
        else return null;
    }

    public String getProductName(int position){
        return mProductList.get(position).getName();
    }

    public String getProductPrice(int position){
        return mProductList.get(position).getPrice();
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
}
