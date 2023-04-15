package com.pkware.foodapp.response;

import java.util.List;

public class OrderRevenueObject {
	private int totalOrders;
	private int totalRevenue;
	public OrderRevenueObject(int totalOrders, int totalRevenue) {
		super();
		this.totalOrders = totalOrders;
		this.totalRevenue = totalRevenue;
	}
	public OrderRevenueObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTotalOrders() {
		return totalOrders;
	}
	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}
	public int getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(int totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
	
}
