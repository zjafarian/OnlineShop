package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlineshop.data.network.models.Customer;
import com.example.onlineshop.data.network.models.Review;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.List;

public class ReviewViewModel extends AndroidViewModel {
    private ShopRepository mShopRepository;
    private CustomerRepository mCustomerRepository;
    private LiveData<List<Review>> mListReviewLiveData;
    private LiveData<Review> mReviewLiveData;
    private int mProductId;

    public ReviewViewModel(@NonNull Application application) {
        super(application);

        mShopRepository = ShopRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance();
        //mListReviewLiveData = mShopRepository.getListReviewsLiveData();
        mReviewLiveData = mShopRepository.getReviewLiveData();
    }


    public void fetchReviews(int id) {
        mProductId = id;
        mShopRepository.fetchListReviewByProductId(id);

     /*   new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 3000);*/
        mListReviewLiveData = mShopRepository.getListReviewsLiveData();

    }


    public void fetchCreateReview(String reviewText) {
        int idCustomer = SharedPreferencesOnlineShop.getCustomerId(getApplication());
        mCustomerRepository.getOrdersCustomer(idCustomer);
        Customer customer = mCustomerRepository.getCustomerLogin().getValue();

        if (reviewText == null || reviewText.equals("")) {
            showToast("هیچ نظری وارد نکردید");
        } else {
            Review review = new Review(mProductId, reviewText, "zahra", customer.getEmail(), 4);
            mShopRepository.fetchCreateReview(review);
            showToast("لطفا کمی صبر کنید");
            mReviewLiveData = mShopRepository.getReviewLiveData();
            showToast("ثبت نظر با موفقیت انجام شد");
        }


    }



    public void fetchEditReview(String reviewText) {
        Review review = mReviewLiveData.getValue();
        review.setReview(reviewText);
        review.setRating(3);
        mShopRepository.fetchEditReview(review);
        showToast("لطفا کمی منتظر باشید");
        mReviewLiveData = mShopRepository.getReviewLiveData();
        showToast("ویرایش با موفقیت انجام شد");
    }

    public void fetchDeleteReview() {
        Review review = mReviewLiveData.getValue();
        mShopRepository.fetchDeleteReview(review);
        //showToast("لطفا کمی منتظر باشید");
        mReviewLiveData = mShopRepository.getReviewLiveData();
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplication(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    public LiveData<List<Review>> getListReviewLiveData() {
        return mListReviewLiveData;
    }

    public LiveData<Review> getReviewLiveData() {
        return mReviewLiveData;
    }
}
