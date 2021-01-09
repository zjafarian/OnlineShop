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
            "?consumer_key=" + CONSUMER_KEY + "&consumer_secret=" + CONSUMER_SECRET;

    public static final String POPULARITY = "popularity";
    public static final String RATING = "rating";
    public static final String LAST ="last";
    public static final String ORDER_STATUS = "desc";


    //path for get products
    public static final String PRODUCT_PATH = "products";

    //options for query get last products
    public static final Map<String, String> LAST_PRODUCTS  = new HashMap<String, String>() {{
        put("order", ORDER_STATUS);
    }};

    //options for query get popularity products
    public static final Map<String, String> POPULARITY_PRODUCTS  = new HashMap<String, String>() {{
        put("order", ORDER_STATUS);
        put("orderby",POPULARITY);
    }};

    //options for query get rating products
    public static final Map<String, String> RATING_PRODUCTS  = new HashMap<String, String>() {{
        put("order", ORDER_STATUS);
        put("orderby",RATING);
    }};


}
