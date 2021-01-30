package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.List;

public class SearchViewModel extends ViewModel {
    private String mWhichList;
    private String mSearchText;
    private String mSetTextHintSearch;
    private int mCategoryId;
    private ShopRepository mShopRepository;
    private LiveData<List<Products>> mSearchProductsLiveData;


    public SearchViewModel() {
        mShopRepository = ShopRepository.getInstance();
        mSearchProductsLiveData = mShopRepository.getSearchProductsLiveData();
    }


    public void setValues(String pageName, String searchText, int categoryId) {
        mWhichList = pageName;
        mSearchText = searchText;
        mCategoryId = categoryId;
        setTextHint();
    }

    private void setTextHint() {
        if (!mWhichList.equals("") && mWhichList.length() != 0) {
            switch (mWhichList) {
                case NetworkParams.CATEGORIES:
                    mSetTextHintSearch = "فروشگاه آنلاین";
                    break;
                case NetworkParams.RATING:
                    mSetTextHintSearch = "بهترین محصولات";
                    break;
                case NetworkParams.LAST:
                    mSetTextHintSearch = "آخرین محصولات";
                    break;
                case NetworkParams.POPULARITY:
                    mSetTextHintSearch = "پربازدیدترین محصولات";
                    break;
                case NetworkParams.AllProducts:
                    mSetTextHintSearch = "فروشگاه آنلاین";
                    break;
                default:
                    mSetTextHintSearch = mWhichList;
                    break;
            }

        }
    }

    public String getSetTextHintSearch() {
        return mSetTextHintSearch;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void fetchSearchQuery(String searchText, String sort) {
        if (searchText.length() == 0 || searchText.equals("") || searchText == null)
            searchText = mSearchText;

        if (sort == null)
            sort = "";


        mShopRepository.searchProductAndSortAsync(mWhichList, searchText, sort, mCategoryId);
        mSearchProductsLiveData = mShopRepository.getSearchProductsLiveData();
    }


    public LiveData<List<Products>> getSearchProductsLiveData() {
        return mSearchProductsLiveData;
    }
}
