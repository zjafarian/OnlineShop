package com.example.onlineshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesOnlineShop {

    private static final String STATUS_LOGIN = "statusLogin";
    private static final String USER_NAME_CUSTOMER = "userNameCustomer";
    private static final String PASSWORD_CUSTOMER = "passwordCustomer";


    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static void setStatusLogin(Context context, boolean status) {
        getSharedPreferences(context)
                .edit()
                .putBoolean(STATUS_LOGIN, status)
                .apply();
    }

    public static boolean getStatusLogin(Context context) {
        return getSharedPreferences(context).getBoolean(STATUS_LOGIN, false);
    }

    public static void setUserNameCustomer(Context context, String username) {
        getSharedPreferences(context)
                .edit()
                .putString(USER_NAME_CUSTOMER, username)
                .apply();
    }

    public static String getUserNameCustomer(Context context) {
        return getSharedPreferences(context).getString(USER_NAME_CUSTOMER, null);
    }

    public static void setPasswordCustomer(Context context, String password) {
        getSharedPreferences(context)
                .edit()
                .putString(PASSWORD_CUSTOMER, password)
                .apply();
    }

    public String getPasswordCustomer (Context context){
       return getSharedPreferences(context).getString(PASSWORD_CUSTOMER,null);
    }


}
