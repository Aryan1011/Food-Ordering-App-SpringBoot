package com.pkware.foodapp.requestObject;

public class CustomerCreateReq {
	private String customerName;
	private String customerMail;
	private String customerAddress;
	private String customerPhone;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMail() {
		return customerMail;
	}
	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public CustomerCreateReq(String customerName, String customerMail, String customerAddress,
			String customerPhone) {
		super();
		this.customerName = customerName;
		this.customerMail = customerMail;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
	}
	public CustomerCreateReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
