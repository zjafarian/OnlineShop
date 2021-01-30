package com.example.onlineshop.data.network.remote.retrofit;


import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Customer;
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
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ShopService {

    @GET(NetworkParams.PRODUCT_PATH + NetworkParams.API_KEY)
    Call<List<Products>> getProducts(@QueryMap Map<String, String> options);

    @GET(NetworkParams.PRODUCT_PATH + NetworkParams.CATEGORY_PATH + NetworkParams.API_KEY)
    Call<List<Categories>> getCategories();

    @POST
    Call<Customer> createCustomer(@Url String address, @Body Customer customer,
                                  @Query("consumer_key") String consumerKey,
                                  @Query("consumer_secret") String consumerSecret);

    @GET("customers")
    Call<List<Customer>> getCustomers(@Query("consumer_key") String consumerKey,
                                      @Query("consumer_secret") String consumerSecret,
                                      @Query("order") String order,
                                      @Query("per_page") String perPage,
                                      @Query("orderby") String orderBy);

}
