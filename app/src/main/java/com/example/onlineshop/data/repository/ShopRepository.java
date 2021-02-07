package com.example.onlineshop.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Coupon;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.models.Review;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.network.remote.retrofit.RetrofitInstance;
import com.example.onlineshop.data.network.remote.retrofit.ShopService;

import java.util.ArrayList;
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
    private MutableLiveData<List<Products>> mProductsShoppingCartLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Products>> mProductsOrderCustomer = new MutableLiveData<>();
    private List<Products> mProductsListShopping = new ArrayList<>();
    private MutableLiveData<Coupon> mCouponLiveData = new MutableLiveData<>();
    private MutableLiveData<Products> mProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Review>> mListReviewsLiveData = new MutableLiveData<>();
    private MutableLiveData<Review> mReviewLiveData = new MutableLiveData<>();
    private boolean mIsDelete;


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

    public void setProductsShopping(Products products) {
        mProductsListShopping.add(products);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mProductsShoppingCartLiveData.postValue(mProductsListShopping);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void removeProductAndLoadNewListProductsShoppingCart(Products products) {
        mProductsListShopping = mProductsShoppingCartLiveData.getValue();
        mProductsListShopping.remove(products);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mProductsShoppingCartLiveData.postValue(mProductsListShopping);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void removeAllProductsShoppingCart() {
        mProductsListShopping.clear();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mProductsShoppingCartLiveData.postValue(mProductsListShopping);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void setProductsByOrder(int[] productsId) {
        getAllProductsAsync();
        List<Products> products = mAllProductsLiveData.getValue();
        List<Products> productsOrder = new ArrayList<>();
        for (int i = 0; i < productsId.length; i++) {
            for (Products productFind : products) {
                if (productFind.getId() == productsId[i])
                    productsOrder.add(productFind);
            }
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mProductsOrderCustomer.postValue(productsOrder);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    public void getProductById(int id, String page) {

        Call<Products> call = mShopService.getOneProduct(id,
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET);

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                switch (page) {
                    case "splash":
                        mProductsListShopping.add(response.body());
                        break;
                    case "detail":
                        mProductLiveData.setValue(response.body());
                        break;
                }


            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });

    }

    public void clearShoppingList() {
        mProductsListShopping.clear();
    }

    public void setShoppingProductsLiveData() {
        if (mProductsListShopping.size() != 0 && mProductsListShopping != null)
            mProductsShoppingCartLiveData.setValue(mProductsListShopping);
    }

    public void fetchCreateCoupon() {
        Coupon coupon = new Coupon("code10",
                "20000",
                false,
                false,
                "10",
                "percent");

        Call<Coupon> call = mShopService.createCoupon(NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET,
                coupon);

        call.enqueue(new Callback<Coupon>() {
            @Override
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {
                mCouponLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Coupon> call, Throwable t) {

            }
        });


    }

    public void getCouponByCode(String code) {

        Call<Coupon> call = mShopService.getCouponByCode(NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET, code);

        call.enqueue(new Callback<Coupon>() {
            @Override
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {
                mCouponLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Coupon> call, Throwable t) {

            }
        });

    }

    public void fetchListReviewByProductId(int id) {
        Call<List<Review>> call = mShopService.getReviewsProduct(
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET,
                id);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                mListReviewsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
    }

    public void fetchCreateReview(Review review) {
        Call<Review> call = mShopService.createReview(
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET,
                review);

        call.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                mReviewLiveData.setValue(review);

            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {

            }
        });

    }

    public void fetchEditReview(Review review) {
        Call<Review> call = mShopService.editReview(review.getId(),
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET,
                review);
        call.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                mReviewLiveData.setValue(review);
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {

            }
        });


    }

    public void fetchDeleteReview(Review review) {
        Call<Review> call = mShopService.deleteReview(
                review.getId(),true,
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET);
        call.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
               if (response.isSuccessful())
                   mIsDelete = true;
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {

            }
        });

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

    public LiveData<List<Products>> getProductsShoppingCartLiveData() {
        return mProductsShoppingCartLiveData;
    }

    public LiveData<List<Products>> getProductsOrderCustomer() {
        return mProductsOrderCustomer;
    }

    public LiveData<Coupon> getCouponLiveData() {
        return mCouponLiveData;
    }

    public LiveData<Products> getProductLiveData() {
        return mProductLiveData;
    }

    public LiveData<List<Review>> getListReviewsLiveData() {
        return mListReviewsLiveData;
    }

    public List<Products> getProductsListShopping() {
        return mProductsListShopping;
    }

    public LiveData<Review> getReviewLiveData() {
        return mReviewLiveData;
    }

    public boolean isDelete() {
        return mIsDelete;
    }
}
