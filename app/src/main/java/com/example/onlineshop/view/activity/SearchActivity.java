package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.view.fragment.ListProductsFragment;
import com.example.onlineshop.view.fragment.SearchFragment;

public class SearchActivity extends SingleFragmentActivity {

    public static final String EXTRA_PAGE_NAME = "com.example.onlineshop.pageName";
    public static final String EXTRA_SEARCH_TEXT = "com.example.onlineshop.searchText";
    public static final String EXTRA_CATEGORY_ID = "com.example.onlineshop.categoryId";

    public static Intent newIntent(Context context, String pageName, String searchText, int categoryId) {
        Intent intent = new Intent(context, ListProductsActivity.class);
        intent.putExtra(EXTRA_PAGE_NAME, pageName);
        intent.putExtra(EXTRA_SEARCH_TEXT, searchText);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryId);


        return intent;
    }


    @Override
    public Fragment createFragment() {
        String pageName = getIntent().getStringExtra(EXTRA_PAGE_NAME);
        String searchText = getIntent().getStringExtra(EXTRA_SEARCH_TEXT);
        int categoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID,0);
        SearchFragment searchFragment = SearchFragment.newInstance(pageName, searchText,categoryId);
        return searchFragment;
    }
}