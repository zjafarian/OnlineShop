package com.example.onlineshop.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.databinding.ItemCategoryHomePageListBinding;
import com.example.onlineshop.databinding.ItemCategoryListBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

public class ListCategoriesHomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Categories> mCategoriesList;
    private OnItemClickCategory mOnItemClick;

    public ListCategoriesHomePageAdapter() {
        mCategoriesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryHomePageListBinding itemCategoryHomePageListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_category_home_page_list,
                parent,
                false);
        return new ListCategoriesHomePageHolder(itemCategoryHomePageListBinding);
    }

    public void setData(List<Categories> categories) {
        mCategoriesList = categories;
        notifyDataSetChanged();
    }

    public void onItemClickedCategory
            (ListCategoriesHomePageAdapter.OnItemClickCategory onItemClick){
        this.mOnItemClick = onItemClick;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Categories item = mCategoriesList.get(position);
        ((ListCategoriesHomePageHolder) holder).bindCategories(item);

        if (mOnItemClick != null)
            ((ListCategoriesHomePageHolder) holder).itemView.setOnClickListener
                    (v -> mOnItemClick.onItemClicked(item,position));
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    static class ListCategoriesHomePageHolder extends RecyclerView.ViewHolder {
        private final ItemCategoryHomePageListBinding mBinding;

        public ListCategoriesHomePageHolder(ItemCategoryHomePageListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindCategories(Categories categories) {
            mBinding.setCategory(categories);
            mBinding.executePendingBindings();
        }
    }

    public interface OnItemClickCategory{
        void onItemClicked(Categories category,int position);
    }


}
