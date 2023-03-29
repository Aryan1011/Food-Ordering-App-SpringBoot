package com.pkware.foodapp.entity;

import java.util.Date;
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
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;
	
	public FoodCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	private Date date;
	
	private int cost;
	
	private String customerMail;


	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public FoodCart(List<OrderItem> orderItems, Customer customer, Date date, int cost, String customerMail) {
		super();
		this.orderItems = orderItems;
		this.customer = customer;
		this.date = date;
		this.cost = cost;
		this.customerMail = customerMail;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> addToList(OrderItem orderItem,List<OrderItem> orderItems ) {
		orderItems.add(orderItem);
		return orderItems;
	}
	
	
}
