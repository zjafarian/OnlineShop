package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;
    private LiveData<List<Products>> mProductsLiveData;
    private LiveData<List<Products>> mRatingLiveData;
    private List<Products> mProducts = new ArrayList<>();
    private MutableLiveData<Products> mProductLiveData = new MutableLiveData<>();

    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mShopRepository = ShopRepository.getInstance(application);
        mProductsLiveData = mShopRepository.getAllProductsLiveData();
        mRatingLiveData = mShopRepository.getRatingProductsLiveData();
        mProducts = new ArrayList<Products>();
        mProducts = mProductsLiveData.getValue();
        mProducts.addAll(mRatingLiveData.getValue());

    }

    public void findProduct(int productId) {


        for (Products productFind : mProducts) {
            if (productFind.getId() == productId) {
                mProductLiveData.setValue(productFind);
                break;
            }
        }
    }

    public List<String> getImagesUriProduct() {
        List<String> imagesUri = new ArrayList<>();
        if (mProductLiveData.getValue().getImages().size() != 0 &&
                mProductLiveData.getValue().getImages() != null) {

            for (int i = 0; i < mProductLiveData.getValue().getImages().size(); i++) {
                imagesUri.add(mProductLiveData.getValue().getImages().get(i).getSrc());
            }
        }
        return imagesUri;
    }

    public LiveData<Products> getProductLiveData() {
        return mProductLiveData;
    }
}
