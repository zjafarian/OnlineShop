package com.example.onlineshop.data.model;

public class Categories {

    private int mCategoryId;
    private String mCategoryName;
    private String mCategorySlug;

    public Categories(int categoryId, String categoryName, String categorySlug) {
        mCategoryId = categoryId;
        mCategoryName = categoryName;
        mCategorySlug = categorySlug;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public String getCategorySlug() {
        return mCategorySlug;
    }
}
