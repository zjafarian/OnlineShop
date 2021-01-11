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
    private ListCategoriesAdapter mCategoriesAdapter;
    private Categories mCategories;


    public CategoryViewModel(@NonNull Application application) {
        super(application);
        mShopRepository = ShopRepository.getInstance(application);
        mCategoryLiveData = mShopRepository.getCategoriesListLiveData();
    }


    public void fetchCategoriesAsync() {
        mShopRepository.getCategoriesAsync();

    }

    public LiveData<List<Categories>> getCategoryLiveData() {
        return mCategoryLiveData;
    }

    public ListCategoriesAdapter getCategoriesAdapter() {
        return mCategoriesAdapter;
    }

    public void setCategoriesAdapter(ListCategoriesAdapter categoriesAdapter) {
        mCategoriesAdapter = categoriesAdapter;
    }

    public void notifyAdapter(){
        mCategoriesAdapter.notifyDataSetChanged();
    }

    public String getImageSrc(Categories categories) {
        mCategories = categories;
        if (categories.getImage() != null)
            return categories.getImage().getSrc();
        return null;
    }
}
