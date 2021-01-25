package com.example.onlineshop.data.network.remote.retrofit;

import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ShopService {

    @GET(NetworkParams.PRODUCT_PATH + NetworkParams.API_KEY)
    Call<List<Products>> getProducts(@QueryMap Map<String, String> options);

    @GET(NetworkParams.PRODUCT_PATH  + NetworkParams.CATEGORY_PATH + NetworkParams.API_KEY)
    Call<List<Categories>> getCategories();


    @POST("/customers" + NetworkParams.API_KEY)
    Call<Customer> createCustomer(@Header("Content-Type:application/json") @Body Customer customer);


}
