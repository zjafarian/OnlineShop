package com.example.onlineshop.view.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.onlineshop.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    public static final String FRAGMENT_TAG = "FragmentActivity";



    public abstract Fragment createFragment();

    @LayoutRes
    public abstract int getLayoutResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mBinding = DataBindingUtil.setContentView(this, getLayoutResId());


        setContentView(R.layout.activity_single_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, createFragment(), FRAGMENT_TAG)
                    .commit();
        } else {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, createFragment(), FRAGMENT_TAG)
                    .commit();
        }
    }
}
