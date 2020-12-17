package com.example.onlineshop.data.model;

import java.util.Date;

public class Product {

    private int mIdProduct;
    private String mNameProduct;
    private String mSlugProduct;
    private String mDescriptionProduct;
    private String mShortDescriptionProduct;
    private String mUriProduct;
    private String mDateCreatedProduct;
    private String mDateCreatedGmtProduct;
    private String mDateModifiedProduct;
    private String mDateModifiedGmtProduct;
    private long mPriceProduct;
    private boolean mOnSaleProduct;
    private long mRegularPriceProduct;
    private long mSalePriceProduct;
    private int[] mRelatedProducts;
    private int mRateProduct;
    private ProductPhotos[] mProductPhotos;
    private Categories[] mCategories;

    public Product() {
    }

    public Product(int idProduct, String nameProduct, String slugProduct, String descriptionProduct,
                   String shortDescriptionProduct, String uriProduct, String dateCreatedProduct,
                   String dateCreatedGmtProduct, String dateModifiedProduct, String dateModifiedGmtProduct,
                   long priceProduct, boolean onSaleProduct, long regularPriceProduct,
                   long salePriceProduct, int[] relatedProducts, int rateProduct,
                   ProductPhotos[] productPhotos, Categories[] categories) {


        mIdProduct = idProduct;
        mNameProduct = nameProduct;
        mSlugProduct = slugProduct;
        mDescriptionProduct = descriptionProduct;
        mShortDescriptionProduct = shortDescriptionProduct;
        mUriProduct = uriProduct;
        mDateCreatedProduct = dateCreatedProduct;
        mDateCreatedGmtProduct = dateCreatedGmtProduct;
        mDateModifiedProduct = dateModifiedProduct;
        mDateModifiedGmtProduct = dateModifiedGmtProduct;
        mPriceProduct = priceProduct;
        mOnSaleProduct = onSaleProduct;
        mRegularPriceProduct = regularPriceProduct;
        mSalePriceProduct = salePriceProduct;
        mRelatedProducts = relatedProducts;
        mRateProduct = rateProduct;
        mProductPhotos = productPhotos;
        mCategories = categories;
    }

    public int getIdProduct() {
        return mIdProduct;
    }

    public String getNameProduct() {
        return mNameProduct;
    }

    public String getSlugProduct() {
        return mSlugProduct;
    }

    public String getDescriptionProduct() {
        return mDescriptionProduct;
    }

    public String getShortDescriptionProduct() {
        return mShortDescriptionProduct;
    }

    public String getUriProduct() {
        return mUriProduct;
    }

    public String getDateCreatedProduct() {
        return mDateCreatedProduct;
    }

    public String getDateCreatedGmtProduct() {
        return mDateCreatedGmtProduct;
    }

    public String getDateModifiedProduct() {
        return mDateModifiedProduct;
    }

    public String getDateModifiedGmtProduct() {
        return mDateModifiedGmtProduct;
    }

    public long getPriceProduct() {
        return mPriceProduct;
    }

    public boolean isOnSaleProduct() {
        return mOnSaleProduct;
    }

    public long getRegularPriceProduct() {
        return mRegularPriceProduct;
    }

    public long getSalePriceProduct() {
        return mSalePriceProduct;
    }

    public int[] getRelatedProducts() {
        return mRelatedProducts;
    }

    public int getRateProduct() {
        return mRateProduct;
    }

    public ProductPhotos[] getProductPhotos() {
        return mProductPhotos;
    }

    public Categories[] getCategories() {
        return mCategories;
    }
}
