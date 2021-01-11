package com.example.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.databinding.ItemCategoryListBinding;
import com.example.onlineshop.viewmodel.CategoryViewModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ListCategoriesAdapter extends
        RecyclerView.Adapter<ListCategoriesAdapter.CategoryHolder> {

    private LifecycleOwner mOwner;
    private CategoryViewModel mViewModel;
    private List<Categories> mCategoriesList= new ArrayList<>();



    public ListCategoriesAdapter(LifecycleOwner owner,
                                 CategoryViewModel categoryViewModel,
                                 List<Categories> categories) {
        mOwner = owner;
        mViewModel = categoryViewModel;
        mCategoriesList = categories;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mViewModel.getApplication());
        ItemCategoryListBinding itemCategoryListBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_category_list,
                parent,
                false);

        return new CategoryHolder(itemCategoryListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.bindCategory(position);

    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{
        private ItemCategoryListBinding mBinding;
        private Categories mCategories;

        public CategoryHolder(ItemCategoryListBinding itemCategoryListBinding) {
            super(itemCategoryListBinding.getRoot());
            mBinding = itemCategoryListBinding;
        }

        public void bindCategory(int position){
            mCategories = mCategoriesList.get(position);
            Picasso.get()
                    .load(mViewModel.getImageSrc(mCategories))
                    .placeholder(R.drawable.place_holder_online_shop)
                    .into(mBinding.categoryImage);

            mBinding.categoryTitle.setText(mCategories.getName());
            mBinding.productCount.setText(String.valueOf(mCategories.getCount()));

        }
    }
}
