package com.pkware.foodapp.response;

import java.util.HashMap;

public class ItemQuantity {
	HashMap<String, Integer> itemQuantity;

	public HashMap<String, Integer> getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(HashMap<String, Integer> itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public ItemQuantity(HashMap<String, Integer> itemQuantity) {
		super();
		this.itemQuantity = itemQuantity;
	}

	public ItemQuantity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
