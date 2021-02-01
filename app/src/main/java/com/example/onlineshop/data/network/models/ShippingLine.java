package com.example.onlineshop.data.network.models;

import com.google.gson.annotations.SerializedName;

public class ShippingLine {

    @SerializedName("id")
    private int id;

    @SerializedName("method_id")
    private String methodId="";

    @SerializedName("method_title")
    private String methodTitle="";

    @SerializedName("total")
    private String total="";

    public ShippingLine() {
    }

    public ShippingLine(int id, String methodId, String methodTitle, String total) {
        this.id = id;
        this.methodId = methodId;
        this.methodTitle = methodTitle;
        this.total = total;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public void setMethodTitle(String methodTitle) {
        this.methodTitle = methodTitle;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMethodId() {
        return methodId;
    }

    public String getMethodTitle() {
        return methodTitle;
    }

    public String getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
