package com.example.onlineshop.view.fragment;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.databinding.FragmentLoginSignUpBinding;
import com.example.onlineshop.viewmodel.LoginSingUpViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class LoginSignUpFragment extends Fragment {
    public static final String ARGS_PASSWORD_CUSTOMER = "passwordCustomer";
    public static final String ARGS_EMAIL_CUSTOMER = "emailCustomer";
    public static final String ARGS_WHICH_PAGE = "whichPage";

    private FragmentLoginSignUpBinding mBinding;
    private LoginSingUpViewModel mViewModel;
    private String mWhichPage;
    private String mEmail;
    private String mPassword;


    public LoginSignUpFragment() {
        // Required empty public constructor
    }


    public static LoginSignUpFragment newInstance(String whichPage) {
        LoginSignUpFragment fragment = new LoginSignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_WHICH_PAGE, whichPage);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);

        if (getArguments() != null)
            mWhichPage = getArguments().getString(ARGS_WHICH_PAGE);


        mViewModel = new ViewModelProvider(requireActivity()).get(LoginSingUpViewModel.class);


        mViewModel.getIsLoginLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mBinding.layoutLogin.setVisibility(View.GONE);
                    mBinding.layoutAccount.setVisibility(View.VISIBLE);
                    mViewModel.findCustomerBeLogin();
                } else {
                    mBinding.layoutLogin.setVisibility(View.VISIBLE);
                    mBinding.layoutAccount.setVisibility(View.GONE);
                }

            }
        });


    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_login_sign_up,
                container,
                false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setTitle(R.string.user_item_navigation_menu);
        mBinding.toolbar.inflateMenu(R.menu.account_nav_main);




        listener();

        return mBinding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.account_nav_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fragment_setting_des:
                Navigation.findNavController(mBinding.getRoot()).navigate(R.id.setting_fragment_des);
            default:
                return super.onOptionsItemSelected(item);
        }



    }

    private void listener() {

        mBinding.textViewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNavigationSignUp();
            }
        });

        mBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmail = mBinding.emailLogin.getText().toString().trim();
                mPassword = mBinding.passwordLogin.getText().toString().trim();
                mViewModel.findCustomerGetLogin(mEmail);
                if (mViewModel.isFindCustomer()) {
                    if (mWhichPage == null)
                        loadAccountLayout();
                    else if (mWhichPage.equals("shopping"))
                        goToShoppingPage();

                }

            }
        });


    }

    private void setNavigationSignUp() {
        Bundle bundle = new Bundle();
        String email = mBinding.emailLogin.getText().toString();
        String password = mBinding.passwordLogin.getText().toString();
        bundle.putString(email, ARGS_EMAIL_CUSTOMER);
        bundle.putString(password, ARGS_PASSWORD_CUSTOMER);

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.sign_up_fragment_des, bundle);
    }

    private void loadAccountLayout() {
        mBinding.layoutLogin.setVisibility(View.GONE);
        mBinding.layoutAccount.setVisibility(View.VISIBLE);
    }

    private void goToShoppingPage() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }


}