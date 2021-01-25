package com.example.onlineshop.view.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListProductsAdapter;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.FragmentSearchBinding;
import com.example.onlineshop.viewmodel.SearchViewModel;

import java.util.List;

public class SearchFragment extends Fragment {
    public static final String ARGS_PAGE_NAME = "pageName";
    public static final String ARGS_SEARCH_TEXT = "searchText";
    public static final String ARGS_CATEGORY_ID = "categoryId";
    private String mWhichList;
    private String mSearchText;
    private int mCategoryId;
    private FragmentSearchBinding mBinding;
    private SearchViewModel mViewModel;
    private ListProductsAdapter mListProductsAdapter;
    private ArrayAdapter<CharSequence> mAdapterSpinner;
    private boolean mClickSort = false;
    private boolean mClickFilter = false;
    private String mSort;


    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(String pageName, String searchText, int categoryId) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_PAGE_NAME, pageName);
        args.putString(ARGS_SEARCH_TEXT, searchText);
        args.putInt(ARGS_CATEGORY_ID, categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mWhichList = getArguments().getString(ARGS_PAGE_NAME);
            mSearchText = getArguments().getString(ARGS_SEARCH_TEXT);
            mCategoryId = getArguments().getInt(ARGS_CATEGORY_ID);
        }

        mViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        mViewModel.setValues(mWhichList, mSearchText, mCategoryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_search,
                container,
                false);
        //openKeyboard();
        mBinding.textViewSearchBox.requestFocus();


        initView();
        initRecyclers();
        listener();
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mViewModel.getSearchProductsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                if (products.size() != 0 && products != null) {
                    mBinding.cardViewFilterAndSort.setVisibility(View.VISIBLE);
                    mBinding.recycleViewListProductsWithSearch.setVisibility(View.VISIBLE);
                    updateUI(products);
                } else {
                    mBinding.cardViewFilterAndSort.setVisibility(View.GONE);
                }

            }
        });
    }

    private void listener() {
        mBinding.textViewSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setVisibilityAction(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                setVisibilityAction(s.toString());
            }
        });

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNavigationBack();

            }
        });

        mBinding.imgBtnSearch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                mViewModel.setValues(mWhichList, mBinding.textViewSearchBox.getText().toString(), mCategoryId);
                mViewModel.fetchSearchQuery(mBinding.textViewSearchBox.getText().toString(),mSort);
                mBinding.cardViewFilterAndSort.setVisibility(View.VISIBLE);
            }
        });

        mBinding.imgBtnSearchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.textViewSearchBox.setText("");
                mBinding.imgBtnSearchClose.setVisibility(View.GONE);
            }
        });

        mBinding.imgBtnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusSort();
            }
        });

        mBinding.txtViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusSort();
            }
        });

        mBinding.spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mBinding.txtViewSort.setText(mAdapterSpinner.getItem(position));
                mSort = mAdapterSpinner.getItem(position).toString();
                mAdapterSpinner.getItemId(position);
                mViewModel.fetchSearchQuery(mBinding.textViewSearchBox.getText().toString(),mSort);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mBinding.txtViewSort.setText(R.string.text_view_sort);

            }
        });
    }

    private void setNavigationBack() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }

    private void statusSort() {
        mClickSort = !mClickSort;
        if (mClickSort) {
            mBinding.spinnerSort.setVisibility(View.VISIBLE);
        } else {
            mBinding.spinnerSort.setVisibility(View.GONE);
        }
    }

    private void setVisibilityAction(String s) {
        if (!s.equals("") && s.length() != 0) {
            mBinding.imgBtnSearchClose.setVisibility(View.VISIBLE);
            mBinding.imgBtnSearch.setVisibility(View.VISIBLE);
        } else {
            mBinding.imgBtnSearchClose.setVisibility(View.GONE);
            mBinding.imgBtnSearch.setVisibility(View.GONE);
            mBinding.textViewSearchBox.setHint(getActivity().getString
                    (R.string.text_search_hint, mViewModel.getSetTextHintSearch()));

        }
    }

    private void initView() {

        mBinding.recycleViewListProductsWithSearch.setLayoutManager
                (new LinearLayoutManager(getActivity()));

        if (mBinding.imgBtnSearch.getVisibility() == View.VISIBLE)
            mBinding.cardViewFilterAndSort.setVisibility(View.VISIBLE);
        else mBinding.cardViewFilterAndSort.setVisibility(View.GONE);


        if (mSearchText.equals("") && mSearchText.length() == 0) {
            mBinding.textViewSearchBox.setHint(getActivity().getString
                    (R.string.text_search_hint, mViewModel.getSetTextHintSearch()));
        } else {
            mBinding.textViewSearchBox.setText(mSearchText);
            mBinding.imgBtnSearchClose.setVisibility(View.VISIBLE);
            mBinding.imgBtnSearch.setVisibility(View.VISIBLE);
        }


        mAdapterSpinner = ArrayAdapter.createFromResource
                (getActivity(),
                        R.array.sort_array,
                        android.R.layout.simple_spinner_dropdown_item);

        mBinding.spinnerSort.setAdapter(mAdapterSpinner);


    }

    private void openKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void updateUI(List<Products> products) {
        mListProductsAdapter.setData(products);

    }

    private void initRecyclers() {
        mListProductsAdapter = new ListProductsAdapter();
        mBinding.recycleViewListProductsWithSearch.setAdapter(mListProductsAdapter);
    }

}