package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.onlineshop.adapter.ShoppingAdapter;

public class ShoppingViewModel extends AndroidViewModel {
    private ShoppingAdapter mShoppingAdapter;


    public ShoppingViewModel(@NonNull Application application) {
        super(application);
    }

    public ShoppingAdapter getShoppingAdapter() {
        return mShoppingAdapter;
    }

    public void setShoppingAdapter(ShoppingAdapter shoppingAdapter) {
        mShoppingAdapter = shoppingAdapter;
    }


    public void updateUI(){
        if (mShoppingAdapter!=null){
            mShoppingAdapter.notifyDataSetChanged();
        }
    }
}
