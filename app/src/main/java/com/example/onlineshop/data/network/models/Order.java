package com.example.onlineshop.data.network.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Order{

	@SerializedName("order_key")
	private String orderKey;

	@SerializedName("line_items")
	private List<LineItem> lineItems;

	@SerializedName("billing")
	private Billing billing;

	@SerializedName("number")
	private String number;

	@SerializedName("total")
	private String total;

	@SerializedName("shipping")
	private Shipping shipping;

	@SerializedName("payment_method_title")
	private String paymentMethodTitle;

	@SerializedName("currency")
	private String currency;

	@SerializedName("id")
	private int id;

	@SerializedName("payment_method")
	private String paymentMethod;

	@SerializedName("shipping_total")
	private String shippingTotal;

	@SerializedName("shipping_lines")
	private List<ShippingLine> shippingLines;

	@SerializedName("customer_id")
	private int customerId;

	@SerializedName("discount_total")
	private String discountTotal;

	@SerializedName("address_1")
	private String addressOne;

	@SerializedName("address_2")
	private String addressTwo;

	@SerializedName("coupon_lines")
	private List<Coupon> coupons;






	public Order() {
	}

	public Order(String orderKey, List<LineItem> lineItems, Billing billing, String number, String total, Shipping shipping, String paymentMethodTitle, String currency, String paymentMethod, String shippingTotal, List<ShippingLine> shippingLines, int customerId, String discountTotal, String addressOne, String addressTwo, List<Coupon> coupons) {
		this.orderKey = orderKey;
		this.lineItems = lineItems;
		this.billing = billing;
		this.number = number;
		this.total = total;
		this.shipping = shipping;
		this.paymentMethodTitle = paymentMethodTitle;
		this.currency = currency;
		this.paymentMethod = paymentMethod;
		this.shippingTotal = shippingTotal;
		this.shippingLines = shippingLines;
		this.customerId = customerId;
		this.discountTotal = discountTotal;
		this.addressOne = addressOne;
		this.addressTwo = addressTwo;
		this.coupons = coupons;
	}

	public int getId() {
		return id;
	}

	public String getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public String getPaymentMethodTitle() {
		return paymentMethodTitle;
	}

	public void setPaymentMethodTitle(String paymentMethodTitle) {
		this.paymentMethodTitle = paymentMethodTitle;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getShippingTotal() {
		return shippingTotal;
	}

	public void setShippingTotal(String shippingTotal) {
		this.shippingTotal = shippingTotal;
	}

	public List<ShippingLine> getShippingLines() {
		return shippingLines;
	}

	public void setShippingLines(List<ShippingLine> shippingLines) {
		this.shippingLines = shippingLines;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getDiscountTotal() {
		return discountTotal;
	}

	public void setDiscountTotal(String discountTotal) {
		this.discountTotal = discountTotal;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
}