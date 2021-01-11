package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.ItemProductListBinding;
import com.example.onlineshop.databinding.RowShowProductBinding;
import com.example.onlineshop.viewmodel.ListProductsViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListProductsAdapter extends RecyclerView.Adapter<ListProductsAdapter.ListProductsViewHolder> {
    private LifecycleOwner mOwner;
    private ListProductsViewModel mViewModel;
    private List<Products> mProducts = new ArrayList<>();


    public ListProductsAdapter(LifecycleOwner owner,
                               List<Products> products,
                               ListProductsViewModel viewModel) {
        mOwner = owner;
        mViewModel = viewModel;
        mProducts = products;
    }

    @NonNull
    @Override
    public ListProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(mViewModel.getApplication());
        ItemProductListBinding itemProductListBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_product_list,
                parent,
                false);

        return new ListProductsViewHolder(itemProductListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductsViewHolder holder, int position) {
        holder.bindProduct(position);


    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class ListProductsViewHolder extends RecyclerView.ViewHolder {
        private ItemProductListBinding mBinding;
        private Products mProduct;


        public ListProductsViewHolder(ItemProductListBinding itemProductListBinding) {
            super(itemProductListBinding.getRoot());
            mBinding = itemProductListBinding;
            mBinding.setListProductsViewModel(mViewModel);
            mBinding.setLifecycleOwner(mOwner);
        }


        public void bindProduct(int position) {
            mBinding.setPosition(position);
            mProduct = mProducts.get(position);
            Picasso.get()
                    .load(mViewModel.getFirstImageSrc(mProduct))
                    .placeholder(R.drawable.place_holder_online_shop)
                    .into(mBinding.productImage);

        }
    }


}
