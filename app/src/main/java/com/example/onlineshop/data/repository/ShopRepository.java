package com.example.onlineshop.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.remote.retrofit.RetrofitInstance;
import com.example.onlineshop.data.remote.retrofit.ShopService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShopRepository {


    private ShopService mShopService;
    private static final String TAG = "ShopRepository";
    private MutableLiveData<List<Product>> mProductsLiveData= new MutableLiveData<>();

    public ShopRepository() {
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofit();
        mShopService = retrofit.create(ShopService.class);
    }



    public void getProductsAsync() {

        Call<List<Product>> call = mShopService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    public void getProductSync(){
        Call<List<Product>> call = mShopService.getProducts();
        try {
            Response<List<Product>> response = call.execute();
            mProductsLiveData.postValue(response.body());



        }catch (IOException e){
            Log.d(TAG,e.getMessage());



        }

    }

    public MutableLiveData<List<Product>> getProducts() {
        return mProductsLiveData;
    }
}
