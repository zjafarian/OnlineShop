package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;


import com.example.onlineshop.R;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.databinding.RowShowProductBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ListProductsHomePageAdapter extends RecyclerView.Adapter
        <ListProductsHomePageAdapter.ProductHolder> {

    private final HomePageViewModel mHomePageViewModel;
    private final LifecycleOwner mOwner;
    private Product mProduct;
    private List<Product> mProductList = new ArrayList<>();

    public ListProductsHomePageAdapter(
            LifecycleOwner owner,
            List<Product> products,
            HomePageViewModel homePageViewModel) {

        mHomePageViewModel = homePageViewModel;
        for (int i = 0; i < 4; i++) {
            mProductList.add(products.get(i));
        }
        mOwner = owner;
    }


    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mHomePageViewModel.getApplication());
        RowShowProductBinding listRowProductBinding = DataBindingUtil.inflate(inflater,
                R.layout.row_show_product,
                parent, false);

        return new ProductHolder(listRowProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        holder.bindProduct(position);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private final RowShowProductBinding mRowShowProductBinding;

        public ProductHolder(RowShowProductBinding rowShowProductBinding) {
            super(rowShowProductBinding.getRoot());
            mRowShowProductBinding = rowShowProductBinding;
            mRowShowProductBinding.setHomePageViewModel(mHomePageViewModel);
            mRowShowProductBinding.setLifecycleOwner(mOwner);
        }

        public void bindProduct(int position) {
            mProduct = mProductList.get(position);
            Picasso.get()
                    .load(mHomePageViewModel.getFirstImageSrc(mProduct))
                    .placeholder(R.drawable.place_holder_online_shop)
                    .into(mRowShowProductBinding.productImage);

            String title = mProduct.getNameProduct();


            mRowShowProductBinding.productTitle.setText(mProduct.getNameProduct());
            mRowShowProductBinding.productPrice.setText(String.valueOf(mProduct.getPriceProduct()));
        }
    }


}
