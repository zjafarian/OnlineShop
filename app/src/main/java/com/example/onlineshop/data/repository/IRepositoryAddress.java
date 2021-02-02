package com.example.onlineshop.data.repository;

import com.example.onlineshop.data.database.Address;


import java.util.List;


public interface IRepositoryAddress {

    void insertAddress (Address address);
    Address getAddress (int id);
    void updateAddress (Address address);
    void deleteAddress (Address address);
    List<Address> getAddressByCustomerId(int idCustomer);
    List<Address> getAddressList();
}
