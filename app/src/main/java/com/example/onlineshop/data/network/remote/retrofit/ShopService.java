package com.example.onlineshop.data.network.remote.retrofit;


import androidx.room.Delete;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Coupon;
import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;

import com.example.onlineshop.data.network.models.Review;
import com.example.onlineshop.data.network.remote.NetworkParams;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ShopService {

    @GET(NetworkParams.PRODUCT_PATH + NetworkParams.API_KEY)
    Call<List<Products>> getProducts(@QueryMap Map<String, String> options);

    @GET(NetworkParams.PRODUCT_PATH + NetworkParams.CATEGORY_PATH + NetworkParams.API_KEY)
    Call<List<Categories>> getCategories();

    @GET("products/{id}")
    Call<Products> getOneProduct(@Path("id") int id,
                                 @Query("consumer_key") String consumerKey,
                                 @Query("consumer_secret") String consumerSecret);

    @POST("coupons")
    Call<Coupon> createCoupon(@Query("consumer_key") String consumerKey,
                              @Query("consumer_secret") String consumerSecret,
                              @Body Coupon coupon);

    @GET("coupons")
    Call<Coupon> getCouponByCode(@Query("consumer_key") String consumerKey,
                                 @Query("consumer_secret") String consumerSecret,
                                 @Query("search") String code);

    @GET("products/reviews")
    Call<List<Review>> getReviewsProduct(@Query("consumer_key") String consumerKey,
                                         @Query("consumer_secret") String consumerSecret,
                                         @Query("product") int id);


    @POST("products/reviews")
    Call<Review> createReview(@Query("consumer_key") String consumerKey,
                              @Query("consumer_secret") String consumerSecret,
                              @Body Review review);


    @PUT("products/reviews/{id}")
    Call<Review> editReview(@Path("id") int id,
                            @Query("consumer_key") String consumerKey,
                            @Query("consumer_secret") String consumerSecret,
                            @Body Review review);

    @DELETE("products/review/{id}")
    Call<Review> deleteReview(@Path("id") int id,
                              @Query("force") boolean status,
                              @Query("consumer_key") String consumerKey,
                              @Query("consumer_secret") String consumerSecret);


}
