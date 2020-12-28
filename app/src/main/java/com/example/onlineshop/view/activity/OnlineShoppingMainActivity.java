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
import com.example.onlineshop.view.fragment.HomePageFragment;
import com.example.onlineshop.viewmodel.SingleFragmentActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OnlineShoppingMainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {


    private ActivityOnlineShoppingFragmentBinding mBinding;
    private SingleFragmentActivityViewModel mViewModel;


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
                mViewModel.setDoubleBackPressToExit(!mViewModel.isHomeButtonClicked());
                mViewModel.fetchProductsAsync();
                HomePageFragment homePageFragment = HomePageFragment.newInstance();
                return mViewModel.loadFragment(this,homePageFragment);


            case R.id.category_navigation_menu:
                //todo
               break;
            //todo
            case R.id.shopping_cart_navigation_menu:
                //todo
                break;
            case R.id.user_navigation_menu:
                //todo
               break;
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
