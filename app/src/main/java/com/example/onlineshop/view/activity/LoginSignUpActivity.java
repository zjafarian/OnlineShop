package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.view.fragment.LoginSignUpFragment;
import com.example.onlineshop.viewmodel.SingleFragmentActivityViewModel;

public class LoginSignUpActivity extends SingleFragmentActivity {

    public static final String EXTRA_WHICH_PAGE = "com.example.onlineshop.whichPage";

    public static Intent newIntent(Context context, String whichPage) {
        Intent intent = new Intent(context, LoginSignUpActivity.class);
        intent.putExtra(EXTRA_WHICH_PAGE, whichPage);

        return intent;
    }


    @Override
    public Fragment createFragment() {
        String whichPage = getIntent().getStringExtra(EXTRA_WHICH_PAGE);
        LoginSignUpFragment loginSignUpFragment = LoginSignUpFragment.newInstance(whichPage);
        return loginSignUpFragment;
    }
}