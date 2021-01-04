package com.example.onlineshop.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivityOnlineShoppingFragmentBinding;
import com.example.onlineshop.view.fragment.AccountLoginFragment;
import com.example.onlineshop.view.fragment.CategoryFragment;
import com.example.onlineshop.view.fragment.HomePageFragment;
import com.example.onlineshop.view.fragment.LoginSignUpFragment;
import com.example.onlineshop.view.fragment.ShoppingFragment;
import com.example.onlineshop.viewmodel.SingleFragmentActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OnlineShoppingMainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {


    private ActivityOnlineShoppingFragmentBinding mBinding;
    private SingleFragmentActivityViewModel mViewModel;
    private boolean mLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SingleFragmentActivityViewModel.class);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_online_shopping_fragment);
        HomePageFragment homePageFragment = HomePageFragment.newInstance();
        mViewModel.loadFragment(this,homePageFragment);

        mViewModel.setDoubleBackPressToExit(false);
        mBinding.onlineShopNavigationBar.setOnNavigationItemSelectedListener
                (this::onNavigationItemSelected);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.home_navigation_menu:
                mViewModel.setHomeButtonClicked(!mViewModel.isHomeButtonClicked());
                HomePageFragment homePageFragment = HomePageFragment.newInstance();
                return mViewModel.loadFragment(this,homePageFragment);


            case R.id.category_navigation_menu:
                mViewModel.setCategoryButtonClicked(!mViewModel.isCategoryButtonClicked());
                CategoryFragment categoryFragment = CategoryFragment.newInstance();
                return mViewModel.loadFragment(this,categoryFragment);

            case R.id.shopping_cart_navigation_menu:
                mViewModel.setShoppingCartClicked(!mViewModel.isShoppingCartClicked());
                ShoppingFragment shoppingFragment = ShoppingFragment.newInstance();
                return mViewModel.loadFragment(this,shoppingFragment);
            case R.id.user_navigation_menu:

                mViewModel.setAccountClicked(!mViewModel.isAccountClicked());
                if (mLogin){
                    AccountLoginFragment accountLoginFragment = AccountLoginFragment.newInstance();
                    return mViewModel.loadFragment(this,accountLoginFragment);
                } else {
                    LoginSignUpFragment loginSignUpFragment = LoginSignUpFragment.newInstance();
                    return mViewModel.loadFragment(this,loginSignUpFragment);
                }

        }

        return false;




    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!mViewModel.isDoubleBackPressToExit()) {
            mViewModel.setDoubleBackPressToExit(true);
            Toast.makeText(this,R.string.exit_user_from_app,Toast.LENGTH_LONG).show();
            mViewModel.handlerDoubleBackPress();
        } else {
            super.onBackPressed();
            return;
        }
    }
}
