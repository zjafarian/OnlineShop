package com.example.onlineshop.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ActivitySingleFragmentBinding;
import com.example.onlineshop.viewmodel.SingleFragmentActivityViewModel;
import com.google.android.material.navigation.NavigationView;

public class OnlineShoppingMainActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, OnlineShoppingMainActivity.class);
        return intent;
    }


    private ActivitySingleFragmentBinding mBinding;
    private SingleFragmentActivityViewModel mViewModel;
    private boolean mLogin;
    private NavController mNavController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mViewModel = new ViewModelProvider(this).get(SingleFragmentActivityViewModel.class);
        mViewModel.setDoubleBackPressToExit(false);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_fragment);
        mBinding.setShowSplash(true);

        mNavController = Navigation.findNavController(this, R.id.fragment_container_navigation);

        NavigationUI.setupWithNavController(mBinding.onlineShopNavigationBar, mNavController);


    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!mViewModel.isDoubleBackPressToExit()) {
            mViewModel.setDoubleBackPressToExit(true);
            Toast.makeText(this, R.string.exit_user_from_app, Toast.LENGTH_LONG).show();
            mViewModel.handlerDoubleBackPress();
        } else {
            super.onBackPressed();
            return;
        }
    }
}
