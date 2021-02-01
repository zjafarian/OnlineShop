package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class ListProductsViewModel extends ViewModel {

    private String mSelectListProducts;
    private ShopRepository mShopRepository;
    private CustomerRepository mCustomerRepository;
    private LiveData<List<Products>> mLastProductsLiveData;
    private LiveData<List<Products>> mPopularityProductsLiveData;
    private LiveData<List<Products>> mRatingProductsLiveData;
    private LiveData<List<Products>> mSelectProductsShow = new MutableLiveData<>();
    private LiveData<List<Categories>> mCategoriesLiveData;
    private LiveData<List<Products>> mSortProducts;
    private LiveData<List<Order>> mOrdersCustomerLiveData;
    private String mWhichList;
    private int mCategoryId;
    private String mCategoryName;
    private int mOrderId;


    public ListProductsViewModel() {

        mShopRepository = ShopRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance();


        mLastProductsLiveData = mShopRepository.getLastProductsLiveData();
        mPopularityProductsLiveData = mShopRepository.getPopularityProductsLiveData();
        mRatingProductsLiveData = mShopRepository.getRatingProductsLiveData();
        mCategoriesLiveData = mShopRepository.getCategoriesListLiveData();
        mSortProducts = mShopRepository.getSortProductsLiveData();
    }

    public void setOrderId(int orderId) {
        mOrderId = orderId;
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    public void setSelectListProducts(String selectListProducts) {
        mSelectListProducts = selectListProducts;
    }

    public void setProductsList() {
        switch (mSelectListProducts) {
            case NetworkParams.LAST:
                mWhichList = mSelectListProducts;
                mSelectProductsShow = new MutableLiveData<>();
                mSelectProductsShow = mLastProductsLiveData;
                break;
            case NetworkParams.POPULARITY:
                mWhichList = mSelectListProducts;
                mSelectProductsShow = new MutableLiveData<>();
                mSelectProductsShow = mPopularityProductsLiveData;
                break;
            case NetworkParams.RATING:
                mWhichList = mSelectListProducts;
                mSelectProductsShow = new MutableLiveData<>();
                mSelectProductsShow = mRatingProductsLiveData;
                break;
            case NetworkParams.CATEGORIES:
                mWhichList = findCategoryName();
                mCategoryName = findCategoryName();
                mSelectProductsShow = new MutableLiveData<>();
                mSelectProductsShow = findProductsByCategory();
                break;
            case NetworkParams.ORDER:
                mOrdersCustomerLiveData = mCustomerRepository.getOrdersCustomerLiveData();
                findProductsId();
                mSelectProductsShow = new MutableLiveData<>();
                mSelectProductsShow = mShopRepository.getProductsOrderCustomer();
                break;
        }

    }

    private void findProductsId() {
        for (Order order : mOrdersCustomerLiveData.getValue()) {
            if (order.getId() == mOrderId){
                int[] idProducts = new int[order.getLineItems().size()];
                for (int i = 0; i <order.getLineItems().size() ; i++) {
                    idProducts[i] = order.getLineItems().get(i).getProductId();
                }
                mShopRepository.setProductsByOrder(idProducts);
            }

        }
    }

    private LiveData<List<Products>> findProductsByCategory() {
        mShopRepository.getProductsByCategoryAsync(mCategoryId);
        return mShopRepository.getProductsByCategoryLiveData();
    }

    private String findCategoryName() {
        List<Categories> categories = mCategoriesLiveData.getValue();
        for (Categories category : categories) {
            if (category.getId() == mCategoryId) return category.getName();
        }
        return null;
    }


    public String getCategoryName() {
        return mCategoryName;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void fetchSortQuery(String sort) {
        mShopRepository.sortProductsAsync(mWhichList, sort, mCategoryId);
        mSortProducts = mShopRepository.getSortProductsLiveData();
    }

    public LiveData<List<Products>> getSelectProductsShow() {
        return mSelectProductsShow;
    }


    public LiveData<List<Products>> getSortProducts() {
        return mSortProducts;
    }

    public LiveData<List<Order>> getOrdersCustomerLiveData() {
        return mOrdersCustomerLiveData;
    }


}
