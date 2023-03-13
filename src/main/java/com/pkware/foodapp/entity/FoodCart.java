package com.pkware.foodapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class FoodCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	

	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;
	
	public void addToList(OrderItem orderItem) {
		List<OrderItem> a = this.getOrderItems();
		a.add(orderItem);
		this.setOrderItems(a);
	}
	
	
	public String getCustomerMail() {
		return customerMail;
	}


	

	public FoodCart() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FoodCart(List<OrderItem> orderItems, String customerMail, Customer customer) {
		super();
		this.orderItems = orderItems;
		this.customerMail = customerMail;
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "FoodCart [cartId=" + cartId + ", orderItems=" + orderItems + ", customerMail=" + customerMail
				+ ", customer=" + customer + "]";
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	private String customerMail;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Customer customer;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
	
}
