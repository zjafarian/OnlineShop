package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListAddressAdapter;
import com.example.onlineshop.data.database.Address;
import com.example.onlineshop.databinding.FragmentAddressBinding;
import com.example.onlineshop.viewmodel.AddressViewModel;

import java.util.List;


public class AddressFragment extends Fragment {

    private FragmentAddressBinding mBinding;
    private AddressViewModel mViewModel;
    private ListAddressAdapter mAdapter;
    public static final String ARGS_ADDRESS_ID = "addressId";



    public AddressFragment() {
        // Required empty public constructor
    }


    public static AddressFragment newInstance() {
        AddressFragment fragment = new AddressFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        mViewModel = new ViewModelProvider(requireActivity()).get(AddressViewModel.class);
        mViewModel.getListAddressLiveData().observe(this, new Observer<List<Address>>() {
            @Override
            public void onChanged(List<Address> addresses) {
                updateUI(addresses);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_address,
                container,
                false);

        initRecycle();

        listener();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.fetchAddresses();
    }

    private void initRecycle() {
        mBinding.recycleViewListAddresses.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ListAddressAdapter();
        mBinding.recycleViewListAddresses.setAdapter(mAdapter);
    }

    private void listener() {
        onItemClick();

        mBinding.cardViewAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddAddressPage();

            }
        });

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackNavigation();
            }
        });
    }

    private void goToAddAddressPage() {
        Bundle bundle = new Bundle();

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.add_address_fragment_des, bundle);
    }

    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getBackStackEntry(R.id.login_sign_up_fragment_des);
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }

    private void updateUI(List<Address> addresses){
        mAdapter.setData(addresses);
    }


    private void onItemClick() {
     mAdapter.onItemEditClicked(new ListAddressAdapter.OnItemEditClick() {
         @Override
         public void onItemEditClicked(Address address) {
             setToShowAddressInMap(address.getIdAddress());
         }
     });

     mAdapter.onItemDeleteClicked(new ListAddressAdapter.OnItemDeleteClick() {
         @Override
         public void onItemDeleteClicked(Address address) {
             mViewModel.deleteAddress(address);
         }
     });


    }


    private void setToShowAddressInMap(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_ADDRESS_ID,id);
        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.show_map_per_address_fragment_des, bundle);
    }

}