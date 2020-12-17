package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;

import com.example.onlineshop.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomePageActivity extends SingleFragmentActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public Fragment createFragment() {
        return null;
    }

    @Override
    public int getLayoutResId() {
        return 0;
    }
}