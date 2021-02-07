package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.models.Review;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;
import com.example.onlineshop.view.activity.OnlineShoppingMainActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ProductDetailViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;
    private CustomerRepository mCustomerRepository;
    private LiveData<Products> mProductLiveData;
    private LiveData<List<Products>> mProductsShoppingCartLiveData;


    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mShopRepository = ShopRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance();
        Set<String> productsId = SharedPreferencesOnlineShop.getShoppingProducts(getApplication());
        if (productsId != null){

            if (mShopRepository.getProductsListShopping().size() == 0)
                getDataSavedByRequestServer(productsId);
            else  mShopRepository.setShoppingProductsLiveData();
        }
        mProductsShoppingCartLiveData = mShopRepository.getProductsShoppingCartLiveData();
        mProductLiveData = mShopRepository.getProductLiveData();
    }


    public void findProduct(int productId) {
        mShopRepository.getProductById(productId,"detail");
        mShopRepository.fetchListReviewByProductId(productId);
        mProductLiveData = mShopRepository.getProductLiveData();


       /* for (Products productFind : mProducts) {
            if (productFind.getId() == productId) {
                mProductLiveData.setValue(productFind);
                break;
            }
        }*/
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

    public void addProductToShoppingCart(Products products){
        mShopRepository.setProductsShopping(products);
    }

    public LiveData<List<Products>> getProductsShoppingCartLiveData() {
        return mProductsShoppingCartLiveData;
    }

    public void getDataSavedByRequestServer(Set<String> productsId) {
        List<Integer> intIds = new ArrayList<>();

        for (Iterator<String> ids = productsId.iterator(); ids.hasNext(); ) {
            String id = ids.next();
            intIds.add(Integer.valueOf(id));
        }
        for (int i = 0; i < intIds.size(); i++) {
            mShopRepository.getProductById((int) intIds.get(i),"splash");
        }
        mShopRepository.setShoppingProductsLiveData();
    }


}
