package com.pkware.foodapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderItem {
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderItemId;
	private String itemName;  
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	private int quantity;
	@OneToOne
	private Item item;
	
	public OrderItem(String itemName, int quantity, Item item) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.item = item;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	

}
