package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.data.network.models.Categories;
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
    private LiveData<List<Products>> mAllProductsLiveData;
    private List<Products> mAllProducts;
    private int mCategoryId;


    public ListProductsViewModel(@NonNull Application application) {
        super(application);
        mShopRepository = ShopRepository.getInstance(application);
        mLastProductsLiveData = mShopRepository.getLastProductsLiveData();
        mPopularityProductsLiveData = mShopRepository.getPopularityProductsLiveData();
        mRatingProductsLiveData = mShopRepository.getRatingProductsLiveData();
        mAllProductsLiveData = mShopRepository.getAllProductsLiveData();
        mAllProducts = mAllProductsLiveData.getValue();
        mAllProducts.addAll(mRatingProductsLiveData.getValue());

    }


    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    public void setSelectListProducts(String selectListProducts) {
        mSelectListProducts = selectListProducts;
        setProductsList();
    }

    private void setProductsList() {
        switch (mSelectListProducts){
            case NetworkParams.LAST:
                mProductList = new ArrayList<>();
                mProductList = mLastProductsLiveData.getValue();
                break;
            case NetworkParams.POPULARITY:
                mProductList = new ArrayList<>();
                mProductList =mPopularityProductsLiveData.getValue();
                break;
            case NetworkParams.RATING:
                mProductList = new ArrayList<>();
                mProductList = mRatingProductsLiveData.getValue();
                break;
            case NetworkParams.CATEGORIES:
                mProductList = new ArrayList<>();
                mProductList = findProductsByCategory();
                break;
        }

    }

    private List<Products> findProductsByCategory() {
        List<Products> productsPerCategory = new ArrayList<>();



        if (mCategoryId != 0){
            for (Products productFind: mAllProducts) {
                for (Categories categoryFind: productFind.getCategories()) {
                    if (categoryFind.getId() == mCategoryId)
                        productsPerCategory.add(productFind);
                }
            }
        }
        return productsPerCategory;
    }

    public List<Products> getProductList() {
        return mProductList;
    }

}
