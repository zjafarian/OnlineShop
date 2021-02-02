package com.example.onlineshop.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "address")
public class Address {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idAddress")
    private int mIdAddress;

    @ColumnInfo(name = "idCustomer")
    private int mIdCustomer;

    @ColumnInfo(name = "nameAddress")
    private String mNameAddress;

    @ColumnInfo(name = "fullAddress")
    private String mFullAddress;

    @ColumnInfo(name = "latitude")
    private String mLatitude;

    @ColumnInfo(name = "longitude")
    private String mLongitude;

    public Address() {
    }



    public Address(int idCustomer, String nameAddress, String fullAddress, String latitude, String longitude) {
        mIdCustomer = idCustomer;
        mNameAddress = nameAddress;
        mFullAddress = fullAddress;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public void setIdAddress(int idAddress) {
        mIdAddress = idAddress;
    }

    public void setIdCustomer(int idCustomer) {
        mIdCustomer = idCustomer;
    }

    public void setNameAddress(String nameAddress) {
        mNameAddress = nameAddress;
    }

    public void setFullAddress(String fullAddress) {
        mFullAddress = fullAddress;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public int getIdAddress() {
        return mIdAddress;
    }

    public int getIdCustomer() {
        return mIdCustomer;
    }

    public String getNameAddress() {
        return mNameAddress;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public String getFullAddress() {
        return mFullAddress;
    }
}
