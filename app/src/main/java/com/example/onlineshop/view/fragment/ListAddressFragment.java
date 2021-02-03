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
import com.example.onlineshop.adapter.ListCategoriesAdapter;
import com.example.onlineshop.data.database.Address;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.databinding.FragmentListAddressBinding;
import com.example.onlineshop.viewmodel.AddressViewModel;

import java.util.List;

public class ListAddressFragment extends Fragment {

    private FragmentListAddressBinding mBinding;
    private AddressViewModel mViewModel;
    private ListAddressAdapter mAdapter;


    public ListAddressFragment() {
        // Required empty public constructor
    }


    public static ListAddressFragment newInstance() {
        ListAddressFragment fragment = new ListAddressFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        mViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
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
                R.layout.fragment_list_address,
                container,
                false);

        initRecycle();

        listener();

        return mBinding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onItemClick();
    }

    private void initRecycle() {
        mBinding.recycleViewListAddressPageListAddress.setLayoutManager
                (new LinearLayoutManager(getActivity()));

        mAdapter = new ListAddressAdapter();
        mBinding.recycleViewListAddressPageListAddress.setAdapter(mAdapter);
    }

    private void updateUI(List<Address> addresses) {
        mAdapter.setData(addresses);
    }

    private void onItemClick() {
        mAdapter.onItemClicked(new ListAddressAdapter.OnItemClick() {
            @Override
            public void onItemClicked(Address address) {
                mViewModel.getSelectAddressCustomer(address);
                setBackNavigation();
            }
        });

    }

    private void listener() {
        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackNavigation();
            }
        });
    }

    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }

}