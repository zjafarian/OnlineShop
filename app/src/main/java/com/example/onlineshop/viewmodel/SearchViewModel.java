package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.ShopRepository;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {
    private String mPageName;
    private String mSearchText;
    private String mSetTextHintSearch;
    private ShopRepository mShopRepository;
    private LiveData<List<Products>> mSearchProductsLiveData;


    public SearchViewModel(@NonNull Application application) {
        super(application);
        mShopRepository = ShopRepository.getInstance(application);
        mSearchProductsLiveData = mShopRepository.getSearchProducts();
    }


    public void setValues(String pageName,String searchText) {
        mPageName = pageName;
        mSearchText = searchText;

        setTextHint();
    }

    private void setTextHint() {
        if (!mPageName.equals("") && mPageName.length() != 0) {
           switch (mPageName){
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
               case "homePage":
                   mSetTextHintSearch = "فروشگاه آنلاین";
                   break;
               default:
                   mSetTextHintSearch =mPageName;
                   break;
           }

        }
    }

    public String getSetTextHintSearch() {
        return mSetTextHintSearch;
    }


    public void fetchSearchQuery(String searchText){
        mShopRepository.searchProducts(mPageName,searchText);
        mSearchProductsLiveData = mShopRepository.getSearchProducts();
    }


    public LiveData<List<Products>> getSearchProductsLiveData() {
        return mSearchProductsLiveData;
    }
}
