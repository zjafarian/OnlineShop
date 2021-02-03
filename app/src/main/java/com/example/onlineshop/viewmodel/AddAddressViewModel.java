package com.example.onlineshop.viewmodel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.data.database.Address;
import com.example.onlineshop.data.repository.AddressRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class AddAddressViewModel extends AndroidViewModel {

    public static final String TAG = "Locator";
    private FusedLocationProviderClient mFusedLocationClient;
    private AddressRepository mAddressRepository;
    private MutableLiveData<Location> mMyLocation = new MutableLiveData<>();

    public AddAddressViewModel(@NonNull Application application) {
        super(application);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplication());
        mAddressRepository = AddressRepository.getInstance(getApplication());
    }

    @SuppressLint("MissingPermission")
    public void requestLocation() {
        if (!hasLocationAccess())
            return;
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(0);
        locationRequest.setNumUpdates(1);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationCallback locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location = locationResult.getLocations().get(0);
                Log.d(TAG, "lat: " + location.getLatitude() + " lon: " + location.getLongitude());
                mMyLocation.setValue(location);
            }
        };


        mFusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallBack,
                Looper.getMainLooper());

    }

    public boolean hasLocationAccess() {
        boolean isFineLocation = ContextCompat.checkSelfPermission(
                getApplication().getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        boolean isCoarseLocation = ContextCompat.checkSelfPermission(
                getApplication().getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        return isFineLocation && isCoarseLocation;
    }

    public LiveData<Location> getMyLocation() {
        return mMyLocation;
    }


    public void submitAddress(String fullAddress, String receiverName, LatLng latLng) {
        int customerId = SharedPreferencesOnlineShop.getCustomerId(getApplication());
        String latitude = String.valueOf(latLng.latitude);
        String longitude = String.valueOf(latLng.longitude);
        Address address = new Address(customerId,receiverName,fullAddress,latitude,longitude);
        mAddressRepository.insertAddress(address);
        mAddressRepository.getAddress(customerId);

    }
}
