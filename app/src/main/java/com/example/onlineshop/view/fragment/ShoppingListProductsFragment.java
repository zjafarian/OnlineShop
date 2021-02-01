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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListCategoriesAdapter;
import com.example.onlineshop.adapter.ListOrdersCustomerAdapter;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.databinding.FragmentShoppingListProductsBinding;
import com.example.onlineshop.viewmodel.ShoppingListProductsViewModel;

import java.util.List;


public class ShoppingListProductsFragment extends Fragment {
    public static final String ARGS_SELECT_LIST_PRODUCTS = "selectListProducts";
    public static final String ARGS_ORDER_ID = "orderId";
    public static final String ARGS_CATEGORY_ID = "categoryId";
    private FragmentShoppingListProductsBinding mBinding;
    private ShoppingListProductsViewModel mViewModel;
    public static final String ARGS_WHICH_PAGE = "whichPage";
    private ListOrdersCustomerAdapter mAdapter;


    public ShoppingListProductsFragment() {
        // Required empty public constructor
    }


    public static ShoppingListProductsFragment newInstance() {
        ShoppingListProductsFragment fragment = new ShoppingListProductsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mViewModel = new ViewModelProvider(requireActivity()).get(ShoppingListProductsViewModel.class);
        mViewModel.getIsLoginLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mBinding.layoutNotLogin.setVisibility(View.GONE);
                    mBinding.layoutShoppingListProducts.setVisibility(View.VISIBLE);
                } else {
                    mBinding.layoutNotLogin.setVisibility(View.VISIBLE);
                    mBinding.layoutShoppingListProducts.setVisibility(View.GONE);
                }
            }
        });


        mViewModel.getOrdersCustomerLiveData().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                updateUI(orders);

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_shopping_list_products,
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

    private void listener() {
        mBinding.loginCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginPage();
            }
        });

    }

    private void onItemClick() {
        mAdapter.setOnItemClickOrder(new ListOrdersCustomerAdapter.OnItemClickOrder() {
            @Override
            public void onItemClicked(List<Products> products, int orderId) {
                startListProducts(NetworkParams.ORDER, orderId);
            }
        });


    }

    private void startLoginPage() {
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_WHICH_PAGE, "shopping");

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.login_sign_up_fragment_des, bundle);
    }

    private void initRecycle() {
        mBinding.recycleViewListShoppingProduct.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ListOrdersCustomerAdapter();
        mBinding.recycleViewListShoppingProduct.setAdapter(mAdapter);
    }

    private void updateUI(List<Order> orders) {
        mAdapter.setData(orders, mViewModel.getProducts());

    }

    private void startListProducts(String selectFilter, int idOrder) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_SELECT_LIST_PRODUCTS, selectFilter);
        bundle.putInt(ARGS_ORDER_ID, idOrder);
        bundle.putInt(ARGS_CATEGORY_ID, 0);

        Navigation.findNavController(mBinding.getRoot()).navigate
                (R.id.list_products_fragment_des, bundle);
    }
}