package com.example.onlineshop.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

@Dao
public interface AddressDAO {

    @Insert
    void insertAddress(Address address);

    @Update
    void updateAddress(Address address);

    @Delete
    void deleteAddress(Address address);

    @Query("SELECT * FROM Address")
    List<Address> getAddressList();

    @Query("SELECT * FROM address WHERE idCustomer=:customerId")
    List<Address> getAddressesCustomer(int customerId);

    @Query("SELECT * FROM address WHERE idAddress =:addressId")
    Address getAddress(int addressId);


}
