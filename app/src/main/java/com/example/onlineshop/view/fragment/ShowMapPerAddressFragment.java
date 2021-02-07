package com.example.onlineshop.view.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlineshop.R;
import com.example.onlineshop.data.database.Address;
import com.example.onlineshop.databinding.FragmentShowMapPerAddressBinding;
import com.example.onlineshop.viewmodel.AddAddressViewModel;
import com.example.onlineshop.viewmodel.AddressViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class ShowMapPerAddressFragment extends Fragment {
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 0;
    public static final String ARGS_ADDRESS_ID = "addressId";
    private FragmentShowMapPerAddressBinding mBinding;
    private AddAddressViewModel mViewModel;
    private int mAddressId;
    private GoogleMap mMap;
    private LatLng mDragPosition;




    public ShowMapPerAddressFragment() {
        // Required empty public constructor
    }


    public static ShowMapPerAddressFragment newInstance(int addressId) {
        ShowMapPerAddressFragment fragment = new ShowMapPerAddressFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_ADDRESS_ID, addressId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            mAddressId = getArguments().getInt(ARGS_ADDRESS_ID);

        mViewModel = new ViewModelProvider(requireActivity()).get(AddAddressViewModel.class);
        mViewModel.findAddress(mAddressId);

        if (mViewModel.hasLocationAccess()) {
            mViewModel.getAddressLiveData().observe(this, new Observer<Address>() {
                @Override
                public void onChanged(Address address) {
                    updateLocation(address);
                }
            });
        } else {
            requestLocationAccessPermission();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_show_map_per_address,
                container,
                false );


        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                updateLocation(mViewModel.getAddressLiveData().getValue());


            }
        });

        listener();


        return mBinding.getRoot();
    }

    private void listener() {
        mBinding.btnEditInformationAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = mBinding.fullAddress.getText().toString();


                if (address != null && !address.equals("")) {

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

                    mViewModel.editAddress(mBinding.fullAddress.getText().toString(),mDragPosition);

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

    private void updateLocation(Address address) {

        if (address == null || mMap == null)
            return;

        double latitude = Double.parseDouble(address.getLatitude());
        double longitude = Double.parseDouble(address.getLongitude());

        LatLng myLatLong = new LatLng(latitude, longitude);
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



    private void requestLocationAccessPermission() {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        requestPermissions(permissions, REQUEST_CODE_LOCATION_PERMISSION);
    }

    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getBackStackEntry(R.id.address_fragment_des);
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }
}