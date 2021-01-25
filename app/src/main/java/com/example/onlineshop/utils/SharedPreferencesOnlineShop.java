package com.example.onlineshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesOnlineShop {
    private static final String PREF_SEARCH_QUERY = "searchQuery";

    public static void setSearchQuery(Context context, String query) {
        getSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_QUERY,query)
                .apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
       return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static String getSearchQuery(Context context){
        return getSharedPreferences(context).getString(PREF_SEARCH_QUERY,null);
    }
}
