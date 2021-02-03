package com.example.onlineshop.data.network.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Coupon {

    @SerializedName("id")
    private int id;

    @SerializedName("code")
    private String code;

    @SerializedName("minimum_amount")
    private String minimumAmount;

    @SerializedName("exclude_sale_items")
    private boolean excludeSaleItems;

    @SerializedName("individual_use")
    private boolean individualUse;

    @SerializedName("amount")
    private String amount;

    @SerializedName("discount_type")
    private String discountType;

    public Coupon(String code, String minimumAmount, boolean excludeSaleItems, boolean individualUse, String amount, String discountType) {
        this.code = code;
        this.minimumAmount = minimumAmount;
        this.excludeSaleItems = excludeSaleItems;
        this.individualUse = individualUse;
        this.amount = amount;
        this.discountType = discountType;
    }

    public String getMinimumAmount() {
        return minimumAmount;
    }

    public boolean isExcludeSaleItems() {
        return excludeSaleItems;
    }

    public boolean isIndividualUse() {
        return individualUse;
    }

    public int getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getDiscountType() {
        return discountType;
    }


}