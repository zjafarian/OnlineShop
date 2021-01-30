package com.example.onlineshop.data.network.models;

import com.google.gson.annotations.SerializedName;

public class LineItem {

    @SerializedName("product_id")
    private String productId;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("variation_id")
    private String variationId;

    public LineItem() {
    }

    public LineItem(String productId, String quantity, String variationId) {
        this.productId = productId;
        this.quantity = quantity;
        this.variationId = variationId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    public String getProductId() {
        return productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getVariationId() {
        return variationId;
    }
}
