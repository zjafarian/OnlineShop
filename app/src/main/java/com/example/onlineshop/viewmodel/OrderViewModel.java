package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlineshop.data.database.Address;
import com.example.onlineshop.data.network.models.LineItem;
import com.example.onlineshop.data.network.models.Order;
import com.example.onlineshop.data.network.models.Products;
import com.example.onlineshop.data.network.remote.NetworkParams;
import com.example.onlineshop.data.repository.AddressRepository;
import com.example.onlineshop.data.repository.CustomerRepository;
import com.example.onlineshop.data.repository.ShopRepository;
import com.example.onlineshop.utils.SharedPreferencesOnlineShop;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends AndroidViewModel {
    private AddressRepository mAddressRepository;
    private LiveData<Address> mAddressLiveData;
    private ShopRepository mShopRepository;
    private CustomerRepository mCustomerRepository;
    private LiveData<List<Products>> mShoppingCartCustomer;
    private double mDiscountTotal=0.0;
    private long mTotal = 0L;
    private boolean mCorrectCode;
    private String mFullAddress;


    public OrderViewModel(@NonNull Application application) {
        super(application);
        mAddressRepository = AddressRepository.getInstance(application);
        mShopRepository = ShopRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance();
        mAddressLiveData = mAddressRepository.getSelectAddress();
        mShoppingCartCustomer = mShopRepository.getProductsShoppingCartLiveData();
    }

    public void checkCouponCode(String code) {

        if (code == null || code.equals(""))
            showToast("کد تخفیف را وارد نکردید");
        else {
            if (!code.equals(NetworkParams.COUPON_CODE)) {
                showToast("کد تخفیف اشتباه است");
            } else if (code.equals(NetworkParams.COUPON_CODE)) {
                mDiscountTotal = mTotal / 100.0;
                mDiscountTotal = mDiscountTotal * 10;

                mCorrectCode = true;
            }
        }
    }

    public String calculatorPrices(List<Products> products) {
        long sum = 0L;
        if (products != null && products.size() != 0) {
            for (Products product : products) {
                long price = Long.valueOf(product.getPrice());
                sum += price;
            }
        } else return "";
        mTotal = sum;
        return String.valueOf(sum);
    }

    public String calculatorPricesAfterDiscount() {
        double dis = mTotal - mDiscountTotal;
        return String.valueOf(dis);
    }

    public void setFullAddress(String fullAddress) {
        mFullAddress = fullAddress;
    }

    public void submitOrder() {

        List<Products> products = mShoppingCartCustomer.getValue();
        int idCustomer = SharedPreferencesOnlineShop.getCustomerId(getApplication());
        List<LineItem> lineItems = new ArrayList<>();

        if (products.size() != 0 && products != null) {
            for (Products productBuy : products) {
                LineItem lineItem = new LineItem();
                lineItem.setProductId(productBuy.getId());
                lineItem.setQuantity(1);
                lineItems.add(lineItem);
            }
        }

        Order order = new Order();
        order.setLineItems(lineItems);
        order.setCustomerId(idCustomer);
        if (mCorrectCode && mDiscountTotal != 0)
            order.setDiscountTotal(String.valueOf(mDiscountTotal));

        if (mFullAddress == null || mFullAddress.equals(""))
            showToast("لطفا آدرس را وارد کنید");
        else {
            order.setAddressOne(mFullAddress);
            mCustomerRepository.createOrder(order);
            afterSubmit();
            fetchGetOrders();
        }


    }

    private void afterSubmit() {
        showToast("ثبت سفارش با موفقیت انجام شد");
        mShopRepository.removeAllProductsShoppingCart();
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplication(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    private void fetchGetOrders() {
        int idCustomer = SharedPreferencesOnlineShop.getCustomerId(getApplication());
        mCustomerRepository.getOrdersCustomer(idCustomer);
    }


    public LiveData<Address> getAddressLiveData() {
        return mAddressLiveData;
    }

    public LiveData<List<Products>> getShoppingCartCustomer() {
        return mShoppingCartCustomer;
    }

    public boolean isCorrectCode() {
        return mCorrectCode;
    }
}
