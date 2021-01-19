package com.example.onlineshop.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.onlineshop.adapter.ListCategoriesAdapter;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.repository.ShopRepository;
import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;
    private LiveData<List<Categories>> mCategoryLiveData;


    public CategoryViewModel(@NonNull Application application) {
        super(application);
        mShopRepository = ShopRepository.getInstance(application);
        mCategoryLiveData = mShopRepository.getCategoriesListLiveData();
    }

    public LiveData<List<Categories>> getCategoryLiveData() {
        return mCategoryLiveData;
    }

}
