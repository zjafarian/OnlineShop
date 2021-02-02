package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.data.database.Address;
import com.example.onlineshop.databinding.ItemProductListBinding;
import com.example.onlineshop.databinding.RowAddressItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ListAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Address> mAddressList = new ArrayList<>();

    public ListAddressAdapter() {
    }

    public void setData(List<Address> addresses){
        mAddressList = addresses;
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


        RowAddressItemBinding rowAddressItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_address_item,
                parent,
                false);



        return new AddressHolder(rowAddressItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Address item = mAddressList.get(position);
        ((ListAddressAdapter.AddressHolder) holder).bindHolder(item);


    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    class AddressHolder extends RecyclerView.ViewHolder{
        private RowAddressItemBinding mBinding;

        public AddressHolder(RowAddressItemBinding rowAddressItemBinding) {
            super(rowAddressItemBinding.getRoot());
            mBinding = rowAddressItemBinding;
        }

        public void bindHolder (Address address){
            mBinding.setAddress(address);

        }
    }
}
