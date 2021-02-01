package com.example.onlineshop.data.network.models;

import com.google.gson.annotations.SerializedName;

public class LineItem {

    @SerializedName("id")
    private int id;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("variation_id")
    private int variationId;

    @SerializedName("name")
    private String name;

    public LineItem() {
    }

    public LineItem(int id, int productId, int quantity, int variationId, String name) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.variationId = variationId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setVariationId(int variationId) {
        this.variationId = variationId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getVariationId() {
        return variationId;
    }
}
