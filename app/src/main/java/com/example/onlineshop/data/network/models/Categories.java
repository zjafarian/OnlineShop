package com.example.onlineshop.data.network.models;

import com.google.gson.annotations.SerializedName;

public class Categories {


    @SerializedName("image")
    private Images image;

    @SerializedName("menu_order")
    private int menuOrder;

    @SerializedName("name")
    private String name;

    @SerializedName("count")
    private int count;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    @SerializedName("slug")
    private String slug;

    public Categories(int id, String name, String description, String slug, Images image,
                      int count, int menuOrder) {
        this.image = image;
        this.menuOrder = menuOrder;
        this.name = name;
        this.count = count;
        this.description = description;
        this.id = id;
        this.slug = slug;
    }

    public Images getImage() {
        return image;
    }

    public int getMenuOrder() {
        return menuOrder;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }
}