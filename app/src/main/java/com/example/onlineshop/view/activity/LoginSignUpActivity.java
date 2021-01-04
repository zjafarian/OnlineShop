package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.view.fragment.LoginSignUpFragment;
import com.example.onlineshop.viewmodel.SingleFragmentActivityViewModel;

public class LoginSignUpActivity extends SingleFragmentActivity {

    public static Intent newIntent (Context context) {
        Intent intent = new Intent(context, LoginSignUpActivity.class);

        return intent;
    }




    @Override
    public Fragment createFragment() {
        LoginSignUpFragment loginSignUpFragment = LoginSignUpFragment.newInstance();
        return loginSignUpFragment;
    }
}