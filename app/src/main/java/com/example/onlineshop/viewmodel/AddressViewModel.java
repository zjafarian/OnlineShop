package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.data.database.Address;
import com.example.onlineshop.data.repository.AddressRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.List;

public class AddressViewModel extends AndroidViewModel {

    private LiveData<List<Address>> mListAddressLiveData;
    private AddressRepository mRepositoryAddress;
    private int mIdCustomer;



    public AddressViewModel(@NonNull Application application) {
        super(application);
        mRepositoryAddress = AddressRepository.getInstance(application);
        mIdCustomer = SharedPreferencesOnlineShop.getCustomerId(getApplication());
        mRepositoryAddress.getAddressByCustomerId(mIdCustomer);
        mListAddressLiveData = mRepositoryAddress.getListAddressesCustomerLiveData();
    }

    public LiveData<List<Address>> getListAddressLiveData() {
        return mListAddressLiveData;
    }

    public void getSelectAddressCustomer(Address address){
        mRepositoryAddress.setSelectAddress(address);

    }

    public void deleteAddress(Address address){
        mRepositoryAddress.deleteAddress(address);
        mRepositoryAddress.getAddressByCustomerId(mIdCustomer);
        mListAddressLiveData = mRepositoryAddress.getListAddressesCustomerLiveData();

    }


}
