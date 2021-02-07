package com.example.onlineshop.data.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.onlineshop.data.database.Address;
import com.example.onlineshop.data.database.AddressDAO;
import com.example.onlineshop.data.database.OnlineShopDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddressRepository implements IRepositoryAddress {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static AddressRepository sInstance;
    private AddressDAO mAddressDAO;
    private MutableLiveData<List<Address>> mListAddressesCustomerLiveData = new MutableLiveData<>();
    private MutableLiveData<Address> mSelectAddress = new MutableLiveData<>();

    private AddressRepository(Context context) {
        mContext = context.getApplicationContext();
        OnlineShopDatabase onlineShopDatabase = Room.databaseBuilder(mContext,
                OnlineShopDatabase.class,"onlineShop.db")
                .allowMainThreadQueries()
                .build();


        mAddressDAO =onlineShopDatabase.getAddressTable();

    }

    public static AddressRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new AddressRepository(context);
        return sInstance;
    }

    @Override
    public void insertAddress(Address address) {
        mAddressDAO.insertAddress(address);

    }

    @Override
    public Address getAddress(int id) {
        return mAddressDAO.getAddress(id);
    }

    @Override
    public void updateAddress(Address address) {
        mAddressDAO.updateAddress(address);

    }

    @Override
    public void deleteAddress(Address address) {
        mAddressDAO.deleteAddress(address);

    }

    @Override
    public List<Address> getAddressByCustomerId(int idCustomer) {
        mListAddressesCustomerLiveData.setValue(mAddressDAO.getAddressesCustomer(idCustomer));
        return mAddressDAO.getAddressesCustomer(idCustomer);
    }

    @Override
    public List<Address> getAddressList() {
        return mAddressDAO.getAddressList();
    }

    public LiveData<List<Address>> getListAddressesCustomerLiveData() {
        return mListAddressesCustomerLiveData;
    }

    public void setSelectAddress(Address address){
        mSelectAddress.setValue(address);
    }

    public LiveData<Address> getSelectAddress() {
        return mSelectAddress;
    }


}
