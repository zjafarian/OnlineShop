package com.example.onlineshop.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentAddAddressBinding;
import com.example.onlineshop.viewmodel.AddAddressViewModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.gson.internal.$Gson$Types;


public class AddAddressFragment extends Fragment {
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 0;
    private FragmentAddAddressBinding mBinding;
    private AddAddressViewModel mViewModel;
    private FusedLocationProviderClient mFusedLocationClient;


    public AddAddressFragment() {
        // Required empty public constructor
    }


    public static AddAddressFragment newInstance() {
        AddAddressFragment fragment = new AddAddressFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        mViewModel = new ViewModelProvider(requireActivity()).get(AddAddressViewModel.class);
        if (hasLocationAccess()) {
            requestLocation();

        } else {
            requestLocationAccessPermission();
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_address,
                container,
                false);


        return mBinding.getRoot();
    }

    private void requestLocationAccessPermission() {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        requestPermissions(permissions, REQUEST_CODE_LOCATION_PERMISSION);
    }

    private boolean hasLocationAccess() {
        boolean isFineLocation = ContextCompat.checkSelfPermission(
                getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        boolean isCoarseLocation = ContextCompat.checkSelfPermission(
                getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        return isFineLocation && isCoarseLocation;
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE_LOCATION_PERMISSION:
                if (grantResults == null && grantResults.length == 0)
                    return;
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    requestLocation();

                    return;
        }


    }


    @SuppressLint("MissingPermission")
    private void requestLocation() {
        if(!hasLocationAccess())
            return;
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(0);
        locationRequest.setNumUpdates(1);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationCallback locationCallBack = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
               Location location = locationResult.getLocations().get(0);
            }
        };



        mFusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallBack,
                Looper.getMainLooper());

    }
}