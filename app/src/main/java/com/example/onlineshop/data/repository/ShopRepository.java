package com.example.onlineshop.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.network.remote.retrofit.RetrofitInstance;
import com.example.onlineshop.data.network.remote.retrofit.ShopService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private MutableLiveData<List<Products>> mSortProductsByMaxPrice = new MutableLiveData<>();
    private MutableLiveData<List<Products>> mSortProductsByMinPrice = new MutableLiveData<>();
    private MutableLiveData<List<Products>> mSortProductsByTotalSale = new MutableLiveData<>();

    private Context mContext;


    private ShopRepository(Context context) {
        mContext = context.getApplicationContext();
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofit();
        mShopService = retrofit.create(ShopService.class);
    }

    public static ShopRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new ShopRepository(context);
        return sInstance;
    }

    public void getAllProductsAsync(){
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

    public void getCategoriesAsync(){
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

    public void sortProductsByMaxPrice(){
        Call<List<Products>> call = mShopService.getProducts
                (NetworkParams.SORT_PRODUCTS_BY_MAX_PRICE);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                mSortProductsByMaxPrice.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }

    public void sortProductsByMinPrice(){
        Call<List<Products>> call = mShopService.getProducts
                (NetworkParams.SORT_PRODUCTS_BY_MIN_PRICE);

        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                mSortProductsByMinPrice.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
    }

    public void sortProductsByTotalSale() {
        List<Products> products = mAllProductsLiveData.getValue();

        if (products != null){
            Collections.sort(products);
            mSortProductsByTotalSale.setValue(products);
        }

    }


  /*  public void getProductSync() {


        Call<List<Product>> call = mShopService.getProducts();
        try {
            Response<List<Product>> response = call.execute();


        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }


    }*/


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

    public MutableLiveData<List<Products>> getSortProductsByMaxPrice() {
        return mSortProductsByMaxPrice;
    }

    public MutableLiveData<List<Products>> getSortProductsByMinPrice() {
        return mSortProductsByMinPrice;
    }

    public MutableLiveData<List<Products>> getSortProductsByTotalSale() {
        return mSortProductsByTotalSale;
    }
}
