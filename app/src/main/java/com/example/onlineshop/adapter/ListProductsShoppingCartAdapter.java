package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.RowProduntInShopCartBinding;

import java.util.ArrayList;
import java.util.List;

public class ListProductsShoppingCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Products> mProductsList = new ArrayList<>();
    private OnItemClickProduct mOnItemClick;
    private onItemClickDeleteProductFromList mOnDeleteProduct;

    public ListProductsShoppingCartAdapter() {
    }

    public void setData(List<Products> products) {
        mProductsList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        RowProduntInShopCartBinding rowProduntInShopCartBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_produnt_in_shop_cart,
                parent,
                false);


        return new ListProductsShoppingCartHolder(rowProduntInShopCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Products item = mProductsList.get(position);
        ((ListProductsShoppingCartHolder) holder).bindHolder(item);

        if (mOnItemClick != null)
            ((ListProductsShoppingCartHolder) holder).itemView
                    .findViewById(R.id.card_view_product_item_in_shop_cart).setOnClickListener
                    ((v -> mOnItemClick.onItemClicked(item, position)));


        if (mOnDeleteProduct != null)
            ((ListProductsShoppingCartHolder) holder).itemView
                    .findViewById(R.id.txt_view_delete_product_from_shop_cart).setOnClickListener
                    ((v -> mOnDeleteProduct.onDeleteSelect(item, position)));

    }


    @Override
    public int getItemCount() {
        return mProductsList.size();
    }

    class ListProductsShoppingCartHolder extends RecyclerView.ViewHolder {
        private RowProduntInShopCartBinding mBinding;


        public ListProductsShoppingCartHolder(RowProduntInShopCartBinding inShopCartBinding) {
            super(inShopCartBinding.getRoot());
            mBinding = inShopCartBinding;
        }

        public void bindHolder(Products products) {
            mBinding.setProduct(products);
        }


    }

    public interface OnItemClickProduct {
        void onItemClicked(Products products, int position);
    }

    public interface onItemClickDeleteProductFromList {
        void onDeleteSelect(Products products, int position);
    }

}
