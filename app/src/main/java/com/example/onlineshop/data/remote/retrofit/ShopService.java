package com.example.onlineshop.data.remote.retrofit;

import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.remote.NetworkParams;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShopService {

    @GET(NetworkParams.PRODUCT_PATH + NetworkParams.API_KEY)
    Call<List<Product>> getProducts ();

}
