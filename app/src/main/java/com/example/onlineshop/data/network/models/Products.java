package com.example.onlineshop.data.network.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Products implements Comparable<Products> {


    @SerializedName("price")
    private String price;

    @SerializedName("id")
    private int id;

    @SerializedName("slug")
    private String slug;

    @SerializedName("shipping_required")
    private boolean shippingRequired;

    @SerializedName("images")
    private List<Images> images;

    @SerializedName("name")
    private String name;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("short_description")
    private String shortDescription;

    @SerializedName("menu_order")
    private int menuOrder;

    @SerializedName("description")
    private String description;

    @SerializedName("regular_price")
    private String regularPrice;

    @SerializedName("categories")
    private List<Categories> categories;

    @SerializedName("total_sales")
    private int totalSales;

    @SerializedName("on_sale")
    private boolean onSale;

    @SerializedName("date_created")
    private String dateCreated;

    @SerializedName("average_rating")
    private String averageRating;

    @SerializedName("sale_price")
    private String salePrice;

    @SerializedName("related_ids")
    private List<Integer> relatedIds;




    public Products(int id, String name, String description, String shortDescription,
                    String price, String regularPrice, String salePrice, boolean onSale,
                    List<Images> images, List<Categories> categories,
                    List<Integer> relatedIds,int totalSales) {

        this.price = price;
        this.id = id;
        this.images = images;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.regularPrice = regularPrice;
        this.categories = categories;
        this.onSale = onSale;
        this.salePrice = salePrice;
        this.relatedIds = relatedIds;
        this.totalSales = totalSales;
    }

    public String getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public boolean isShippingRequired() {
        return shippingRequired;
    }

    public List<Images> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public int getMenuOrder() {
        return menuOrder;
    }

    public String getDescription() {
        return description;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }


    @Override
    public int compareTo(Products o) {

        if(this.totalSales==o.totalSales)
            return 0;
        else if(this.totalSales>o.totalSales)
            return 1;
        else
            return -1;
    }

}