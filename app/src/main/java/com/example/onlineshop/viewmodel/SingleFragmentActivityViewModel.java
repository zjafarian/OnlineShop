package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.AndroidViewModel;

import com.example.onlineshop.R;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.view.fragment.HomePageFragment;

public class SingleFragmentActivityViewModel extends AndroidViewModel {

    public static final String FRAGMENT_TAG = "FragmentActivity";


    private boolean mDoubleBackPressToExit;
    private boolean mHomeButtonClicked;
    private boolean mCategoryButtonClicked;
    private boolean mShoppingCartClicked;
    private boolean mAccountClicked;


    public SingleFragmentActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean loadFragment(FragmentActivity activity, Fragment fragmentInput) {

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragmentInput, FRAGMENT_TAG)
                    .commit();
        } else {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragmentInput, FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();
        }

        return true;
    }

    public boolean isDoubleBackPressToExit() {
        return mDoubleBackPressToExit;
    }

    public void setDoubleBackPressToExit(boolean doubleBackPressToExit) {
        mDoubleBackPressToExit = doubleBackPressToExit;
    }

    public int resDrawableIcon(MenuItem item){
        switch (item.getItemId()){
            case R.id.home_navigation_menu:
               return isHomeButtonClicked() ? R.drawable.ic_home_full : R.drawable.ic_home;

            case R.id.category_navigation_menu:
             return isCategoryButtonClicked() ? R.drawable.ic_category_full : R.drawable.ic_category;

            case R.id.shopping_cart_navigation_menu:

                return isShoppingCartClicked()
                        ? R.drawable.ic_shopping_cart_full : R.drawable.ic_shopping_cart;

            case R.id.user_navigation_menu:
              return isAccountClicked() ? R.drawable.ic_user_full : R.drawable.ic_user;

            default:
                return 0;
        }

    }


    public void handlerDoubleBackPress() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mDoubleBackPressToExit = false;
            }
        }, 2000);
    }


  /*  public int returnId (@NonNull MenuItem item){
        switch (item.getItemId()){


        }

    }*/

    public boolean isHomeButtonClicked() {
        return mHomeButtonClicked;
    }

    public void setHomeButtonClicked(boolean homeButtonClicked) {
        mHomeButtonClicked = homeButtonClicked;
    }

    public boolean isCategoryButtonClicked() {
        return mCategoryButtonClicked;
    }

    public void setCategoryButtonClicked(boolean categoryButtonClicked) {
        mCategoryButtonClicked = categoryButtonClicked;
    }

    public boolean isShoppingCartClicked() {
        return mShoppingCartClicked;
    }

    public void setShoppingCartClicked(boolean shoppingCartClicked) {
        mShoppingCartClicked = shoppingCartClicked;
    }

    public boolean isAccountClicked() {
        return mAccountClicked;
    }

    public void setAccountClicked(boolean accountClicked) {
        mAccountClicked = accountClicked;
    }
}
