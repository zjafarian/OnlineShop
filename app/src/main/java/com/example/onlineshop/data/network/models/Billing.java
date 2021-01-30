package com.example.onlineshop.data.network.models;

import com.google.gson.annotations.SerializedName;

public class Billing{

	@SerializedName("first_name")
	private String firstName="";

	@SerializedName("last_name")
	private String lastName="";

	@SerializedName("company")
	private String company="";

	@SerializedName("address_1")
	private String address1="";

	@SerializedName("address_2")
	private String address2="";

	@SerializedName("city")
	private String city="";

	@SerializedName("state")
	private String state="";

	@SerializedName("postcode")
	private String postcode="";

	@SerializedName("country")
	private String country="";

	@SerializedName("email")
	private String email="";

	@SerializedName("phone")
	private String phone="";


	public Billing(String firstName, String lastName, String company, String address1, String address2, String city, String state, String postcode, String country, String email, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.country = country;
		this.email = email;
		this.phone = phone;
	}

	public Billing() {
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry(){
		return country;
	}

	public String getCity(){
		return city;
	}

	public String getPhone(){
		return phone;
	}

	public String getAddress1(){
		return address1;
	}

	public String getAddress2(){
		return address2;
	}

	public String getPostcode(){
		return postcode;
	}

	public String getLastName(){
		return lastName;
	}

	public String getState(){
		return state;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getEmail(){
		return email;
	}

	public String getCompany() {
		return company;
	}
}