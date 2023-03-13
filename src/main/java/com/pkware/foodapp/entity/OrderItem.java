package com.pkware.foodapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderItemId;
	@OneToOne(cascade = CascadeType.ALL)
	private Item item;
	private int quantity;
	private String customerMail;
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCustomerMail() {
		return customerMail;
	}
	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}
	public OrderItem(Item item, int quantity, String customerMail) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.customerMail = customerMail;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
