package com.example.onlineshop.data.network.remote.retrofit;


import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Coupon;
import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;

import com.example.onlineshop.data.network.remote.NetworkParams;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ShopService {

    @GET(NetworkParams.PRODUCT_PATH + NetworkParams.API_KEY)
    Call<List<Products>> getProducts(@QueryMap Map<String, String> options);

    @GET(NetworkParams.PRODUCT_PATH + NetworkParams.CATEGORY_PATH + NetworkParams.API_KEY)
    Call<List<Categories>> getCategories();

    @GET("products/{id}" + NetworkParams.API_KEY)
    Call<Products> getOneProduct(@Path("id") int id);

    @POST("coupons")
    Call<Coupon> createCoupon(@Query("consumer_key") String consumerKey,
                              @Query("consumer_secret") String consumerSecret,
                              @Body Coupon coupon);



}
