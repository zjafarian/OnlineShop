package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;


import com.example.onlineshop.R;
import com.example.onlineshop.data.model.Product;
import com.example.onlineshop.databinding.RowShowProductBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.google.gson.internal.$Gson$Preconditions;
import com.squareup.picasso.Picasso;

import java.util.List;


public class LastProductsAdapter extends RecyclerView.Adapter<LastProductsAdapter.ProductHolder> {
    private final HomePageViewModel mHomePageViewModel;
    private final LifecycleOwner mOwner;
    private  Product mProduct;
    private List<Product> mProductList;

    public LastProductsAdapter(LifecycleOwner owner, HomePageViewModel homePageViewModel) {
        mHomePageViewModel = homePageViewModel;
        mProductList=mHomePageViewModel.getProductsList();
        mOwner = owner;

    }


    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mHomePageViewModel.getApplication());
        RowShowProductBinding listRowProductBinding = DataBindingUtil.inflate(inflater,
                R.layout.row_show_product,
                parent,false);

        return new ProductHolder(listRowProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        holder.bindProduct(position);
    }

    @Override
    public int getItemCount() {
        return mHomePageViewModel.getProductsList().size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private final RowShowProductBinding mRowShowProductBinding;

        public ProductHolder(RowShowProductBinding rowShowProductBinding) {
            super(rowShowProductBinding.getRoot());
            mRowShowProductBinding =rowShowProductBinding;
            mRowShowProductBinding.setHomePageViewModel(mHomePageViewModel);
            mRowShowProductBinding.setLifecycleOwner(mOwner);
        }

        public void bindProduct(int position){
            mProduct = mHomePageViewModel.getProductsList().get(position);
            Picasso.get()
                    .load(mHomePageViewModel.getFirstImageSrc(mProduct))
                    .placeholder(R.drawable.place_holder_online_shop)
                    .into(mRowShowProductBinding.productImage);

            mRowShowProductBinding.productTitle.setText(mProduct.getNameProduct());
            mRowShowProductBinding.productPrice.setText(String.valueOf(mProduct.getPriceProduct()));
        }
    }


}
