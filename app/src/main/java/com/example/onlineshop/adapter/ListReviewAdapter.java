package com.example.onlineshop.adapter;

import android.inputmethodservice.Keyboard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.data.network.models.Review;
import com.example.onlineshop.data.network.models.ReviewerAvatarUrls;
import com.example.onlineshop.databinding.RowItemReviewBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Review> mReviewList = new ArrayList<>();

    public ListReviewAdapter() {
    }

    public void setData(List<Review> reviews) {
        mReviewList = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemReviewBinding rowItemReviewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_item_review,
                parent,
                false);
        return new ReviewHolder(rowItemReviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Review item = mReviewList.get(position);
        if(item != null)
            ((ListReviewAdapter.ReviewHolder) holder).bindReview(item);

    }

    @Override
    public int getItemCount() {
        return mReviewList.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder {
        RowItemReviewBinding mBinding;

        public ReviewHolder(RowItemReviewBinding rowItemReviewBinding) {
            super(rowItemReviewBinding.getRoot());
            mBinding = rowItemReviewBinding;

        }

        public void bindReview(Review review) {
            if (review != null) {
                mBinding.setReview(review);
                mBinding.executePendingBindings();
            }


            if (review.getReviewerAvatarUrls().getJsonMember48() != null)
                Picasso.get()
                        .load(review.getReviewerAvatarUrls().getJsonMember48())
                        .placeholder(R.drawable.ic_user_full)
                        .into(mBinding.imgViewReviewerAvatar);


        }
    }
}
