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

	public Order() {
	}

	public Order(String orderKey, List<LineItem> lineItems, Billing billing, String number, String total, Shipping shipping, String paymentMethodTitle, String currency, int id, String paymentMethod, String shippingTotal, List<ShippingLine> shippingLines, int customerId) {
		this.orderKey = orderKey;
		this.lineItems = lineItems;
		this.billing = billing;
		this.number = number;
		this.total = total;
		this.shipping = shipping;
		this.paymentMethodTitle = paymentMethodTitle;
		this.currency = currency;
		this.id = id;
		this.paymentMethod = paymentMethod;
		this.shippingTotal = shippingTotal;
		this.shippingLines = shippingLines;
		this.customerId = customerId;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public void setPaymentMethodTitle(String paymentMethodTitle) {
		this.paymentMethodTitle = paymentMethodTitle;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setShippingTotal(String shippingTotal) {
		this.shippingTotal = shippingTotal;
	}

	public void setShippingLines(List<ShippingLine> shippingLines) {
		this.shippingLines = shippingLines;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getOrderKey(){
		return orderKey;
	}

	public List<LineItem> getLineItems(){
		return lineItems;
	}

	public Billing getBilling(){
		return billing;
	}

	public String getNumber(){
		return number;
	}

	public String getTotal(){
		return total;
	}

	public Shipping getShipping(){
		return shipping;
	}

	public String getPaymentMethodTitle(){
		return paymentMethodTitle;
	}

	public String getCurrency(){
		return currency;
	}

	public int getId(){
		return id;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public String getShippingTotal(){
		return shippingTotal;
	}

	public List<ShippingLine> getShippingLines(){
		return shippingLines;
	}

	public int getCustomerId(){
		return customerId;
	}

}