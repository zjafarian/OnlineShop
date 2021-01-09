package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;
import com.example.onlineshop.view.fragment.ListProductsFragment;

public class ListProductsActivity extends SingleFragmentActivity {

    public static final String EXTRA_SELECT_LIST_PRODUCTS = "com.example.onlineshop.selectListProducts";

    public static Intent newIntent (Context context, String selectListsProducts) {
        Intent intent = new Intent(context, ListProductsActivity.class);
        intent.putExtra(EXTRA_SELECT_LIST_PRODUCTS,selectListsProducts);

        return intent;
    }


    @Override
    public Fragment createFragment() {
        String selectListProducts = getIntent().getStringExtra(EXTRA_SELECT_LIST_PRODUCTS);
        ListProductsFragment listProductsFragment =
                ListProductsFragment.newInstance(selectListProducts);

        return listProductsFragment;
    }
}