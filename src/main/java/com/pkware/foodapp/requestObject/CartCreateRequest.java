package com.pkware.foodapp.requestObject;
import java.util.List;

public class CartCreateRequest {
	private int customerId;
	private int cartCost;
	private List<ItemPair> cartItems;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getCartCost() {
		return cartCost;
	}
	public void setCartCost(int cartCost) {
		this.cartCost = cartCost;
	}
	public List<ItemPair> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<ItemPair> cartItems) {
		this.cartItems = cartItems;
	}
	
	
}
