package com.example.onlineshop.view.fragment;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListProductsAdapter;

import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.FragmentListProductsBinding;
import com.example.onlineshop.viewmodel.ListProductsViewModel;

import java.util.List;


public class ListProductsFragment extends Fragment {
    public static final String ARGS_SELECT_LIST_PRODUCTS = "selectListProducts";
    public static final String ARGS_CATEGORY_ID = "categoryId";
    public static final String ARGS_PRODUCT_ID = "productId";
    public static final String ARGS_ORDER_ID = "orderId";
    public static final String ARGS_PAGE_NAME = "pageName";
    public static final String ARGS_SEARCH_TEXT = "searchText";
    private FragmentListProductsBinding mBinding;
    private ListProductsViewModel mListProductsViewModel;
    private String mSelectListProducts;
    private ListProductsAdapter mListProductAdapter;
    private int mCategoryId;
    private ArrayAdapter<CharSequence> mAdapterSpinner;
    private boolean mClickSort = false;
    private int mOrderId;


    public ListProductsFragment() {
        // Required empty public constructor
    }

    public static ListProductsFragment newInstance(String selectListProducts, int categoryId, int orderId) {
        ListProductsFragment fragment = new ListProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_SELECT_LIST_PRODUCTS, selectListProducts);
        args.putInt(ARGS_CATEGORY_ID, categoryId);
        args.putInt(ARGS_ORDER_ID, orderId);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSelectListProducts = getArguments().getString(ARGS_SELECT_LIST_PRODUCTS);
            mCategoryId = getArguments().getInt(ARGS_CATEGORY_ID);
            mOrderId = getArguments().getInt(ARGS_ORDER_ID);
        }
        setRetainInstance(true);

        mListProductsViewModel = new
                ViewModelProvider(requireActivity()).get(ListProductsViewModel.class);

        mListProductsViewModel.setSelectListProducts(mSelectListProducts);
        mListProductsViewModel.setCategoryId(mCategoryId);
        mListProductsViewModel.setOrderId(mOrderId);
        mListProductsViewModel.getListName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null && s.length() != 0)
                    mListProductsViewModel.setProductsList();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_list_products,
                container,
                false);

        mBinding.recycleViewListProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        initRecycler();
        initView();
        listener();


        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setObservers();
        onClickItems();

    }

    @Override
    public void onResume() {
        super.onResume();

        mListProductsViewModel.setSelectListProducts(mSelectListProducts);
        mListProductsViewModel.setCategoryId(mCategoryId);
        mListProductsViewModel.setOrderId(mOrderId);
        mListProductsViewModel.setProductsList();

    }

    private void setObservers() {
        mListProductsViewModel.getSelectProductsShow().observe(getViewLifecycleOwner(), new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                updateUI(products);
            }
        });


        mListProductsViewModel.getSortProducts().observe(getViewLifecycleOwner(), new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                updateUI(products);
            }
        });


    }

    private void initRecycler() {
        mListProductAdapter = new ListProductsAdapter();
        mBinding.recycleViewListProducts.setAdapter(mListProductAdapter);
    }

    private void updateUI(List<Products> products) {
        mListProductAdapter.setData(products);
    }

    private void onClickItems() {
        mListProductAdapter.onItemClickedProduct(new ListProductsAdapter.OnItemClickProduct() {
            @Override
            public void onItemClicked(Products products) {
                setNavigationToProductPage(products);
            }
        });
    }

    private void setNavigationToProductPage(Products products) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_PRODUCT_ID, products.getId());

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.product_detail_fragment_des, bundle);
    }

    private void initView() {

        mAdapterSpinner = ArrayAdapter.createFromResource
                (getActivity(),
                        R.array.sort_array,
                        android.R.layout.simple_spinner_dropdown_item);

        mBinding.spinnerSort.setAdapter(mAdapterSpinner);
    }

    private void listener() {
        mBinding.imgBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEvent();
            }
        });

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackNavigation();
            }
        });

        mBinding.imgBtnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusSpinnerSort();

            }
        });

        mBinding.txtViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStatusSpinnerSort();
            }
        });

        mBinding.spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sort = mAdapterSpinner.getItem(position).toString();
                mBinding.txtViewSort.setText(mAdapterSpinner.getItem(position));

                mAdapterSpinner.getItemId(position);
                mListProductsViewModel.fetchSortQuery(sort);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }

    private void setStatusSpinnerSort() {
        mClickSort = !mClickSort;
        if (mClickSort) {
            mBinding.spinnerSort.setVisibility(View.VISIBLE);
        } else {
            mBinding.spinnerSort.setVisibility(View.GONE);
        }
    }

    private void searchEvent() {
        String search = "";
        String whichList;
        if (mCategoryId != 0)
            whichList = mListProductsViewModel.getCategoryName();
        else whichList = mSelectListProducts;
        Bundle bundle = new Bundle();

        bundle.putString(ARGS_PAGE_NAME, whichList);
        bundle.putString(ARGS_SEARCH_TEXT, search);
        bundle.putInt(ARGS_CATEGORY_ID, mCategoryId);
        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.search_Fragment_des, bundle);
    }

}