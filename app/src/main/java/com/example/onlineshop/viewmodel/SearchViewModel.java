package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.remote.NetworkParams;

public class SearchViewModel extends AndroidViewModel {
    private String mPageName;
    private String mSearchText;
    private String mSetTextHintSearch;


    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public String getPageName() {
        return mPageName;
    }

    public void setValues(String pageName) {
        mPageName = pageName;

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





}
