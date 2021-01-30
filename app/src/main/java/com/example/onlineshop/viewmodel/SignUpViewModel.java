package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;

public class SignUpViewModel extends AndroidViewModel {
    public static final String TAG = "SignUpCustomer";


    private CustomerRepository mCustomerRepository;
    private boolean mIsCreateCustomer;
    private String mMessage;


    public SignUpViewModel(@NonNull Application application) {
        super(application);
       mCustomerRepository = CustomerRepository.getInstance();
    }


    public void fetchCreateCustomer(Customer customer) {
        if (customer.getEmail().length() != 0 && !customer.getEmail().equals("") && customer.getEmail() != null &&
                customer.getPassword().length() != 0 && !customer.getPassword().equals("") && customer.getPassword() != null) {
           mCustomerRepository.postCustomer(customer);

            //mCustomerRepository.postCustomer(email);
            mMessage = mCustomerRepository.getMessage();
            if (mMessage == null) {
                Toast toast = Toast.makeText(getApplication(),"ثبت نام با موفقیت انجام شد",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.show();
                Log.d(TAG, "customer created");
                mIsCreateCustomer = true;
            } else mIsCreateCustomer = false;
        } else {
            Toast toast = Toast.makeText(getApplication(), "نام کاربری و پسورد را وارد نکردید!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }
    }

    public boolean isCreateCustomer() {
        return mIsCreateCustomer;
    }

    public String getMessage() {
        return mMessage;
    }
}
