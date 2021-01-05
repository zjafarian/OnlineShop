package com.example.onlineshop.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.remote.NetworkParams;
import com.example.onlineshop.data.remote.retrofit.RetrofitInstance;
import com.example.onlineshop.data.remote.retrofit.ShopService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShopRepository {


    private ShopService mShopService;
    private static final String TAG = "ShopRepository";
    private MutableLiveData<List<Product>> mLastProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mPopularityProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mRatingProductsLiveData = new MutableLiveData<>();

    public ShopRepository() {
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofit();
        mShopService = retrofit.create(ShopService.class);
    }


    public void getLastProductsAsync() {

        Call<List<Product>> call = mShopService.getProducts(NetworkParams.LAST_PRODUCTS);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mLastProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

   public void getPopularityProductsAsync() {

        Call<List<Product>> call = mShopService.getProducts(NetworkParams.POPULARITY_PRODUCTS);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mPopularityProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }


    public void getRatingProductsAsync() {

        Call<List<Product>> call = mShopService.getProducts(NetworkParams.RATING_PRODUCTS);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mRatingProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }


  /*  public void getProductSync() {


        Call<List<Product>> call = mShopService.getProducts();
        try {
            Response<List<Product>> response = call.execute();


        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }


    }*/





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
