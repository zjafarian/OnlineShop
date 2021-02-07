package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
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
    private OnItemClick mOnItemClick;
    private OnItemEditClick mOnItemEditClick;
    private OnItemDeleteClick mOnItemDeleteClick;


    public ListAddressAdapter() {
    }

    public void setData(List<Address> addresses) {
        mAddressList = addresses;
        notifyDataSetChanged();
    }

    public void onItemClicked(OnItemClick onItemClick) {
        this.mOnItemClick = onItemClick;
    }

    public void onItemEditClicked(OnItemEditClick onItemEditClick) {
        this.mOnItemEditClick = onItemEditClick;
    }

    public void onItemDeleteClicked(OnItemDeleteClick onItemDeleteClick) {
        this.mOnItemDeleteClick = onItemDeleteClick;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


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

        if (mOnItemClick != null) {
            ((AddressHolder) holder)
                    .itemView
                    .findViewById(R.id.card_view_one_address)
                    .setOnClickListener(v -> mOnItemClick.onItemClicked(item));
        }

        if (mOnItemEditClick != null) {
            ((AddressHolder) holder)
                    .itemView
                    .findViewById(R.id.txt_view_edit_address)
                    .setOnClickListener(v -> mOnItemEditClick.onItemEditClicked(item));
        }

        if (mOnItemDeleteClick != null) {
            ((AddressHolder) holder)
                    .itemView
                    .findViewById(R.id.txt_view_delete_address)
                    .setOnClickListener(v -> mOnItemDeleteClick.onItemDeleteClicked(item));
        }


    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    class AddressHolder extends RecyclerView.ViewHolder {
        private RowAddressItemBinding mBinding;

        public AddressHolder(RowAddressItemBinding rowAddressItemBinding) {
            super(rowAddressItemBinding.getRoot());
            mBinding = rowAddressItemBinding;
        }

        public void bindHolder(Address address) {
            mBinding.setAddress(address);
            mBinding.executePendingBindings();

        }
    }

    public interface OnItemClick {
        void onItemClicked(Address address);
    }

    public interface OnItemEditClick {
        void onItemEditClicked(Address address);
    }

    public interface OnItemDeleteClick {
        void onItemDeleteClicked(Address address);
    }

}
