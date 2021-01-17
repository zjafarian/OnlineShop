package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListCategoriesHomePageAdapter;
import com.example.onlineshop.adapter.ListProductsHomePageAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class HomePageViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;
    private Products mProduct;
    private LiveData<List<Products>> mLastProductsLiveData;
    private LiveData<List<Products>> mPopularityProductsLiveData;
    private LiveData<List<Products>> mRatingProductsLiveData;
    private LiveData<List<Categories>> mListCategoriesLiveData;
    private ListProductsHomePageAdapter mLastProductsAdapter;
    private ListProductsHomePageAdapter mPopularityProductsAdapter;
    private ListProductsHomePageAdapter mRatingProductsAdapter;
    private ListCategoriesHomePageAdapter mCategoriesHomePageAdapter;
    private SliderAdapter mSliderAdapter;
    private List<Integer> mColors = new ArrayList<Integer>(){{
        add(R.color.online_shop_red);
        add(R.color.online_shop_green);
        add(R.color.online_shop_blue);
        add(R.color.online_shop_purple);
        add(R.color.online_shop_mustard);
        add(R.color.online_shop_pink);
        add(R.color.online_shop_blue_two);
        add(R.color.online_shop_teal);
        add(R.color.online_shop_deep_purple);
        add(R.color.online_shop_blue_gray_two);
    }};



    public HomePageViewModel(@NonNull Application application) {
        super(application);

        mShopRepository = ShopRepository.getInstance(application);
        mLastProductsLiveData = mShopRepository.getLastProductsLiveData();
        mPopularityProductsLiveData = mShopRepository.getPopularityProductsLiveData();
        mRatingProductsLiveData = mShopRepository.getRatingProductsLiveData();
        mListCategoriesLiveData = mShopRepository.getCategoriesListLiveData();

        mSliderAdapter = new SliderAdapter(new ArrayList<String>() {{
            add(getUriImage(R.drawable.slider_one));
            add(getUriImage(R.drawable.slider_two));
            add(getUriImage(R.drawable.slider_three));
            add(getUriImage(R.drawable.slider_four));
        }});
    }

    public void fetchProductsAsync() {
        mShopRepository.getLastProductsAsync();
        mShopRepository.getPopularityProductsAsync();
        mShopRepository.getRatingProductsAsync();
        mShopRepository.getCategoriesAsync();
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

    public LiveData<List<Categories>> getListCategoriesLiveData() {
        return mListCategoriesLiveData;
    }

    public String getFirstImageSrc(Products products) {
        mProduct = products;
        if (products.getImages().size() != 0)
            return mProduct.getImages().get(0).getSrc();
        return null;
    }

    public String getUriImage(int idImage) {

        return Uri.parse("android.resource://" +
                R.class.getPackage().getName() +
                "/" +
                idImage).toString();
    }

    public void updateAdapters(String adapterName) {

        switch (adapterName) {
            case NetworkParams.LAST:
                mLastProductsAdapter.notifyDataSetChanged();
                break;

            case NetworkParams.POPULARITY:
                mPopularityProductsAdapter.notifyDataSetChanged();
                break;

            case NetworkParams.RATING:
                mRatingProductsAdapter.notifyDataSetChanged();
                break;

            case NetworkParams.CATEGORIES:
                mCategoriesHomePageAdapter.notifyDataSetChanged();
                break;
        }
    }

    public void setLastProductsAdapter(ListProductsHomePageAdapter lastProductsAdapter) {
        mLastProductsAdapter = lastProductsAdapter;
    }

    public void setPopularityProductsAdapter(ListProductsHomePageAdapter popularityProductsAdapter) {
        mPopularityProductsAdapter = popularityProductsAdapter;
    }

    public void setRatingProductsAdapter(ListProductsHomePageAdapter ratingProductsAdapter) {
        mRatingProductsAdapter = ratingProductsAdapter;
    }

    public void setSliderAdapter(SliderAdapter sliderAdapter) {
        mSliderAdapter = sliderAdapter;
    }

    public ListCategoriesHomePageAdapter getCategoriesHomePageAdapter() {
        return mCategoriesHomePageAdapter;
    }

    public void setCategoriesHomePageAdapter(ListCategoriesHomePageAdapter categoriesHomePageAdapter) {
        mCategoriesHomePageAdapter = categoriesHomePageAdapter;
    }

    public ListProductsHomePageAdapter getLastProductsAdapter() {
        return mLastProductsAdapter;
    }

    public ListProductsHomePageAdapter getPopularityProductsAdapter() {
        return mPopularityProductsAdapter;
    }

    public ListProductsHomePageAdapter getRatingProductsAdapter() {
        return mRatingProductsAdapter;
    }

    public List<Integer> getColors() {
        return mColors;
    }

    public SliderAdapter getSliderAdapter() {
        return mSliderAdapter;
    }




}
