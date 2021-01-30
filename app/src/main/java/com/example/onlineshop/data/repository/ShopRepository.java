package com.example.onlineshop.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.network.remote.retrofit.RetrofitInstance;
import com.example.onlineshop.data.network.remote.retrofit.ShopService;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShopRepository {

    private static ShopRepository sInstance;
    private ShopService mShopService;
    private static final String TAG = "ShopRepository";
    private MutableLiveData<List<Products>> mLastProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Products>> mPopularityProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Products>> mRatingProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Products>> mAllProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Categories>> mCategoriesListLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Products>> mSearchProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Products>> mProductsByCategoryLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Products>> mSortProductsLiveData = new MutableLiveData<>();



    private ShopRepository() {
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofit();
        mShopService = retrofit.create(ShopService.class);
    }

    public static ShopRepository getInstance() {
        if (sInstance == null)
            sInstance = new ShopRepository();
        return sInstance;
    }

    public void getAllProductsAsync() {
        Call<List<Products>> call = mShopService.getProducts(NetworkParams.ALL_PRODUCTS);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                mAllProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
    }

    public void getLastProductsAsync() {
        Call<List<Products>> call = mShopService.getProducts(NetworkParams.LAST_PRODUCTS);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                mLastProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });


    }

    public void getPopularityProductsAsync() {

        Call<List<Products>> call = mShopService.getProducts(NetworkParams.POPULARITY_PRODUCTS);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {

                mPopularityProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }

    public void getRatingProductsAsync() {

        Call<List<Products>> call = mShopService.getProducts(NetworkParams.RATING_PRODUCTS);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                mRatingProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }

    public void getCategoriesAsync() {
        Call<List<Categories>> call = mShopService.getCategories();
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                mCategoriesListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {

            }
        });
    }

    public void getProductsByCategoryAsync(int idCategory) {
        Map<String, String> options = NetworkParams.ALL_PRODUCTS;
        options.put("category", String.valueOf(idCategory));
        Call<List<Products>> call = mShopService.getProducts(options);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                mProductsByCategoryLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
    }

    public void searchProductAndSortAsync(String whichList, String search, String sort, int categoryId) {
        Map<String, String> options = null;
        switch (whichList) {
            case NetworkParams.LAST:
                options = new HashMap<>();
                options = NetworkParams.LAST_PRODUCTS;
                break;
            case NetworkParams.POPULARITY:
                options = new HashMap<>();
                options = NetworkParams.POPULARITY_PRODUCTS;
                break;
            case NetworkParams.RATING:
                options = new HashMap<>();
                options = NetworkParams.RATING_PRODUCTS;

                break;
            case NetworkParams.AllProducts:
                options = new HashMap<>();
                options = NetworkParams.ALL_PRODUCTS;
                break;
            default:
                options = new HashMap<>();
                options = NetworkParams.ALL_PRODUCTS;
                options.put("category", String.valueOf(categoryId));
                break;
        }
        if (search.length() != 0 && !search.equals("") && search != null) {
            options.put("search", search);
        }

        if (sort.length() != 0 && !sort.equals("") && sort != null) {
            switch (sort) {
                case "جدیدترین":
                    if (!options.containsKey("order"))
                        options.put("order", NetworkParams.ORDER_STATUS_DESC);
                    break;
                case "ارزان\u200Cترین":
                    options.put("order", NetworkParams.ORDER_STATUS_ASC);
                    options.put("orderby", "price");
                    options.put("price", "min_price");
                    break;
                case "گران\u200Cترین":
                    options.put("orderby", "price");
                    options.put("price", "max_price");
                    break;
            }
        }

        Call<List<Products>> call = mShopService.getProducts(options);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (sort.equals("پرفروش\u200Cترین")) {
                    if (response.body() != null && response.body().size() != 0) {
                        List<Products> products = response.body();
                        Collections.sort(products);
                        mSearchProductsLiveData.setValue(products);
                    }
                } else mSearchProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }

    public void sortProductsAsync(String whichList, String sort, int categoryId) {
        Map<String, String> options = null;
        switch (whichList) {
            case NetworkParams.LAST:
                options = new HashMap<>();
                options = NetworkParams.LAST_PRODUCTS;
                break;
            case NetworkParams.POPULARITY:
                options = new HashMap<>();
                options = NetworkParams.POPULARITY_PRODUCTS;
                break;
            case NetworkParams.RATING:
                options = new HashMap<>();
                options = NetworkParams.RATING_PRODUCTS;

                break;
            case NetworkParams.AllProducts:
                options = new HashMap<>();
                options = NetworkParams.ALL_PRODUCTS;
                break;
            default:
                options = new HashMap<>();
                options = NetworkParams.ALL_PRODUCTS;
                options.put("category", String.valueOf(categoryId));
                break;
        }

        if (sort.length() != 0 && !sort.equals("") && sort != null) {
            switch (sort) {
                case "جدیدترین":
                    if (!options.containsKey("order"))
                        options.put("order", NetworkParams.ORDER_STATUS_DESC);
                    break;
                case "ارزان\u200Cترین":
                    options.put("order", NetworkParams.ORDER_STATUS_ASC);
                    options.put("orderby", "price");
                    options.put("price", "min_price");
                    break;
                case "گران\u200Cترین":
                    options.put("orderby", "price");
                    options.put("price", "max_price");
                    break;
            }
        }

        Call<List<Products>> call = mShopService.getProducts(options);

        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (sort.equals("پرفروش\u200Cترین")) {
                    if (response.body() != null && response.body().size() != 0) {
                        List<Products> products = response.body();
                        Collections.sort(products);
                        mSortProductsLiveData.setValue(products);
                    }

                } else mSortProductsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });


    }


    @NotNull
    private String TAG() {
        return "shopRepository";
    }


    public LiveData<List<Products>> getLastProductsLiveData() {
        return mLastProductsLiveData;
    }

    public LiveData<List<Products>> getPopularityProductsLiveData() {
        return mPopularityProductsLiveData;
    }

    public LiveData<List<Products>> getRatingProductsLiveData() {
        return mRatingProductsLiveData;
    }

    public LiveData<List<Categories>> getCategoriesListLiveData() {
        return mCategoriesListLiveData;
    }

    public LiveData<List<Products>> getAllProductsLiveData() {
        return mAllProductsLiveData;
    }

    public LiveData<List<Products>> getSearchProductsLiveData() {
        return mSearchProductsLiveData;
    }

    public LiveData<List<Products>> getProductsByCategoryLiveData() {
        return mProductsByCategoryLiveData;
    }

    public LiveData<List<Products>> getSortProductsLiveData() {
        return mSortProductsLiveData;
    }


}
