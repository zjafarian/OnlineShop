package com.example.onlineshop.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;

import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.internal.$Gson$Types;


public class AddAddressFragment extends Fragment {
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 0;
    private FragmentAddAddressBinding mBinding;
    private AddAddressViewModel mViewModel;
    private GoogleMap mMap;
    private LatLng mDragPosition;


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


        mViewModel = new ViewModelProvider(requireActivity()).get(AddAddressViewModel.class);
        if (mViewModel.hasLocationAccess()) {
            mViewModel.requestLocation();
        } else {
            requestLocationAccessPermission();
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_add_address,
                container,
                false);


        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                updateLocation(mViewModel.getMyLocation().getValue());


            }
        });



        return mBinding.getRoot();
    }

    private void listener() {




        mBinding.btnSaveInformationAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = mBinding.fullAddress.getText().toString();
                String receiver = mBinding.nameReceiver.getText().toString();

                if (address != null && !address.equals("") && receiver != null &&
                        !receiver.equals("")) {

                    mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                        @Override
                        public void onMarkerDragStart(Marker marker) {

                        }

                        @Override
                        public void onMarkerDrag(Marker marker) {

                        }

                        @Override
                        public void onMarkerDragEnd(Marker marker) {
                            mDragPosition = marker.getPosition();

                        }
                    });



                    mViewModel.submitAddress(
                            mBinding.fullAddress.getText().toString(),
                            mBinding.nameReceiver.getText().toString(),mDragPosition );
                    setBackNavigation();

                } else {
                    Toast toast = Toast.makeText(getActivity(),
                            R.string.text_error_address_field,
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }

            }
        });

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackNavigation();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.getMyLocation().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                updateLocation(location);
            }
        });

        listener();
    }

    private void updateLocation(Location location) {
        if (location == null || mMap == null)
            return;
        LatLng myLatLong = new LatLng(location.getLatitude(), location.getLongitude());
        mDragPosition = myLatLong;
        MarkerOptions markerOptions = new MarkerOptions()
                .position(myLatLong)
                .draggable(true)
                .title("My Location");


        mMap.addMarker(markerOptions);


        //int margin = getResources().getDimensionPixelSize(R.dimen.map_inset_margin);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(myLatLong);


        mMap.animateCamera(cameraUpdate);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.setDraggable(true);
                return false;
            }
        });



    }

    private void requestLocationAccessPermission() {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        requestPermissions(permissions, REQUEST_CODE_LOCATION_PERMISSION);
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
                    mViewModel.requestLocation();

                return;
        }


    }


    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }


}