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

	@Override
	public String toString() {
		return "FoodCart [cartId=" + cartId + ", items=" + items + ", customerMail=" + customerMail + ", customer="
				+ customer + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	
	public FoodCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FoodCart(List<Item> items, String customerMail, Customer customer) {
		super();
		this.items = items;
		this.customerMail = customerMail;
		this.customer = customer;
	}

	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	private List<Item> items;
	
	public void addToList(Item item) {
		List<Item> a = this.getItems();
		a.add(item);
		this.setItems(a);
	}
	
	public void removeFromCart(Item item) {
		List<Item> a = this.getItems();
		a.remove(item);
		this.setItems(a);
	}
	
	public String getCustomerMail() {
		return customerMail;
	}


	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
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
