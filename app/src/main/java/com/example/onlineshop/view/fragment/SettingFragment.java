package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentSettingBinding;
import com.example.onlineshop.viewmodel.SettingViewModel;


public class SettingFragment extends Fragment {
    private SettingViewModel mViewModel;
    private FragmentSettingBinding mBinding;


    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(SettingViewModel.class);
        mViewModel.getActive().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mBinding.radioBtnHourThree.setEnabled(aBoolean);
                mBinding.radioBtnHoutFive.setEnabled(aBoolean);
                mBinding.radioBtnHourEight.setEnabled(aBoolean);
                mBinding.radioBtnHourTwelve.setEnabled(aBoolean);
                mBinding.editTextCustomTime.setEnabled(aBoolean);
                if(aBoolean)
                    mBinding.switchShowNewProducts.setText(R.string.text_active_show_new_product);
                else mBinding.switchShowNewProducts.setText(R.string.text_de_active_show_new_product);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate
                (inflater,
                        R.layout.fragment_setting,
                        container,
                        false);

        mBinding.setSettingViewModel(mViewModel);
        if(mBinding.switchShowNewProducts.isChecked())
            mViewModel.setStatusActiveToggle(true);
        else mViewModel.setStatusActiveToggle(false);
        initView();

        listener();

        return mBinding.getRoot();
    }

    private void initView() {
        if (mViewModel.isPollWorkerActive())
            mBinding.switchShowNewProducts.setChecked(true);
        else mBinding.switchShowNewProducts.setChecked(false);

        if (mBinding.switchShowNewProducts.isChecked())
            mBinding.radioGroupHours.setEnabled(true);
        else mBinding.radioGroupHours.setEnabled(false);

        switch (mViewModel.getTimeFromSharedPreferences()){
            case 3:
                mBinding.radioBtnHourThree.setChecked(true);
                break;
            case 5:
                mBinding.radioBtnHoutFive.setChecked(true);
                break;
            case 8:
                mBinding.radioBtnHourEight.setChecked(true);
                break;
            case 12:
                mBinding.radioBtnHourTwelve.setChecked(true);
                break;
            default:
                mBinding.editTextCustomTime.setText
                        (String.valueOf(mViewModel.getTimeFromSharedPreferences()));
                break;
        }
    }

    private void listener() {
        mBinding.switchShowNewProducts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mViewModel.setStatusActiveToggle(isChecked);
            }
        });

        mBinding.radioGroupHours.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_btn_hour_three:
                        mViewModel.setTimeNotification("3");
                        break;
                    case R.id.radio_btn_hout_five:
                        mViewModel.setTimeNotification("5");
                        break;
                    case R.id.radio_btn_hour_eight:
                        mViewModel.setTimeNotification("8");
                        break;
                    case R.id.radio_btn_hour_twelve:
                        mViewModel.setTimeNotification("12");
                        break;
                }
            }
        });

        mBinding.editTextCustomTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.setTimeNotification(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.saveSetting();
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