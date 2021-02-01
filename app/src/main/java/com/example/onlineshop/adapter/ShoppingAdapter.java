package com.example.onlineshop.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.onlineshop.view.fragment.ShoppingCartFragment;
import com.example.onlineshop.view.fragment.ShoppingListProductsFragment;


import java.util.ArrayList;
import java.util.List;

public class ShoppingAdapter extends FragmentStateAdapter {
    private boolean mLogin = false;

    private List<String> mShoppingTabs = new ArrayList<String>() {{
        add("سبد خرید");
        add("لیست خریدها");
    }};


    public ShoppingAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                ShoppingCartFragment shoppingCartLoginFragment =
                        ShoppingCartFragment.newInstance(null);
                return shoppingCartLoginFragment;
            case 1:

                ShoppingListProductsFragment shoppingListLoginFragment =
                        ShoppingListProductsFragment.newInstance();
                return shoppingListLoginFragment;

        }


        return null;
    }

    @Override
    public int getItemCount() {
        return mShoppingTabs.size();
    }


}
