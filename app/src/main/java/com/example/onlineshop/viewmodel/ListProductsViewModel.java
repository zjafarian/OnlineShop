package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListProductsAdapter;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListProductsViewModel extends AndroidViewModel {
    private ListProductsAdapter mListProductAdapter;
    private String mSelectListProducts;
    private List<Product> mProductList;
    private ShopRepository mShopRepository;
    private Product mProduct;
    private LiveData<List<Product>> mLastProductsLiveData;
    private LiveData<List<Product>> mPopularityProductsLiveData;
    private LiveData<List<Product>> mRatingProductsLiveData;


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


    public List<Product> getProductList() {
        return mProductList;
    }

    public void notifyAdapter(){

        mListProductAdapter.notifyDataSetChanged();
    }

    public String getFirstImageSrc(Product product) {
        mProduct = product;
        if (product.getProductPhotos().length != 0)
            return product.getProductPhotos()[0].getPhotoSrc();
        else return null;
    }

    public String getProductName(int position){
        return mProductList.get(position).getNameProduct();
    }

    public String getProductPrice(int position){
        return mProductList.get(position).getPriceProduct();
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
}
