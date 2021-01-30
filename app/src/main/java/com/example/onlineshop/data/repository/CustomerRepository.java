package com.example.onlineshop.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.network.remote.retrofit.RetrofitInstance;
import com.example.onlineshop.data.network.remote.retrofit.ShopService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomerRepository {
    private static final String TAG = "CustomerRepository";
    private ShopService mShopService;
    private static CustomerRepository sInstance;
    private MutableLiveData<Customer> mCustomerLogin = new MutableLiveData<>();
    private MutableLiveData<List<Customer>> mCustomerListLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLogin = new MutableLiveData<>();
    private String mMessage;

    private CustomerRepository() {
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofit();
        mShopService = retrofit.create(ShopService.class);
    }

    public static CustomerRepository getInstance() {
        if (sInstance == null)
            sInstance = new CustomerRepository();

        return sInstance;
    }


    public void postCustomer(Customer customer) {

        Call<Customer> call =
                mShopService.createCustomer("https://woocommerce.maktabsharif.ir/wp-json/wc/v3/customers/",
                        customer,
                        NetworkParams.CONSUMER_KEY,
                        NetworkParams.CONSUMER_SECRET);

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful())
                    mCustomerLogin.setValue(response.body());
                else Log.d(TAG, response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                mMessage = t.getMessage();
            }
        });

        Customer customerTest = mCustomerLogin.getValue();
    }

    public void getCustomers() {
        Call<List<Customer>> call = mShopService.getCustomers
                (NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET,
                NetworkParams.ORDER_STATUS_DESC,
                NetworkParams.PER_PAGE, NetworkParams.DATE_CUSTOMER);

        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                mCustomerListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {

            }
        });

    }

    public LiveData<Customer> getCustomerLogin() {
        return mCustomerLogin;
    }

    public String getMessage() {
        return mMessage;
    }

    public LiveData<List<Customer>> getCustomerListLiveData() {
        return mCustomerListLiveData;
    }


    public void setLoginCustomer(Customer customer){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mCustomerLogin.postValue(customer);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public MutableLiveData<Boolean> getIsLogin() {
        return mIsLogin;
    }

    public void setIsLogin(boolean isLogin) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mIsLogin.postValue(isLogin);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
