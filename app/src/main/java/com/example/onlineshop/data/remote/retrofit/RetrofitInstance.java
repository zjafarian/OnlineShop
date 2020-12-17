package com.example.onlineshop.data.remote.retrofit;

import com.example.onlineshop.GetProductsItemDeserializer;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.data.remote.NetworkParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static RetrofitInstance sInstance;
    private final Retrofit mRetrofit;

    public static RetrofitInstance getInstance() {
        if (sInstance == null)
            sInstance = new RetrofitInstance();

        return sInstance;
    }

    private static Converter.Factory createGsonConverter (){
        Type type = new TypeToken<List<Product>>() {
        }.getType();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(type, new GetProductsItemDeserializer());
        Gson gson = gsonBuilder.create();

        return GsonConverterFactory.create(gson);

    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    private RetrofitInstance() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkParams.BASE_URL)
                .addConverterFactory(createGsonConverter())
                .build();
    }
}
