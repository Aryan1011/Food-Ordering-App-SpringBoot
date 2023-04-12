package com.pkware.foodapp.requestObject;

public class ItemRequest {
	
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	private String itemName;
	private String itemDesc;
	private int itemCost;
	private String itemCategory;
	private String itemImage;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public int getItemCost() {
		return itemCost;
	}
	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
}
