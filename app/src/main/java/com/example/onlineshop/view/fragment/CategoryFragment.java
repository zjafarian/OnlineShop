package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListCategoriesAdapter;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.databinding.FragmentCategoryBinding;
import com.example.onlineshop.viewmodel.CategoryViewModel;

import java.util.List;


public class CategoryFragment extends Fragment {
    public static final String ARGS_SELECT_LIST_PRODUCTS = "selectListProducts";
    public static final String ARGS_CATEGORY_ID = "categoryId";
    public static final String ARGS_ORDER_ID = "orderId";
    public static final String ARGS_PAGE_NAME = "pageName";
    public static final String ARGS_SEARCH_TEXT = "searchText";

    private FragmentCategoryBinding mBinding;
    private CategoryViewModel mViewModel;
    private ListCategoriesAdapter mCategoriesAdapter;


    public CategoryFragment() {
        // Required empty public constructor
    }


    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_category,
                container,
                false);

        initialRecycle();
        listener();
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        setLiveDataObserver();
        onItemClick();
    }

    private void onItemClick() {

        mCategoriesAdapter.onItemClicked(new ListCategoriesAdapter.OnItemClick() {
            @Override
            public void onItemClicked(Categories category, int position) {
                if (mViewModel.getCategoryLiveData()
                        .getValue()
                        .get(position)
                        .getId() == category.getId())
                    startListProducts(NetworkParams.CATEGORIES, category.getId());
            }
        });
    }

    private void updateUI(List<Categories> categories) {
        mCategoriesAdapter.setData(categories);
    }


    private void setLiveDataObserver() {

        mViewModel.getCategoryLiveData().observe(getViewLifecycleOwner(), new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {
                updateUI(categories);
            }
        });

    }

    private void initialRecycle() {
        mBinding.recycleViewCategoriesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCategoriesAdapter = new ListCategoriesAdapter();
        mBinding.recycleViewCategoriesList.setAdapter(mCategoriesAdapter);
    }

    private void startListProducts(String selectFilter, int categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_SELECT_LIST_PRODUCTS, selectFilter);
        bundle.putInt(ARGS_CATEGORY_ID, categoryId);
        bundle.putInt(ARGS_ORDER_ID,0);

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.list_products_fragment_des, bundle);
    }

    private void listener() {

        mBinding.textViewSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchEvent();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.imgBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEvent();
            }
        });
    }


    private void searchEvent() {
        String search = mBinding.textViewSearchBox.getText().toString();
        Bundle bundle = new Bundle();

        bundle.putString(ARGS_PAGE_NAME, NetworkParams.AllProducts);
        bundle.putString(ARGS_SEARCH_TEXT, search);
        bundle.putInt(ARGS_CATEGORY_ID, 0);
        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.search_Fragment_des, bundle);
    }


}