package com.example.onlineshop.data.network.models;

import androidx.annotation.NonNull;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("id")
    private int id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("first_name")
    @Expose
    private String firstName = "";

    @SerializedName("last_name")
    @Expose
    private String lastName = "";

    @SerializedName("billing")
    @Expose
    private Billing billing;

    @SerializedName("shipping")
    @Expose
    private Shipping shipping;

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Customer() {
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getLastName() {
        return lastName;
    }

    public Billing getBilling() {
        return billing;
    }

    public Shipping getShipping() {
        return shipping;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
}