package com.example.onlineshop.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ListReviewAdapter;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.models.Review;
import com.example.onlineshop.databinding.FragmentReviewBinding;
import com.example.onlineshop.viewmodel.ProductDetailViewModel;
import com.example.onlineshop.viewmodel.ReviewViewModel;

import java.util.List;

public class ReviewFragment extends Fragment {
    public static final String ARGS_ID_PRODUCT_REVIEW = "idProductReview";
    private FragmentReviewBinding mBinding;
    private ListReviewAdapter mAdapter;
    private int mProductId;
    private ReviewViewModel mViewModel;


    public ReviewFragment() {
        // Required empty public constructor
    }


    public static ReviewFragment newInstance(int idProduct) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_ID_PRODUCT_REVIEW, idProduct);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProductId = getArguments().getInt(ARGS_ID_PRODUCT_REVIEW);

        }

        mViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);
        mViewModel.fetchReviews(mProductId);


        mViewModel.getListReviewLiveData().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                updateUI(reviews);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_review,
                container,
                false);

        initRecycle();
        listener();

        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setObservers();
    }

    private void setObservers() {

        mViewModel.getReviewLiveData().observe(getViewLifecycleOwner(), new Observer<Review>() {
            @Override
            public void onChanged(Review review) {
                mViewModel.fetchReviews(mProductId);
            }
        });
    }

    private void initRecycle() {
        mBinding.recycleViewReviews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ListReviewAdapter();
        mBinding.recycleViewReviews.setAdapter(mAdapter);
    }

    private void updateUI(List<Review> reviews) {
        if (reviews.size() != 0 && reviews != null)
            mAdapter.setData(reviews);
    }

    private void listener() {
        mBinding.btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.fetchCreateReview(mBinding.editTextReview.getText().toString());
            }
        });

        mBinding.btnEditReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.fetchEditReview(mBinding.editTextReview.getText().toString());
            }
        });

        mBinding.btnDeleteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.fetchDeleteReview();
            }
        });

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackNavigation();
            }
        });
    }

    private void setBackNavigation() {
        NavBackStackEntry navBackStackEntry = Navigation.findNavController
                (mBinding.getRoot()).getPreviousBackStackEntry();
        Navigation.findNavController(mBinding.getRoot()).navigate
                (navBackStackEntry.getDestination().getId());
    }


}