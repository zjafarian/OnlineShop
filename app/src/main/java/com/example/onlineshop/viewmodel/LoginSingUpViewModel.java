package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.ArrayList;
import java.util.List;

public class LoginSingUpViewModel extends AndroidViewModel {

    private LiveData<List<Customer>> mCustomerListLiveData;
    private CustomerRepository mCustomerRepository;
    private LiveData<Customer> mCustomerLiveData;
    private LiveData<Boolean> mIsLoginLiveData;

    public LoginSingUpViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance();
        mCustomerRepository.getCustomers();
        mCustomerListLiveData = mCustomerRepository.getCustomerListLiveData();
        mCustomerLiveData = mCustomerRepository.getCustomerLogin();
        mIsLoginLiveData = mCustomerRepository.getIsLogin();
    }


    public LiveData<List<Customer>> getCustomerListLiveData() {
        return mCustomerListLiveData;
    }

    public void getFetchCustomers() {
        mCustomerRepository.getCustomers();
        mCustomerListLiveData = mCustomerRepository.getCustomerListLiveData();

    }


    public void setCustomerLogin(Customer customer) {
        mCustomerLiveData = mCustomerRepository.getCustomerLogin();
        if (mCustomerLiveData.getValue() != null) {

            if (customer.getId() != mCustomerLiveData.getValue().getId()) {

                mCustomerRepository.setLoginCustomer(customer);
                mCustomerRepository.setIsLogin(true);
                SharedPreferencesOnlineShop.setStatusLogin(getApplication(), true);
            } else if (customer.getId() == mCustomerLiveData.getValue().getId()) {
                mCustomerRepository.setIsLogin(true);
                SharedPreferencesOnlineShop.setStatusLogin(getApplication(), true);
            }


        } else if (mCustomerLiveData.getValue() == null){
            mCustomerRepository.setLoginCustomer(customer);
            mCustomerRepository.setIsLogin(true);
            SharedPreferencesOnlineShop.setStatusLogin(getApplication(), true);
        }
    }

    public LiveData<Boolean> getIsLoginLiveData() {
        return mIsLoginLiveData;
    }
}
