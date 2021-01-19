package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;


import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.RowShowProductBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ListProductsHomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Products> mProductList = new ArrayList<>();
    private OnItemClickProduct mOnItemClick;


    public ListProductsHomePageAdapter() {}

    public void setData(List<Products> productList) {
        for (int i = 0; i < 4; i++) {
            mProductList.add(productList.get(i));
        }
        notifyDataSetChanged();
    }

    public void onItemClickedProduct(OnItemClickProduct onItemClick){
        this.mOnItemClick = onItemClick;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowShowProductBinding listRowProductBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_show_product,
                parent, false);

        return new ProductHolder(listRowProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Products item = mProductList.get(position);
        ((ListProductsHomePageAdapter.ProductHolder) holder).bindProduct(item);

        if (mOnItemClick != null)
            ((ProductHolder) holder).itemView.setOnClickListener
                    (v -> mOnItemClick.onItemClicked(item));

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private final RowShowProductBinding mBinding;

        public ProductHolder(RowShowProductBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }

        public void bindProduct(Products products) {
            mBinding.setProduct(products);
            mBinding.executePendingBindings();
            if (products.getImages().size() != 0 ) {
                Picasso.get()
                        .load(products.getImages().get(0).getSrc())
                        .placeholder(R.drawable.place_holder_online_shop)
                        .into(mBinding.productImage);
            }


        }
    }

    public interface OnItemClickProduct{
        void onItemClicked(Products products);
    }


}
