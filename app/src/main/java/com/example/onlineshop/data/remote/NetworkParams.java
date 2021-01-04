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


    //path for get products
    public static final  String PRODUCT_PATH = "products";

    //







}
