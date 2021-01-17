package com.example.onlineshop.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Categories;
import com.example.onlineshop.databinding.ItemCategoryHomePageListBinding;
import com.example.onlineshop.databinding.ItemCategoryListBinding;
import com.example.onlineshop.viewmodel.HomePageViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

public class ListCategoriesHomePageAdapter extends
        RecyclerView.Adapter<ListCategoriesHomePageAdapter.ListCategoriesHomePageHolder> {

    private LifecycleOwner mOwner;
    private HomePageViewModel mViewModel;
    private List<Categories> mCategoriesList = new ArrayList<>();
    private Bitmap mItemBitmap;

    public ListCategoriesHomePageAdapter(LifecycleOwner owner, HomePageViewModel homePageViewModel,
                                         List<Categories> categories) {
        mOwner = owner;
        mViewModel = homePageViewModel;
        mCategoriesList = categories;
    }

    @NonNull
    @Override
    public ListCategoriesHomePageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mViewModel.getApplication());
        ItemCategoryHomePageListBinding itemCategoryHomePageListBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_category_home_page_list,
                parent,
                false);
        return new ListCategoriesHomePageHolder(itemCategoryHomePageListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCategoriesHomePageHolder holder, int position) {
        holder.bindCategories(position);

    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    class ListCategoriesHomePageHolder extends RecyclerView.ViewHolder {
        private ItemCategoryHomePageListBinding mBinding;
        private Categories mCategory;

        public ListCategoriesHomePageHolder(ItemCategoryHomePageListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindCategories(int position) {
            mCategory = mCategoriesList.get(position);

            Picasso.get()
                    .load(mCategory.getImage().getSrc())
                    .placeholder(R.drawable.place_holder_online_shop)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            mItemBitmap = bitmap;

                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
        /*    Bitmap imageScale = Bitmap.createScaledBitmap(mItemBitmap, 50, 50, true);
            mBinding.imgViewCategory.setImageBitmap(imageScale);


            mBinding.categoryName.setText(mCategory.getName());
*/

        }
    }


}
