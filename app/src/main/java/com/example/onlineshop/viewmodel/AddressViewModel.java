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



    public AddressViewModel(@NonNull Application application) {
        super(application);
        mRepositoryAddress = AddressRepository.getInstance(application);
        int customerId = SharedPreferencesOnlineShop.getCustomerId(getApplication());
        mRepositoryAddress.getAddressByCustomerId(customerId);
        mListAddressLiveData = mRepositoryAddress.getListAddressesCustomerLiveData();
    }

    public LiveData<List<Address>> getListAddressLiveData() {
        return mListAddressLiveData;
    }

    public void getSelectAddressCustomer(Address address){
        mRepositoryAddress.setSelectAddress(address);

    }


}
