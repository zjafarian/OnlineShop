package com.example.onlineshop.data.network.remote.retrofit;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface CustomerService {

    @POST
    Call<Customer> createCustomer(@Url String address, @Body Customer customer,
                                  @Query("consumer_key") String consumerKey,
                                  @Query("consumer_secret") String consumerSecret);

    @GET("customers/{id}")
    Call<Customer> getCustomerById(@Path("id") int id,
                                   @Query("consumer_key") String consumerKey,
                                   @Query("consumer_secret") String consumerSecret);

    @GET("customers")
    Call<Customer> getCustomerByEmail(@Query("consumer_key") String consumerKey,
                                      @Query("consumer_secret") String consumerSecret,
                                      @Query("email") String email);


    @POST("orders")
    Call<Order> createOrder(@Query("consumer_key") String consumerKey,
                            @Query("consumer_secret") String consumerSecret,
                            @Body Order order);

    @GET("orders")
    Call<List<Order>> getOrdersByCustomer(@Query("consumer_key") String consumerKey,
                                          @Query("consumer_secret") String consumerSecret,
                                          @Query("customer") int idCustomer);


    @GET("customers")
    Call<List<Customer>> getAllCustomer(@Query("consumer_key") String consumerKey,
                                        @Query("consumer_secret") String consumerSecret,
                                        @Query("orderby") String orderBy,
                                        @Query("order") String order,
                                        @Query("per_page") String perPage);
}
