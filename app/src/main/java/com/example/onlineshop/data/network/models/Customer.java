package com.example.onlineshop.data.network.models;

public class Customer {

    private String email;
    private String first_name;
    private String last_name;
    private String username;
    private Billing billing;
    private Shipping shipping;

    public Customer(String email, String first_name, String last_name, String username,
                    Billing billing,
                    Shipping shipping) {

        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.billing = billing;
        this.shipping = shipping;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public Billing getBilling() {
        return billing;
    }

    public Shipping getShipping() {
        return shipping;
    }


}
