package com.example.onlineshop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.ArraySet;

import androidx.annotation.RequiresApi;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Products;

import java.util.List;
import java.util.Set;

public class SharedPreferencesOnlineShop {

    private static final String STATUS_LOGIN = "statusLogin";
    private static final String ID_CUSTOMER = "IdCustomer";
    private static final String PRODUCTS_IDS = "productsIds";


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

    public static void setCustomerId(Context context, int customerId) {
        getSharedPreferences(context)
                .edit()
                .putInt(ID_CUSTOMER, customerId)
                .apply();
    }

    public static int getCustomerId(Context context) {
        return getSharedPreferences(context).getInt(ID_CUSTOMER, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void setShoppingProducts(Context context, List<Products> products) {

        if (products.size() != 0 && products != null) {
            Set<String> set = new ArraySet<>();
            for (int i = 0; i < products.size(); i++) {
                set.add(String.valueOf(products.get(i).getId()));

            }
            getSharedPreferences(context)
                    .edit()
                    .putStringSet(PRODUCTS_IDS, set)
                    .apply();
        }


    }

    public static Set<String> getShoppingProducts(Context context) {
        return getSharedPreferences(context).getStringSet(PRODUCTS_IDS,null);
    }
}
