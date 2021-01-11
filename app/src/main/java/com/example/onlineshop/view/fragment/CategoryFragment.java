package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListCategoriesAdapter;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.databinding.FragmentCategoryBinding;
import com.example.onlineshop.viewmodel.CategoryViewModel;

import java.util.List;


public class CategoryFragment extends Fragment {
    private FragmentCategoryBinding mBinding;
    private CategoryViewModel mViewModel;


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

        mViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        mViewModel.fetchCategoriesAsync();
        mViewModel.getCategoryLiveData().observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {
                List<Categories> categoriesList = categories;
                updateUI(categories);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_category,
                container,
                false);


        mBinding.recycleViewCategoriesList.setLayoutManager(new LinearLayoutManager(getActivity()));


        return mBinding.getRoot();
    }

    private void updateUI(List<Categories> categories) {
        if (mViewModel.getCategoriesAdapter() == null) {
            ListCategoriesAdapter listCategoriesAdapter = new ListCategoriesAdapter
                    (this,mViewModel,categories);

            mViewModel.setCategoriesAdapter(listCategoriesAdapter);
            mBinding.recycleViewCategoriesList.setAdapter(mViewModel.getCategoriesAdapter());
        } else {
            mViewModel.notifyAdapter();
        }
    }
}