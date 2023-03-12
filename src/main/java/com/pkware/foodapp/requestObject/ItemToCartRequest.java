package com.pkware.foodapp.requestObject;

public class ItemToCartRequest {
	private int customerId;
	private int itemId;
	
	public int getCustomerId() {
		return customerId;
	}
	@Override
	public String toString() {
		return "ItemToCartRequest [customerId=" + customerId + ", itemId=" + itemId + "]";
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	
}
