package com.pkware.foodapp.requestObject;

public class ItemPair {
	int itemId;
	int itemQuantity;
	public ItemPair(int itemId, int itemQuantity) {
		super();
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public ItemPair() {
		super();
		// TODO Auto-generated constructor stub
	}
}
