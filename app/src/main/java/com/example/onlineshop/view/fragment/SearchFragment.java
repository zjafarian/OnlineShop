package com.example.onlineshop.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentSearchBinding;
import com.example.onlineshop.viewmodel.SearchViewModel;

public class SearchFragment extends Fragment {
    public static final String ARGS_PAGE_NAME = "pageName";
    public static final String ARGS_SEARCH_TEXT = "searchText";
    private String mPageName;
    private String mSearchText;
    private FragmentSearchBinding mBinding;
    private SearchViewModel mViewModel;



    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(String pageName, String searchText) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_PAGE_NAME, pageName);
        args.putString(ARGS_SEARCH_TEXT, searchText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mPageName = getArguments().getString(ARGS_PAGE_NAME);
            mSearchText = getArguments().getString(ARGS_SEARCH_TEXT);
        }

        mViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_search,
                container,
                false);
        openKeyboard();
        //mBinding.textViewSearchBox.requestFocus();
        initView();
        listener();
        return mBinding.getRoot();
    }

    private void listener() {
        mBinding.textViewSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.equals("") && s.length() !=0)
                    mBinding.imgBtnSearchClose.setVisibility(View.VISIBLE);
                else {
                    mBinding.imgBtnSearchClose.setVisibility(View.GONE);
                    mViewModel.setValues(mPageName);
                    mBinding.textViewSearchBox.setHint(getActivity().getString
                            (R.string.text_search_hint, mViewModel.getSetTextHintSearch()));

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.equals("") && s.length() !=0)
                    mBinding.imgBtnSearchClose.setVisibility(View.VISIBLE);
                else {
                    mBinding.imgBtnSearchClose.setVisibility(View.GONE);
                    mViewModel.setValues(mPageName);
                    mBinding.textViewSearchBox.setHint(getActivity().getString
                            (R.string.text_search_hint, mViewModel.getSetTextHintSearch()));

                }

            }
        });

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavBackStackEntry navBackStackEntry = Navigation.findNavController
                        (mBinding.getRoot()).getPreviousBackStackEntry();
                Navigation.findNavController(mBinding.getRoot()).navigate
                        (navBackStackEntry.getDestination().getId());





            }
        });

        mBinding.imgBtnSearchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.textViewSearchBox.setText("");
                mBinding.imgBtnSearchClose.setVisibility(View.GONE);
            }
        });

    }

    private void initView() {
        if (mSearchText.equals("") && mSearchText.length() == 0){
            mViewModel.setValues(mPageName);
            mBinding.textViewSearchBox.setHint(getActivity().getString
                    (R.string.text_search_hint, mViewModel.getSetTextHintSearch()));
        } else {
            mBinding.textViewSearchBox.setText(mSearchText);
            mBinding.imgBtnSearchClose.setVisibility(View.VISIBLE);
        }
    }


    private void openKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


    @Override
    public void onPause() {
        super.onPause();
    /*    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);*/
    }
}