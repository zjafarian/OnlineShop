package com.example.onlineshop.data.network.remote;

import java.util.HashMap;
import java.util.Map;

public class NetworkParams {

    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";



    public static final String CONSUMER_KEY = "ck_66caee3abb3e7a93e98b2f1e2b5bb4216d2eaee7";
    public static final String CONSUMER_SECRET = "cs_bc59f09cc045eda7134363273277b10ae155fc6f";
    public static final String API_KEY =
            "?consumer_key=" + CONSUMER_KEY + "&consumer_secret=" + CONSUMER_SECRET;

    public static final String POPULARITY = "popularity";
    public static final String RATING = "rating";
    public static final String CATEGORIES = "categories";
    public static final String ORDER ="order";
    public static final String LAST = "last";
    public static final String ORDER_STATUS_DESC = "desc";
    public static final String ORDER_STATUS_ASC = "asc";
    public static final String PER_PAGE="40";
    public static final String AllProducts= "allProducts";
    public static final String DATE="date";
    public static final String DATE_CUSTOMER="registered_date";
    public static final String COUPON_CODE = "code10";





    //path for get products
    public static final String PRODUCT_PATH = "products";
    public static final String CATEGORY_PATH = "/categories";

    //options for query get ALL products
    public static final Map<String, String> ALL_PRODUCTS = new HashMap<String, String>() {{
        put("per_page",PER_PAGE);
    }};


    //options for query get last products
    public static final Map<String, String> LAST_PRODUCTS = new HashMap<String, String>() {{
        put("orderby",DATE);
        put("per_page",PER_PAGE);
    }};

    //options for query get popularity products
    public static final Map<String, String> POPULARITY_PRODUCTS = new HashMap<String, String>() {{
        put("order", ORDER_STATUS_DESC);
        put("orderby", POPULARITY);
        put("per_page",PER_PAGE);
    }};

    //options for query get rating products
    public static final Map<String, String> RATING_PRODUCTS = new HashMap<String, String>() {{
        put("order", ORDER_STATUS_DESC);
        put("orderby", RATING);
        put("per_page",PER_PAGE);
    }};














}
