package com.example.onlineshop.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Order;
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
    private MutableLiveData<Boolean> mIsLogin = new MutableLiveData<>();
    private MutableLiveData<List<Order>> mOrdersCustomerLiveData = new MutableLiveData<>();
    private String mMessage;
    private String mMessageOrder;

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
                if (response.isSuccessful()) {
                    mCustomerLogin.setValue(response.body());
                } else Log.d(TAG, response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                mMessage = t.getMessage();
            }
        });

    }


    public void findCustomerById(int id) {
        Call<Customer> call = mShopService.getCustomerById(id,
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET);

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful())
                    mCustomerLogin.setValue(response.body());
                else mMessage = response.message();
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
    }

    public void findCustomerByEmail(String email) {
        Call<Customer> call = mShopService.getCustomerByEmail(
                NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET,
                email);

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful())
                    mCustomerLogin.setValue(response.body());
                else mMessage = response.message();
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });

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

    public void createOrder(Order order) {
        Call<Order> call = mShopService.createOrder(NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET, order);
        final Order[] orderTest = {new Order()};

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful())
                    mMessageOrder = "Successful";
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });

    }

    public void getOrdersCustomer(int customerId) {
        Call<List<Order>> call = mShopService.getOrdersByCustomer(NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET, customerId);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                mOrdersCustomerLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }

    public String getMessageOrder() {
        return mMessageOrder;
    }

    public String getMessage() {
        return mMessage;
    }

    public LiveData<Boolean> getIsLogin() {
        return mIsLogin;
    }


    public LiveData<Customer> getCustomerLogin() {
        return mCustomerLogin;
    }

    public LiveData<List<Order>> getOrdersCustomerLiveData() {
        return mOrdersCustomerLiveData;
    }


}
