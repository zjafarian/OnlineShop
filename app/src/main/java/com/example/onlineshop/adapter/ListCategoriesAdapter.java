package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.databinding.ItemCategoryListBinding;
import java.util.ArrayList;
import java.util.List;

public class ListCategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Categories> mCategoriesList= new ArrayList<>();
    private OnItemClick mOnItemClick;

    public ListCategoriesAdapter() {

    }

    public void setData(List<Categories> categories) {
        mCategoriesList = categories;
        notifyDataSetChanged();
    }

    public void onItemClicked(OnItemClick onItemClick){
        this.mOnItemClick = onItemClick;
    }

    @NonNull
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCategoryListBinding itemCategoryListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_category_list,
                parent,
                false);

        return new CategoryHolder(itemCategoryListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Categories item = mCategoriesList.get(position);
        ((ListCategoriesAdapter.CategoryHolder) holder).bindCategory(item);
       /* if (mOnItemClick != null)
            ((CategoryHolder) holder).itemView.findViewById
                    (R.id.text_view_all_category).setOnClickListener
                    (v -> mOnItemClick.onItemClicked(item, position));*/
    }



    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{
        private ItemCategoryListBinding mBinding;

        public CategoryHolder(ItemCategoryListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindCategory(Categories categories){
            mBinding.setCategory(categories);
            mBinding.executePendingBindings();


        }
    }

    public interface OnItemClick{
        void onItemClicked(Categories category, int position);
    }
}
