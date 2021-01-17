package com.example.onlineshop.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivitySingleFragmentBinding;
import com.example.onlineshop.view.fragment.HomePageFragment;
import com.example.onlineshop.viewmodel.SingleFragmentActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OnlineShoppingMainActivity extends SingleFragmentActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {


    private ActivitySingleFragmentBinding mBinding;
    private SingleFragmentActivityViewModel mViewModel;
    private boolean mLogin;
    private NavController mNavController;
    public static final String HOME_PAGE_FRAGMENT_TAG = "HomePageFragmentTag";


    @Override
    public Fragment createFragment() {

        getSupportFragmentManager().
                beginTransaction().
                add(R.id.fragment_container_navigation,
                        HomePageFragment.newInstance(),
                        HOME_PAGE_FRAGMENT_TAG);
        return HomePageFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SingleFragmentActivityViewModel.class);


        mViewModel.setDoubleBackPressToExit(false);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_single_fragment);

        mBinding.onlineShopNavigationBar.setOnNavigationItemSelectedListener
                (this::onNavigationItemSelected);

        mNavController = Navigation.findNavController(this,R.id.fragment_container_navigation);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        NavOptions optionBuilder=
                new NavOptions.Builder().
                        setEnterAnim(android.R.anim.fade_in).
                        setExitAnim(android.R.anim.fade_out).
                        setPopExitAnim(android.R.anim.slide_out_right).
                        build();


        switch (item.getItemId()) {
            case R.id.home_navigation_menu:
                mNavController.navigate(R.id.home_page_fragment_des,null,optionBuilder);
               return true;

            case R.id.category_navigation_menu:
                mNavController.navigate(R.id.category_fragment_des,null,optionBuilder);
                return true;

            case R.id.shopping_cart_navigation_menu:
                mNavController.navigate(R.id.shopping_fragment_des,null,optionBuilder);
                return true;

            case R.id.user_navigation_menu:
                mNavController.navigate(R.id.login_sign_up_fragment_des,null,optionBuilder);
                return true;
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
