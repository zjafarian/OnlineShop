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



    public SingleFragmentActivityViewModel(@NonNull Application application) {
        super(application);
    }


    public boolean isDoubleBackPressToExit() {
        return mDoubleBackPressToExit;
    }

    public void setDoubleBackPressToExit(boolean doubleBackPressToExit) {
        mDoubleBackPressToExit = doubleBackPressToExit;
    }




    public void handlerDoubleBackPress() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mDoubleBackPressToExit = false;
            }
        }, 2000);
    }





}
