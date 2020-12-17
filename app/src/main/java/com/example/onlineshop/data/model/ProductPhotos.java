package com.example.onlineshop.data.model;

public class ProductPhotos {
    private int mPhotoId;
    private String mPhotoName;
    private String mPhotoSrc;

    public ProductPhotos(int photoId, String photoName, String photoSrc) {
        mPhotoId = photoId;
        mPhotoName = photoName;
        mPhotoSrc = photoSrc;
    }

    public int getPhotoId() {
        return mPhotoId;
    }

    public String getPhotoName() {
        return mPhotoName;
    }

    public String getPhotoSrc() {
        return mPhotoSrc;
    }
}
