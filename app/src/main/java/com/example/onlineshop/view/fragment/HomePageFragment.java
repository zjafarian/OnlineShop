package com.example.onlineshop.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListCategoriesHomePageAdapter;
import com.example.onlineshop.adapter.ListProductsHomePageAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.databinding.FragmentHomePageBinding;
import com.example.onlineshop.databinding.SearchBarBinding;
import com.example.onlineshop.view.activity.SearchActivity;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    public static final String ARGS_SELECT_LIST_PRODUCTS = "selectListProducts";
    public static final String ARGS_PRODUCT_ID = "productId";
    public static final String ARGS_CATEGORY_ID = "categoryId";
    public static final String ARGS_PAGE_NAME = "pageName";
    public static final String ARGS_SEARCH_TEXT = "searchText";
    private HomePageViewModel mHomePageViewModel;
    private FragmentHomePageBinding mBinding;
    private ListCategoriesHomePageAdapter mCategoryAdapter;
    private ListProductsHomePageAdapter mLastProductsAdapter;
    private ListProductsHomePageAdapter mPopularityProductsAdapter;
    private ListProductsHomePageAdapter mRatingProductsAdapter;
    private SliderAdapter mSliderAdapter;


    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
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

        mHomePageViewModel = new ViewModelProvider(requireActivity()).get(HomePageViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home_page,
                container,
                false);


        initRecyclers();
        listener();

        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLiveDataObservers();
        onClickItems();
    }

    private void onClickItems() {

        mLastProductsAdapter.onItemClickedProduct
                (new ListProductsHomePageAdapter.OnItemClickProduct() {
                    @Override
                    public void onItemClicked(Products products) {
                        setNavigationToProductPage(products);

                    }
                });

        mPopularityProductsAdapter.onItemClickedProduct
                (new ListProductsHomePageAdapter.OnItemClickProduct() {
                    @Override
                    public void onItemClicked(Products products) {
                        setNavigationToProductPage(products);

                    }
                });

        mRatingProductsAdapter.onItemClickedProduct
                (new ListProductsHomePageAdapter.OnItemClickProduct() {
                    @Override
                    public void onItemClicked(Products products) {
                        setNavigationToProductPage(products);
                    }
                });

        mCategoryAdapter.onItemClickedCategory
                (new ListCategoriesHomePageAdapter.OnItemClickCategory() {
                    @Override
                    public void onItemClicked(Categories category, int position) {
                        if (mHomePageViewModel.getListCategoriesLiveData()
                                .getValue()
                                .get(position)
                                .getId() == category.getId())
                            startListProducts(NetworkParams.CATEGORIES, category.getId());
                    }
                });


    }

    private void setNavigationToProductPage(Products products) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_PRODUCT_ID, products.getId());

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.product_detail_fragment_des, bundle);
    }

    private void listener() {
        //openKeyboard();

        mBinding.textViewAllLastProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.LAST, 0);
            }
        });

        mBinding.imgViewAllLastProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.LAST, 0);
            }
        });

        mBinding.textViewAllPopularityProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.POPULARITY, 0);

            }
        });

        mBinding.imgViewAllPopularityProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.POPULARITY, 0);
            }
        });

        mBinding.textViewAllRatingProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.RATING, 0);
            }
        });

        mBinding.imgViewAllRatingProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListProducts(NetworkParams.RATING, 0);
            }
        });

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

        bundle.putString(ARGS_PAGE_NAME, "homePage");
        bundle.putString(ARGS_SEARCH_TEXT, search);
        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.search_Fragment_des,bundle);
    }

    private void startListProducts(String selectFilter, int categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_SELECT_LIST_PRODUCTS, selectFilter);
        bundle.putInt(ARGS_CATEGORY_ID, categoryId);

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.list_products_fragment_des, bundle);
    }

    public void updateUI(List<Products> products, List<Categories> categories, String selectAdapter) {
        switch (selectAdapter) {

            case NetworkParams.LAST:
                mLastProductsAdapter.setData(products);
                break;

            case NetworkParams.POPULARITY:
                mPopularityProductsAdapter.setData(products);
                break;

            case NetworkParams.RATING:
                mRatingProductsAdapter.setData(products);
                break;

            case NetworkParams.CATEGORIES:
                mCategoryAdapter.setData(categories);
                break;
        }
    }

    private void setLiveDataObservers() {

        if (!mHomePageViewModel.getLastProductsLiveData().hasActiveObservers())
            mHomePageViewModel.getLastProductsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Products>>() {
                @Override
                public void onChanged(List<Products> products) {
                    updateUI(products, null, NetworkParams.LAST);
                }
            });

        if (!mHomePageViewModel.getPopularityProductsLiveData().hasActiveObservers())
            mHomePageViewModel.getPopularityProductsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Products>>() {
                @Override
                public void onChanged(List<Products> products) {
                    updateUI(products, null, NetworkParams.POPULARITY);

                }
            });

        if (!mHomePageViewModel.getRatingProductsLiveData().hasActiveObservers())
            mHomePageViewModel.getRatingProductsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Products>>() {
                @Override
                public void onChanged(List<Products> products) {
                    updateUI(products, null, NetworkParams.RATING);
                }


            });

        //if (!mHomePageViewModel.getListCategoriesLiveData().hasActiveObservers())
        mHomePageViewModel.getListCategoriesLiveData().observe(getViewLifecycleOwner(), new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {
                updateUI(null, categories, NetworkParams.CATEGORIES);

            }
        });


    }

    private void initRecyclers() {
        mCategoryAdapter = new ListCategoriesHomePageAdapter();
        mLastProductsAdapter = new ListProductsHomePageAdapter();
        mPopularityProductsAdapter = new ListProductsHomePageAdapter();
        mRatingProductsAdapter = new ListProductsHomePageAdapter();

        mSliderAdapter = new SliderAdapter(new ArrayList<String>() {{
            add(mHomePageViewModel.getUriImage(R.drawable.slider_one));
            add(mHomePageViewModel.getUriImage(R.drawable.slider_two));
            add(mHomePageViewModel.getUriImage(R.drawable.slider_three));
            add(mHomePageViewModel.getUriImage(R.drawable.slider_four));
        }});
        ;


        mBinding.recycleViewListCategory.setAdapter(mCategoryAdapter);
        mBinding.recycleViewLastProducts.setAdapter(mLastProductsAdapter);
        mBinding.recycleViewPopularityProducts.setAdapter(mPopularityProductsAdapter);
        mBinding.recycleViewRatingProducts.setAdapter(mRatingProductsAdapter);

        mBinding.specialProductsSlider.setSliderAdapter(mSliderAdapter);
        mBinding.specialProductsSlider.startAutoCycle();
        mBinding.specialProductsSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mBinding.specialProductsSlider.setSliderTransformAnimation
                (SliderAnimations.SIMPLETRANSFORMATION);
    }


    private void openKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


}