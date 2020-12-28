package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.adapter.LastProductsAdapter;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class HomePageViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;
    private Product mProduct;
    private LiveData<List<Product>> mProductsLiveData;


    public HomePageViewModel(@NonNull Application application) {
        super(application);
        mShopRepository = new ShopRepository();
        mProductsLiveData = mShopRepository.getProducts();

    }

    public void fetchProductsAsync() {
        mShopRepository.getProductsAsync();
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return mProductsLiveData;
    }

    public List<Product> getProductsList() {
        if (mProductsLiveData.getValue() != null)
            return mProductsLiveData.getValue();
        else return new ArrayList<>();
    }

    public String getFirstImageSrc(Product product) {
        mProduct = product;
        if (product.getProductPhotos().length != 0)
                return product.getProductPhotos()[0].getPhotoSrc();
        return null;
    }
}
