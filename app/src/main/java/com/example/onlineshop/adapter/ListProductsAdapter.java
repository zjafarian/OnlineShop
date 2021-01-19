package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.ItemCategoryListBinding;
import com.example.onlineshop.databinding.ItemProductListBinding;
import com.example.onlineshop.databinding.RowShowProductBinding;
import com.example.onlineshop.viewmodel.ListProductsViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Products> mProducts = new ArrayList<>();


    public ListProductsAdapter() {
    }

    public void setData(List<Products> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        ItemProductListBinding itemProductListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_product_list,
                parent,
                false);


        return new ListProductsViewHolder(itemProductListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Products item = mProducts.get(position);
        ((ListProductsAdapter.ListProductsViewHolder) holder).bindProduct(item);
    }


    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class ListProductsViewHolder extends RecyclerView.ViewHolder {
        private ItemProductListBinding mBinding;

        public ListProductsViewHolder(ItemProductListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindProduct(Products product) {
            mBinding.setProduct(product);
            mBinding.executePendingBindings();
            if (product.getImages().size() != 0 ) {
                Picasso.get()
                        .load(product.getImages().get(0).getSrc())
                        .placeholder(R.drawable.place_holder_online_shop)
                        .into(mBinding.productImage);
            }
        }

    }
}
