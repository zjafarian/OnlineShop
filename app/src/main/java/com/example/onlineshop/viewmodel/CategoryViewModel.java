package com.example.onlineshop.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.adapter.ListCategoriesAdapter;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.repository.ShopRepository;
import java.util.List;

public class CategoryViewModel extends ViewModel {
    private ShopRepository mShopRepository;
    private LiveData<List<Categories>> mCategoryLiveData;


    public CategoryViewModel() {

        mShopRepository = ShopRepository.getInstance();
        mCategoryLiveData = mShopRepository.getCategoriesListLiveData();
    }

    public LiveData<List<Categories>> getCategoryLiveData() {
        return mCategoryLiveData;
    }

}
