package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.databinding.FragmentSinUpBinding;
import com.example.onlineshop.viewmodel.SignUpViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class SinUpFragment extends BottomSheetDialogFragment {


    public static final String ARGS_PASSWORD_CUSTOMER = "passwordCustomer";
    public static final String ARGS_EMAIL_CUSTOMER = "emailCustomer";
    private SignUpViewModel mViewModel;
    private FragmentSinUpBinding mBinding;
    private String mEmail;
    private String mPassword;

    public SinUpFragment() {
        // Required empty public constructor
    }


    public static SinUpFragment newInstance(String email, String password) {
        SinUpFragment fragment = new SinUpFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_EMAIL_CUSTOMER, email);
        args.putString(ARGS_PASSWORD_CUSTOMER, password);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null) {
            mEmail = getArguments().getString(ARGS_EMAIL_CUSTOMER);
            mPassword = getArguments().getString(ARGS_PASSWORD_CUSTOMER);
        }

        mViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_sin_up,
                container,
                false);



        //mBinding.userNameSignUp.setText(mEmail);
        mBinding.passwordSignUp.setText(mPassword);
        listener();

        return mBinding.getRoot();
    }

    private void listener() {
        mBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer(mBinding.emailSignUp.getText().toString().trim(),
                        mBinding.passwordSignUp.getText().toString().trim());
                mViewModel.fetchCreateCustomer(customer);
                if (mViewModel.isCreateCustomer()) {
                    setBackNavigation();
                } else {
                    Toast.makeText(getActivity(), mViewModel.getMessage(), Toast.LENGTH_LONG).show();
                }


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