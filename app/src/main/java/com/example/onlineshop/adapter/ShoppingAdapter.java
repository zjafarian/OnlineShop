package com.example.onlineshop.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.onlineshop.R;
import com.example.onlineshop.view.fragment.ShoppingCartLoginFragment;
import com.example.onlineshop.view.fragment.ShoppingCartWithoutLoginFragment;
import com.example.onlineshop.view.fragment.ShoppingListLoginFragment;
import com.example.onlineshop.view.fragment.ShoppingListWithoutLoginFragment;

import java.util.ArrayList;
import java.util.List;

public class ShoppingAdapter extends FragmentStateAdapter {
    private boolean mLogin=false;

    private List<String> mShoppingTabs = new ArrayList<String>(){{
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
                if (mLogin){
                    ShoppingCartLoginFragment shoppingCartLoginFragment =
                            ShoppingCartLoginFragment.newInstance();
                    return shoppingCartLoginFragment;
                } else {
                    ShoppingCartWithoutLoginFragment shoppingCartWithoutLoginFragment =
                            ShoppingCartWithoutLoginFragment.newInstance();
                    return shoppingCartWithoutLoginFragment;
                }

            case 1:
                if (mLogin){
                    ShoppingListLoginFragment shoppingListLoginFragment =
                            ShoppingListLoginFragment.newInstance();

                    return shoppingListLoginFragment;
                } else {
                    ShoppingListWithoutLoginFragment shoppingListWithoutLoginFragment =
                            ShoppingListWithoutLoginFragment.newInstance();

                    return shoppingListWithoutLoginFragment;
                }

            default:
                break;
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return mShoppingTabs.size();
    }


}
