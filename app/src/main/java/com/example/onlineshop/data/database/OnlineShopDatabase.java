package com.example.onlineshop.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Address.class}, version = 1)
public abstract class OnlineShopDatabase extends RoomDatabase {

    public abstract AddressDAO getAddressTable();

}
