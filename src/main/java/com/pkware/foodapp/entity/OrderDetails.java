package com.pkware.foodapp.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private String orderComment;
	private Date orderDate;	
	@OneToOne(cascade = CascadeType.ALL)
	private FoodCart foodCart;
	public String getCustomerMail() {
		return customerMail;
	}
	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetails(String orderComment, Date orderDate, FoodCart foodCart, String status, String customerMail) {
		super();
		this.orderComment = orderComment;
		this.orderDate = orderDate;
		this.foodCart = foodCart;
		this.status = status;
		this.customerMail = customerMail;
	}
	private String status;
	private String customerMail;
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderComment() {
		return orderComment;
	}
	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public FoodCart getFoodCart() {
		return foodCart;
	}
	public void setFoodCart(FoodCart foodCart) {
		this.foodCart = foodCart;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
