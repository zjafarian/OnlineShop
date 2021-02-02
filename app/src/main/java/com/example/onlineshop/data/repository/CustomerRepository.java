package com.example.onlineshop.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.network.remote.retrofit.CustomerService;
import com.example.onlineshop.data.network.remote.retrofit.RetrofitInstance;
import com.example.onlineshop.data.network.remote.retrofit.ShopService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomerRepository {
    private static final String TAG = "CustomerRepository";
    private CustomerService mCustomerService;
    private static CustomerRepository sInstance;
    private MutableLiveData<Customer> mCustomerLogin = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLogin = new MutableLiveData<>();
    private MutableLiveData<List<Order>> mOrdersCustomerLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Customer>> mListCustomerLiveData = new MutableLiveData<>();
    private String mMessage;
    private String mMessageOrder;

    private CustomerRepository() {
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofit();
        mCustomerService= retrofit.create(CustomerService.class);
    }

    public static CustomerRepository getInstance() {
        if (sInstance == null)
            sInstance = new CustomerRepository();

        return sInstance;
    }


    public void postCustomer(Customer customer) {

        Call<Customer> call =
                mCustomerService.createCustomer("https://woocommerce.maktabsharif.ir/wp-json/wc/v3/customers/",
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
        Call<Customer> call = mCustomerService.getCustomerById(id,
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
        Call<Customer> call = mCustomerService.getCustomerByEmail(
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
        Call<Order> call = mCustomerService.createOrder(NetworkParams.CONSUMER_KEY,
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
        Call<List<Order>> call = mCustomerService.getOrdersByCustomer(NetworkParams.CONSUMER_KEY,
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


    public void getAllCustomers(){
     Call<List<Customer>> call=  mCustomerService.getAllCustomer(NetworkParams.CONSUMER_KEY,
                NetworkParams.CONSUMER_SECRET,
                "registered_date", "desc","40");
     call.enqueue(new Callback<List<Customer>>() {
         @Override
         public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
             mListCustomerLiveData.setValue(response.body());
         }

         @Override
         public void onFailure(Call<List<Customer>> call, Throwable t) {

         }
     });


    }

    public void setLoginCustomer(Customer customer){
        mCustomerLogin.setValue(customer);
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

    public LiveData<List<Customer>> getListCustomerLiveData() {
        return mListCustomerLiveData;
    }


}
