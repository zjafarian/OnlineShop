package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.List;

public class LoginSingUpViewModel extends AndroidViewModel {


    private CustomerRepository mCustomerRepository;
    private LiveData<Customer> mCustomerLiveData;
    private LiveData<Boolean> mIsLoginLiveData;
    private boolean mFindCustomer;

    public LoginSingUpViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance();
        mCustomerLiveData = mCustomerRepository.getCustomerLogin();

        mIsLoginLiveData = mCustomerRepository.getIsLogin();
    }


    public LiveData<Boolean> getIsLoginLiveData() {
        return mIsLoginLiveData;
    }

    public void findCustomerBeLogin() {
        int id = SharedPreferencesOnlineShop.getCustomerId(getApplication());
        if (id != 0) {
            mCustomerRepository.findCustomerById(id);
            mCustomerLiveData = mCustomerRepository.getCustomerLogin();

        }

    }

    public void findCustomerGetLogin(String email) {
        mCustomerRepository.findCustomerByEmail(email);
        if (mCustomerRepository.getMessage() == null) {
            mCustomerLiveData = mCustomerRepository.getCustomerLogin();
            SharedPreferencesOnlineShop.setCustomerId(getApplication(), mCustomerLiveData.getValue().getId());
            SharedPreferencesOnlineShop.setStatusLogin(getApplication(), true);
            mFindCustomer = true;
        } else mFindCustomer = false;

    }

    public boolean isFindCustomer() {
        return mFindCustomer;
    }
}
