package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.onlineshop.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    public static final String FRAGMENT_TAG = "FragmentActivity";


    public abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_navigation);


        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container_navigation, createFragment(), FRAGMENT_TAG)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container_navigation, createFragment(), FRAGMENT_TAG)
                    .commit();
        }
    }


}