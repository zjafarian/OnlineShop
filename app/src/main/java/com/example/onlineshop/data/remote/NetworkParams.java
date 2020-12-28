package com.example.onlineshop.data.remote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class NetworkParams {

    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";

    public static final String USER_NAME = "zjafarian88";
    public static final String PASSWORD = "rVfdEYSWDFj9";

    public static final String CONSUMER_KEY = "ck_66caee3abb3e7a93e98b2f1e2b5bb4216d2eaee7";
    public static final String CONSUMER_SECRET = "cs_bc59f09cc045eda7134363273277b10ae155fc6f";
    public static final String API_KEY =
            "?consumer_key=" + CONSUMER_KEY + "&consumer_secret=" +CONSUMER_SECRET;
    public static final  String PRODUCT_PATH = "products";


   /* public static final Map<String,String> LIST_PRODUCT_OPTIONS = new HashMap<String, String>(){{



    }};*/





    /*@GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("search") String searchText, @Query("attribute")
            String attribute, @Query("attribute_term") String terms, @Query("per_page") int perPage,
                                    @Query("page") int numberOfPage, @Query("orderby") String baseOn,
                                    @Query("order") String order);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("search") String searchText, @Query("attribute")
            String attribute, @Query("attribute_term") String terms, @Query("per_page") int perPage,
                                    @Query("page") int numberOfPage);


    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("attribute") String attribute, @Query("attribute_term")
            String terms, @Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=100")
    Call<List<Product>> searchProducts(@Query("search") String searchText, @Query("per_page")
            int perPage, @Query("page") int numberOfPage);


    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("per_page") int perPage, @Query("page") int numberOfPage,
                                    @Query("orderby") String baseOn);

    ///// With Category

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("category") String category, @Query("search")
            String searchText, @Query("attribute") String attribute, @Query("attribute_term")
                                            String terms, @Query("per_page") int perPage, @Query("page") int numberOfPage,
                                    @Query("orderby") String baseOn, @Query("order") String order);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("category") String category, @Query("search")
            String searchText, @Query("attribute") String attribute, @Query("attribute_term")
                                            String terms, @Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("category") String category, @Query("per_page")
            int perPage, @Query("page") int numberOfPage);


    //// Special For Products

    @GET(BASE_URL + "products/{id}" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<Product> getProductById(@Path("id") String productId);


    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&on_sale=true")
    Call<List<Product>> getSaleProduct(@Query("per_page") int perPage, @Query("page") int numberOfPage);


    // Attributes //

    @GET(BASE_URL + "products/attributes" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=20")
    Call<List<FilterRepository.Attribute>> getAttributes();

    @GET(BASE_URL + "products/attributes/{id}/terms" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=20")
    Call<List<FilterRepository.Term>> getTerms(@Path("id") String id);

    // Categories //

    @GET("products" + "/categories" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&per_page=100")
    Call<List<Category>> getAllCategories();
*/






}
