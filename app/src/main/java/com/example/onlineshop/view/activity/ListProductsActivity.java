package com.example.onlineshop.view.activity;


import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;


import com.example.onlineshop.view.fragment.ListProductsFragment;

public class ListProductsActivity extends SingleFragmentActivity {

    public static final String EXTRA_SELECT_LIST_PRODUCTS = "com.example.onlineshop.selectListProducts";
    public static final String EXTRA_CATEGORY_ID = "com.example.onlineshop.categoryId";
    public static final String EXTRA_ORDER_ID = "orderId";

    public static Intent newIntent (Context context, String selectListsProducts, int categoryId,int orderId) {
        Intent intent = new Intent(context, ListProductsActivity.class);
        intent.putExtra(EXTRA_SELECT_LIST_PRODUCTS,selectListsProducts);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryId);
        intent.putExtra(EXTRA_ORDER_ID, orderId );


        return intent;
    }


    @Override
    public Fragment createFragment() {
        String selectListProducts = getIntent().getStringExtra(EXTRA_SELECT_LIST_PRODUCTS);
        int categoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID,0);
        int orderId = getIntent().getIntExtra(EXTRA_ORDER_ID,0);
        ListProductsFragment listProductsFragment =
                ListProductsFragment.newInstance(selectListProducts,categoryId,orderId);

        return listProductsFragment;
    }
}