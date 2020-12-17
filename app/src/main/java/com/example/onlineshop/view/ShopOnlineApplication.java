package com.example.onlineshop.view;

import android.app.Application;

import com.example.onlineshop.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ShopOnlineApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //change default font android to IranSans Font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/iran_sans_mobile.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()

        );


    }


}
