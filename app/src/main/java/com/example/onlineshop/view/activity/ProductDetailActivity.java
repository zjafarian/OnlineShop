package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;
import com.example.onlineshop.view.fragment.ListProductsFragment;
import com.example.onlineshop.view.fragment.ProductDetailFragment;

public class ProductDetailActivity extends SingleFragmentActivity {

    public static final String EXTRA_PRODUCT_ID = "com.example.onlineshop.productId";

    public static Intent newIntent (Context context, int productId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID,productId);

        return intent;
    }


    @Override
    public Fragment createFragment() {
        int productId = getIntent().getIntExtra(EXTRA_PRODUCT_ID,0);
        ProductDetailFragment productDetailFragment = ProductDetailFragment.newInstance(productId);
        return productDetailFragment;
    }
}